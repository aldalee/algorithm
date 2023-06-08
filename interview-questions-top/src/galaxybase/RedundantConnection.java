package galaxybase;

import java.util.Scanner;

/**
 * 创邻科技笔试题
 * @author HuanyuLee
 * @date 2023/6/8
 */
public class RedundantConnection {
    public static int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        // 初始化并查集，每个节点的父亲节点是自己
        for (int i = 1; i <= edges.length; i++) {
            parent[i] = i;
        }
        int[] result = null;
        // 遍历每一条边
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            // 找到 u 和 v 所在的集合的根节点
            int rootU = find(u, parent);
            int rootV = find(v, parent);
            // 如果根节点相同，说明 u 和 v 已经在同一个集合中了，删除该边
            if (rootU == rootV) {
                result = edge;
            } else { // 否则将两个集合合并
                parent[rootU] = rootV;
            }
        }
        return result;
    }

    // 找到节点 x 所在集合的根节点
    private static int find(int x, int[] parent) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]]; // 路径压缩
            x = parent[x];
        }
        return x;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] edges = new int[n][2];
        for (int i = 0; i < n; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }
        sc.close();
        int[] result = findRedundantConnection(edges);
        System.out.println(result[0] + " " + result[1]);
    }
}
