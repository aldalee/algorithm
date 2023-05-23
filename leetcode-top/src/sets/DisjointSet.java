package sets;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 并查集（哈希表实现）
 * @author HuanyuLee
 * @date 2023/5/23
 */
public class DisjointSet<V> {
    private HashMap<V, V> father = new HashMap<>();
    private HashMap<V, Integer> size= new HashMap<>();

    public DisjointSet(List<V> values) {
        for (V v : values) {
            father.put(v, v);
            size.put(v, 1);
        }
    }

    /**
     * 查询v所在集合的代表
     * @param v 待查询节点
     * @return v所在集合的代表节点
     */
    public V find(V v) {
        Deque<V> path = new LinkedList<>();
        while (v != father.get(v)) {
            path.push(v);
            v = father.get(v);
        }
        // 更改沿途节点的代表节点，下次再次遇到，直接返回（优化二）
        while (!path.isEmpty()) {
            father.put(path.pop(), v);
        }
        return v;
    }

    /**
     * 将a、b代表的集合合并为同一个集合
     * @param a a代表的集合
     * @param b b代表的集合
     */
    public void union(V a, V b) {
        V repA = find(a);                   // a的代表节点
        V repB = find(b);                   // b的代表节点
        if (repA != repB) {
            int sizeA = size.get(repA);
            int sizeB = size.get(repB);
            V big = sizeA >= sizeB ? repA : repB;
            V small = big == repA ? repB : repA;
            father.put(small, big);         // 小集合挂在大集合上（优化一）
            size.put(big, sizeA + sizeB);   // 更新大集合的大小记录
            size.remove(small);             // 移除小集合的大小记录
        }
    }

    /**
     * 判断节点a、b是否隶属于同一个集合
     */
    public boolean isSameSet(V a, V b) {
        return find(a) == find(b);
    }

    /**
     * 并查集的集合总数
     */
    public int sets() {
        return size.size();
    }
}
