package datastructure.hashtable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * 哈希表：HashMap  增删改查 O(1)
 * 有序表：TreeMap  增删改查 O(log N)
 * Java 中有序表是用红黑树实现的。当然，AVL树、SB树、跳表都可以实现有序表
 * @author HuanyuLee
 * @date 2022/10/2
 */
public class Code01_HashMapAndSortedMap {
    static class Node {
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void hashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer a = 2022;
        Integer b = 2022;
        System.out.println(a == b);
        map.put(a, "a:2022");
        // Java原生类型，在HashMap中按值传递
        System.out.println(map.containsKey(b));

        Node node1 = new Node(1);
        Node node2 = new Node(1);
        HashMap<Node, String> map2 = new HashMap<>();
        map2.put(node1, "node01::1");
        // Java非原生类型，在HashMap中按引用传递
        System.out.println(map2.containsKey(node2));
    }

    public static void treeMap() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "value = 3");
        treeMap.put(2, "value = 2");
        treeMap.put(6, "value = 6");
        treeMap.put(5, "value = 5");
        treeMap.put(5, "value = 5'");
        System.out.println(treeMap.containsKey(5));
        System.out.println(treeMap.get(5));
        // ======= 比HashMap多的功能 =======
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        // <= 4 最右的
        System.out.println(treeMap.floorKey(4));
        // >= 4 最左的
        System.out.println(treeMap.ceilingKey(4));

        // 引用类型需要自定义比较器
        TreeMap<Node, String> treeMap2 = new TreeMap<>((Comparator.comparingInt(o -> o.value)));
        Node[] nodes = new Node[4];
        for (int i = nodes.length - 1; i >= 0; i--) {
            nodes[i] = new Node(i);
            treeMap2.put(nodes[i], "" + i);
        }
        System.out.println(treeMap2.firstKey().value);
        System.out.println(treeMap2.lastKey().value);
    }

    public static void main(String[] args) {
        // hashMap();
        treeMap();
    }
}
