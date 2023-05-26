package ds.graph.mst;

import ds.graph.Edge;
import ds.graph.Graph;
import ds.graph.Vertex;

import java.util.*;

/**
 * 最小生成树 —— 克鲁斯卡尔算法
 * 针对无向图
 * @author HuanyuLee
 * @date 2023/5/26
 */
public class Kruskal {
    private static class UnionFind {
        private HashMap<Vertex, Vertex> father;
        private HashMap<Vertex, Integer> size;

        public UnionFind() {
            father = new HashMap<>();
            size = new HashMap<>();
        }

        public void makeSets(Collection<Vertex> vertices) {
            father.clear();
            size.clear();
            for (var vertex : vertices) {
                father.putIfAbsent(vertex, vertex);
                size.putIfAbsent(vertex, 1);
            }
        }

        private Vertex find(Vertex vertex) {
            LinkedList<Vertex> path = new LinkedList<>();
            while (vertex != father.get(vertex)) {
                path.push(vertex);
                vertex = father.get(vertex);
            }
            while (!path.isEmpty()) {
                father.put(path.pop(), vertex);
            }
            return vertex;
        }

        public void union(Vertex a, Vertex b) {
            if (a == null || b == null) {
                return;
            }
            Vertex repA = find(a);
            Vertex repB = find(b);
            if (repA != repB) {
                int sizeA = size.get(repA);
                int sizeB = size.get(repB);
                Vertex big = sizeA >= sizeB ? repA : repB;
                Vertex small = big == repA ? repB : repA;
                father.put(small, big);
                size.put(big, sizeA + sizeB);
                size.remove(small);
            }
        }

        public boolean isSameSet(Vertex a, Vertex b) {
            return find(a) == find(b);
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind uf = new UnionFind();
        uf.makeSets(graph.vertices.values());
        PriorityQueue<Edge> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        heap.addAll(graph.edges);
        Set<Edge> mst = new HashSet<>();
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            if (!uf.isSameSet(edge.src, edge.dest)) {
                mst.add(edge);
                uf.union(edge.src, edge.dest);
            }
        }
        return mst;
    }
}
