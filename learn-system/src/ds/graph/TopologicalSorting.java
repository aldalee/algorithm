package ds.graph;

import java.util.*;

/**
 * https://www.lintcode.com/problem/127/
 * 拓扑排序 对特殊结构的图描述
 * @author HuanyuLee
 * @date 2023/5/25
 */
public class TopologicalSorting {
    // Definition for Directed graph.
    static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    /**
     * 方法一: 根据入度进行拓扑排序
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> inMap = new HashMap<>();
        Queue<DirectedGraphNode> zeroInQueue = new LinkedList<>();
        for (var cur : graph) {
            inMap.put(cur, 0);
        }

        for (var cur : graph) {
            for (var next : cur.neighbors) {
                inMap.put(next, inMap.get(next) + 1);
            }
        }
        for (var node : inMap.keySet()) {
            if (inMap.get(node) == 0) {
                zeroInQueue.add(node);
            }
        }
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            var node = zeroInQueue.poll();
            ans.add(node);
            for (var next : node.neighbors) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return ans;
    }

    /**
     * 方法二: 根据顶点数进行拓扑排序
     */
    static class Record {
        DirectedGraphNode node;
        long nodes;

        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    public ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Record> order = new HashMap<>();
        for (var cur : graph) {
            process(cur, order);
        }
        ArrayList<Record> record = new ArrayList<>(order.values());
        record.sort((o1, o2) -> Long.compare(o2.nodes, o1.nodes));
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (var r : record) {
            ans.add(r.node);
        }
        return ans;
    }

    /**
     * 递归函数
     * @param cur   当前来到的顶点
     * @param order 缓存
     * @return Record信息结构体
     */
    private Record process(DirectedGraphNode cur, Map<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        long nodes = 0;
        for (var next : cur.neighbors) {
            nodes += process(next, order).nodes;
        }
        Record record = new Record(cur, nodes + 1);
        order.put(cur, record);
        return record;
    }


    /**
     * 方法三: 根据深度进行拓扑排序
     */
    static class Info {
        DirectedGraphNode node;
        int deep;

        public Info(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public ArrayList<DirectedGraphNode> topSort3(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Info> order = new HashMap<>();
        for (var cur : graph) {
            dfs(cur, order);
        }
        ArrayList<Info> infos = new ArrayList<>(order.values());
        infos.sort((o1, o2) -> o2.deep - o1.deep);
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (var info : infos) {
            ans.add(info.node);
        }
        return ans;
    }

    /**
     * 递归函数
     * @param cur   当前来到的顶点
     * @param order 缓存
     * @return Record信息结构体
     */
    private Info dfs(DirectedGraphNode cur, Map<DirectedGraphNode, Info> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        int follow = 0;
        for (var next : cur.neighbors) {
            follow = Math.max(follow, dfs(next, order).deep);
        }
        Info info = new Info(cur, follow + 1);
        order.put(cur, info);
        return info;
    }
}
