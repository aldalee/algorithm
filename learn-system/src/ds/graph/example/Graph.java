package ds.graph.example;

import java.util.*;

/**
 * 一个完整的图代码示例
 * @author HuanyuLee
 * @date 2023/5/25
 */
public class Graph {
    Map<Integer, Vertex> vertices;  // 图的顶点集
    Set<Edge> edges;                // 图的边集

    public Graph() {
        vertices = new HashMap<>();
        edges = new HashSet<>();
    }

    static class Vertex {
        int value;                  // 顶点的值
        int inDegree;               // 入度
        int outDegree;              // 出度
        List<Vertex> nexts;         // 从该顶点出发的邻接顶点集
        List<Edge> edges;           // 从该顶点出发的边集

        public Vertex(int value) {
            this.value = value;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    static class Edge {
        Vertex src;                 // 起始顶点
        Vertex dest;                // 目的顶点
        int weight;                 // 边的权重

        public Edge(Vertex src, Vertex dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // matrix[i] = [src, dest, weight]
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (var vector : matrix) {
            int from = vector[0];
            int to = vector[1];
            int weight = vector[2];
            graph.vertices.putIfAbsent(from, new Vertex(from));
            graph.vertices.putIfAbsent(to, new Vertex(to));
            Vertex src = graph.vertices.get(from);
            Vertex dest = graph.vertices.get(to);
            Edge edge = new Edge(src, dest, weight);
            src.nexts.add(dest);
            src.outDegree++;
            dest.inDegree++;
            src.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }

    public static List<Integer> dfs(Vertex start) {
        List<Integer> ans = new ArrayList<>();
        if (start == null) {
            return ans;
        }
        LinkedList<Vertex> stack = new LinkedList<>();
        Set<Vertex> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        ans.add(start.value);
        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            for (var next : vertex.nexts) {
                if (!set.contains(next)) {
                    stack.push(vertex);
                    stack.push(next);
                    set.add(next);
                    ans.add(next.value);
                    break;
                }
            }
        }
        return ans;
    }

    public static List<Integer> bfs(Vertex start) {
        List<Integer> ans = new ArrayList<>();
        if (start == null) {
            return ans;
        }
        Deque<Vertex> queue = new LinkedList<>();
        Set<Vertex> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            ans.add(vertex.value);
            for (var next : vertex.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 0},
                {1, 3, 0},
                {1, 4, 0},
                {1, 6, 0},
                {2, 3, 0},
                {2, 5, 0},
                {3, 4, 0},
                {5, 3, 0},
                {6, 4, 0},
        };
        Graph graph = createGraph(matrix);
        Vertex start = graph.vertices.get(1);
        System.out.println("start = " + start.value);
        System.out.println("BFS = " + bfs(start));
        System.out.println("DFS = " + dfs(start));
    }
}
