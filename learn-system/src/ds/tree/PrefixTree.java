package ds.tree;

import java.util.HashMap;

/**
 * 前缀树-实现方式二
 * @author HuanyuLee
 * @date 2023/5/10
 */
public class PrefixTree {
    static class TrieNode {
        int pass;
        int end;
        HashMap<Integer, TrieNode> nexts;

        public TrieNode() {
            nexts = new HashMap<>();
        }
    }

    private TrieNode root;

    public PrefixTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) return;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        node.pass++;
        int index = 0;
        for (char ch : chs) {
            index = (int) ch;
            if (!node.nexts.containsKey(index)) {
                node.nexts.put(index, new TrieNode());
            }
            node = node.nexts.get(index);
            node.pass++;
        }
        node.end++;
    }

    public void delete(String word) {
        if (search(word) != 0) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass--;
            int index = 0;
            for (char ch : chs) {
                index = (int) ch;
                if (--node.nexts.get(index).pass == 0) {
                    node.nexts.remove(index);
                    return;
                }
                node = node.nexts.get(index);
            }
            node.end--;
        }
    }

    public int search(String word) {
        if (word == null) return 0;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (char ch : chs) {
            index = (int) ch;
            if (!node.nexts.containsKey(index)) return 0;
            node = node.nexts.get(index);
        }
        return node.end;
    }

    public int prefixNumber(String pre) {
        if (pre == null) return 0;
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (char ch : chs) {
            index = (int) ch;
            if (!node.nexts.containsKey(index)) return 0;
            node = node.nexts.get(index);
        }
        return node.pass;
    }
}
