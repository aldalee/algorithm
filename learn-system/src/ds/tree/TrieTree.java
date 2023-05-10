package ds.tree;

/**
 * 前缀树（prefix tree）
 * @author HuanyuLee
 * @date 2023/5/10
 */
public class TrieTree {
    static class TrieNode {
        int pass;
        int end;
        TrieNode[] nexts;

        public TrieNode() {
            // 0 -> a
            // 1 -> b
            //   ...
            // 25 -> z
            // nexts[i] == null i方向的路不存在
            // nexts[i] != null i方向的路存在
            nexts = new TrieNode[26];
        }
    }

    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) return;
        char[] str = word.toCharArray();    // "abc" -> ['a','b','c']
        TrieNode node = root;
        node.pass++;
        for (char c : str) {                // 从左往右遍历字符
            int path = c - 'a';             // 当前字符要走哪条路
            if (node.nexts[path] == null) {
                node.nexts[path] = new TrieNode();
            }
            node = node.nexts[path];
            node.pass++;
        }
        node.end++;
    }

    /**
     * 查询word出现的次数
     */
    public int search(String word) {
        if (word == null) return 0;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        for (char ch : chs) {
            int index = ch - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    public void delete(String word) {
        if (search(word) != 0) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass--;
            for (char ch : chs) {
                int path = ch - 'a';
                if (--node.nexts[path].pass == 0) {
                    node.nexts[path] = null;
                    return;
                }
                node = node.nexts[path];
            }
            node.end--;
        }
    }

    /**
     * 查询加入的字符串中，以pre字符作为前缀的字符串数量
     */
    public int prefixNumber(String pre) {
        if (pre == null) return 0;
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        for (char ch : chs) {
            int index = ch - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.pass;
    }
}

