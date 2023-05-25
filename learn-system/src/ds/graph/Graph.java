package ds.graph;

import java.util.*;

/**
 * 图结构的定义 G = <V,E>
 * 这种面向对象表达图的方式有别于邻接矩阵、邻接表法
 * 虽然图分为有向图和无向图，但无向图也可看做是特殊的有向图
 * 因此，下面的代码都用有向图来表达和定义
 * @author HuanyuLee
 * @date 2023/5/25
 */
public class Graph {
    Map<Integer, Vertex> vertices;      // 图的顶点集（哈希表是为了做Value到Vertex的映射）
    Set<Edge> edges;                    // 图的边集

    public Graph() {
        vertices = new HashMap<>();
        edges = new HashSet<>();
    }

    public boolean isEmpty(){
        return vertices.isEmpty();
    }
}

/**
 * 图 点结构的描述
 */
class Vertex {
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

/**
 * 图 边结构的描述
 */
class Edge {
    Vertex src;         // 起始顶点
    Vertex dest;        // 目的顶点
    int weight;         // 边的权重

    public Edge(Vertex src, Vertex dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}