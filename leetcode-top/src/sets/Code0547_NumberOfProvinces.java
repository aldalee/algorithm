package sets;

/**
 * https://leetcode.cn/problems/number-of-provinces/
 * 省份数量
 * @author HuanyuLee
 * @date 2023/5/23
 */
public class Code0547_NumberOfProvinces {
    /**
     * 使用并查集
     * @param isConnected 1表示第i、j两个城市直接相连，0表示二者不直接相连
     * @return 矩阵中省份的数量
     */
    public int findCircleNum(int[][] isConnected) {
        UnionFind unionFind = new UnionFind(isConnected.length);
        // 只遍历右上角矩阵，因为它是对称矩阵
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) unionFind.union(i, j);
            }
        }
        return unionFind.sets();
    }

    static class UnionFind {
        private int[] parent;       // parent[v]表示节点v的父亲
        private int[] size;         // size[v]表示代表节点v所在的集合大小
        private int[] path;         // 辅助结构
        private int sets;           // 集合总数

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            path = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * 查询v所在集合的代表
         * @param v 待查询节点
         * @return v所在集合的代表节点
         */
        public int find(int v) {
            int idx = 0;
            while (v != parent[v]) {
                path[idx++] = v;
                v = parent[v];
            }
            for (idx--; idx >= 0; idx--) { //路径压缩
                parent[path[idx]] = v;
            }
            return v;
        }

        /**
         * 将a、b所在的集合合并为同一个集合
         * @param a a所在的集合
         * @param b b所在的集合
         */
        public void union(int a, int b) {
            int fa = find(a);
            int fb = find(b);
            if (fa != fb) {
                if (size[fa] >= size[fb]) {
                    size[fa] += size[fb];
                    parent[fb] = fa;
                } else {
                    size[fb] += size[fa];
                    parent[fa] = fb;
                }
                sets--;
            }
        }

        /**
         * 并查集的集合总数
         */
        public int sets() {
            return sets;
        }
    }
}
