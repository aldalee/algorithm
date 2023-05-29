package ds.graph.sp;

import ds.graph.Edge;
import ds.graph.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 单源最短路径 —— 迪杰斯特拉算法
 * 无负权重有向图，要给定出发点
 * @author HuanyuLee
 * @date 2023/5/26
 */
public class Dijkstra {
    public static Map<Vertex, Integer> dijkstra(Vertex source) {
        Map<Vertex, Integer> distanceMap = new HashMap<>();
        distanceMap.put(source, 0);
        Set<Vertex> selected = new HashSet<>();
        Vertex vertex = getNextVertex(distanceMap, selected);
        while (vertex != null) {
            int dist = distanceMap.get(vertex);
            for (Edge edge : vertex.edges) {
                Vertex to = edge.dest;
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, dist + edge.weight);
                } else {
                    distanceMap.put(edge.dest, Math.min(distanceMap.get(to), dist + edge.weight));
                }
            }
            selected.add(vertex);
            vertex = getNextVertex(distanceMap, selected);
        }
        return distanceMap;
    }

    /**
     * 从给定的顶点集合中选择下一个距离最小且未被选择的顶点
     * @param distanceMap 源顶点到目标顶点的距离映射表
     * @param selected    已选择的顶点集合
     * @return 下一个距离最小且未被选择的顶点，如果不存在这样的顶点则返回null
     */
    private static Vertex getNextVertex(Map<Vertex, Integer> distanceMap, Set<Vertex> selected) {
        Vertex minVertex = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Vertex, Integer> entry : distanceMap.entrySet()) {
            Vertex vertex = entry.getKey();
            int distance = entry.getValue();
            if (!selected.contains(vertex) && distance < minDistance) {
                minVertex = vertex;
                minDistance = distance;
            }
        }
        return minVertex;
    }

    // ================================= Dijkstra算法改进 =================================

    private static class Heap {
        Vertex[] nodes;         // 堆的结构
        int size;               // 堆的大小
        Map<Vertex, Integer> heapIdxMap = new HashMap<>();  // 反向索引表
        Map<Vertex, Integer> distanceMap = new HashMap<>();

        public Heap(int size) {
            nodes = new Vertex[size];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 将节点vertex添加到堆中，如果节点已存在则更新距离值
         * @param node     当前的节点
         * @param distance 从源节点到vertex的距离
         */
        public void putIfAbsentOrUpdate(Vertex node, int distance) {
            if (!isEntered(node)) {   // add
                nodes[size] = node;
                heapIdxMap.put(node, size);
                distanceMap.put(node, distance);
                heapInsert(node, size++);
            }
            if (inHeap(node)) {       // update
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(node, heapIdxMap.get(node));
            }
        }

        public Info pop() {
            Info info = new Info(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIdxMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return info;
        }

        private void heapInsert(Vertex node, int idx) {
            while (distanceMap.get(nodes[idx]) < distanceMap.get(nodes[(idx - 1) / 2])) {
                swap(idx, (idx - 1) / 2);
                idx = (idx - 1) / 2;
            }
        }

        private void heapify(int idx, int size) {
            int l, r, fewest;
            while (true) {
                l = 2 * idx + 1;
                r = 2 * idx + 2;
                fewest = idx;
                if (l < size && distanceMap.get(nodes[l]) < distanceMap.get(nodes[fewest])) fewest = l;
                if (r < size && distanceMap.get(nodes[r]) < distanceMap.get(nodes[fewest])) fewest = r;
                if (fewest == idx) break;
                swap(fewest, idx);
                idx = fewest;
            }
        }

        private boolean isEntered(Vertex node) {
            return heapIdxMap.containsKey(node);
        }

        private boolean inHeap(Vertex node) {
            return isEntered(node) && heapIdxMap.get(node) != -1;
        }

        private void swap(int i, int j) {
            heapIdxMap.put(nodes[i], j);
            heapIdxMap.put(nodes[j], i);
            Vertex tmp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = tmp;
        }
    }

    private static class Info {
        Vertex node;
        int distance;

        public Info(Vertex node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static Map<Vertex, Integer> dijkstra(Vertex source, int size) {
        Heap heap = new Heap(size);
        heap.putIfAbsentOrUpdate(source, 0);
        Map<Vertex, Integer> distanceMap = new HashMap<>();
        while (!heap.isEmpty()) {
            Info info = heap.pop();
            Vertex vertex = info.node;
            int distance = info.distance;
            for (Edge edge : vertex.edges) {
                heap.putIfAbsentOrUpdate(edge.dest, edge.weight + distance);
            }
            distanceMap.put(vertex, distance);
        }
        return distanceMap;
    }
}
