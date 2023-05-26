package ds.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图 点结构的描述
 */
public class Vertex {
    public int value;                  // 顶点的值
    public int inDegree;               // 入度
    public int outDegree;              // 出度
    public List<Vertex> nexts;         // 从该顶点出发的邻接顶点集
    public List<Edge> edges;           // 从该顶点出发的边集

    public Vertex(int value) {
        this.value = value;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
