package ds.graph;

/**
 * 图 边结构的描述
 */
public class Edge {
    public Vertex src;         // 起始顶点
    public Vertex dest;        // 目的顶点
    public int weight;         // 边的权重

    public Edge(Vertex src, Vertex dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}
