package ds.graph.mst;

import ds.graph.Edge;
import ds.graph.Graph;
import ds.graph.Vertex;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树 —— 普里姆算法
 * 针对无向图
 * 参考博客: Why Can’t Prim’s or Kruskal’s Algorithms Be Used on a Directed Graph?
 * 博客地址: https://www.baeldung.com/cs/prims-kruskals-on-directed-graph
 * @author HuanyuLee
 * @date 2023/5/26
 */
public class Prim {
    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        Set<Vertex> set = new HashSet<>();          // 记录被解锁的顶点
        Set<Edge> mst = new HashSet<>();            // 保存依次挑选的边
        for (var vertex : graph.vertices.values()) {
            if (!set.contains(vertex)) {
                set.add(vertex);
                heap.addAll(vertex.edges);          // 由一个顶点，解锁其所有直接相连的边
                while (!heap.isEmpty()) {
                    Edge edge = heap.poll();        // 弹出解锁的边中权值最小的一个
                    Vertex dest = edge.dest;        // 得到一个可能的新的点
                    if (!set.contains(dest)) {      // 当set集合中不存在时，该点为新的点
                        set.add(dest);
                        mst.add(edge);              // 记录新解锁的边
                        heap.addAll(dest.edges);    // 再次由点解锁其所有直接相连的边
                    }
                }
            }
            // break;                               // 如果存在森林，注释掉；如果确定只有一个图，打开注释
        }
        return mst;
    }
}
