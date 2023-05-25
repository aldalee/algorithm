package ds.graph;

import java.util.*;

/**
 * 拓扑排序
 * 对一个有向无环图（DAG）进行拓扑排序
 * @author HuanyuLee
 * @date 2023/5/25
 */
public class TopologicalSort {
    /**
     * 根据入度进行拓扑排序
     */
    public static List<Vertex> sortedTopology(Graph graph) {
        Map<Vertex, Integer> inMap = new HashMap<>();       // <某个节点, 剩余的入度>
        Queue<Vertex> zeroInQueue = new LinkedList<>();     // 存储入度为零的节点
        for (var vertex : graph.vertices.values()) {
            inMap.put(vertex, vertex.inDegree);
            if (vertex.inDegree == 0) {
                zeroInQueue.add(vertex);
            }
        }
        List<Vertex> res = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Vertex vertex = zeroInQueue.poll();
            res.add(vertex);
            // 消除当前节点对剩下图的入度影响
            for (var next : vertex.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return res;
    }
}
