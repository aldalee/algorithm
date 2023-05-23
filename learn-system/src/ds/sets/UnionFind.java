package ds.sets;

/**
 * 并查集（数组实现）
 * 刷题过程中，推荐使用这个
 * @author HuanyuLee
 * @date 2023/5/23
 */
public class UnionFind {
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
     * 判断节点a、b是否隶属于同一个集合
     */
    public boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    /**
     * 并查集的集合总数
     */
    public int sets() {
        return sets;
    }
}
