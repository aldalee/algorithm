package ds.graph.mst;

import java.util.*;

/**
 * （牛客网）NC159 最小生成树
 * @author HuanyuLee
 * @date 2023/5/26
 */
public class MiniSpanningTree {
    private int find(int[] father, int vertex) {
        int root = vertex;
        while (root != father[root]) {
            root = father[root];
        }
        while (vertex != root) {
            int next = father[vertex];
            father[vertex] = root;
            vertex = next;
        }
        return root;
    }

    // graph[i] = [src, dest, weight]
    private int kruskal(int n, int[][] graph) {
        int[] father = new int[n + 1];
        int[] size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
        Arrays.sort(graph, Comparator.comparingInt(o -> o[2]));
        int sum = 0;
        for (int[] edge : graph) {
            int f1 = find(father, edge[0]);
            int f2 = find(father, edge[1]);
            if (f1 != f2) {                 // 并查集合并
                if (size[f1] > size[f2]) {
                    father[f2] = f1;
                    size[f1] += size[f2];
                } else {
                    father[f1] = f2;
                    size[f2] += size[f1];
                }
                sum += edge[2];
            }
        }
        return sum;
    }

    // matrix[i] = [src, dest, weight]
    public int prim(int n, int[][] matrix) {
        // 创建邻接表来表示图
        @SuppressWarnings("unchecked")
        ArrayList<int[]>[] graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        // 根据给定的matrix数组构建邻接表
        for (int[] ints : matrix) {
            int u = ints[0];
            int v = ints[1];
            int w = ints[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
        int sum = 0;
        boolean[] visited = new boolean[n + 1];
        // 优先队列用于存储边，按照边的权值排序，优先选择权值最小的边
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        // 从第一个顶点开始，将其标记为已访问，并将与其相邻的边加入优先队列
        visited[1] = true;
        heap.addAll(graph[1]);
        while (!heap.isEmpty()) {
            // 从优先队列中取出权值最小的边
            int[] edge = heap.poll();
            int next = edge[0];
            int weight = edge[1];
            if (!visited[next]) {
                visited[next] = true;
                sum += weight;
                // 将目标顶点的所有相邻边加入优先队列
                heap.addAll(graph[next]);
            }
        }
        return sum;
    }

    /**
     * 返回最小的花费代价使得这n户人家连接起来
     * @param n    顶点数
     * @param m    边数
     * @param cost cost[i] = [src, dest, weight]
     * @return int
     */
    public int miniSpanningTree(int n, int m, int[][] cost) {
        // return kruskal(n, cost);
        return prim(n, cost);
    }
}
