package ds.graph;

/**
 * 图接口转化的适配器
 * @author HuanyuLee
 * @date 2023/5/25
 */
public class GraphGenerator {
    /**
     * 将用户提供的个性化图结构定义，转化成自己熟悉的图结构
     * 面试场上最常用的，以边的形式进行描述图
     * @param matrix 2D矩阵，matrix[i] = [src, dest, weight]
     * @return 自己熟悉的图结构定义
     */
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
}
