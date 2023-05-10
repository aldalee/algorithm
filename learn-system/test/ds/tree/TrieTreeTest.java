package ds.tree;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static tool.ArraysUtil.generateRandomStringArray;

public class TrieTreeTest {
    private static final int arrLen = 100;
    private static final int strLen = 20;
    private static final int testTimes = 100000;

    @Test
    public void test() {
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            TrieTree trieTree = new TrieTree();
            PrefixTree prefixTree = new PrefixTree();
            Right right = new Right();
            for (String s : arr) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trieTree.insert(s);
                    prefixTree.insert(s);
                    right.insert(s);
                } else if (decide < 0.5) {
                    trieTree.delete(s);
                    prefixTree.delete(s);
                    right.delete(s);
                } else if (decide < 0.75) {
                    int ans1 = trieTree.search(s);
                    int ans2 = prefixTree.search(s);
                    int ans3 = right.search(s);
                    assertEquals(ans1, ans2);
                    assertEquals(ans1, ans3);
                    assertEquals(ans2, ans3);
                } else {
                    int ans1 = trieTree.prefixNumber(s);
                    int ans2 = prefixTree.prefixNumber(s);
                    int ans3 = right.prefixNumber(s);
                    assertEquals(ans1, ans2);
                    assertEquals(ans1, ans3);
                    assertEquals(ans2, ans3);
                }
            }
        }
    }
}

class Right {
    private HashMap<String, Integer> box;

    public Right() {
        box = new HashMap<>();
    }

    public void insert(String word) {
        if (!box.containsKey(word)) {
            box.put(word, 1);
        } else {
            box.put(word, box.get(word) + 1);
        }
    }

    public void delete(String word) {
        if (box.containsKey(word)) {
            if (box.get(word) == 1) {
                box.remove(word);
            } else {
                box.put(word, box.get(word) - 1);
            }
        }
    }

    public int search(String word) {
        return box.getOrDefault(word, 0);
    }

    public int prefixNumber(String pre) {
        int count = 0;
        for (String cur : box.keySet()) {
            if (cur.startsWith(pre)) count += box.get(cur);
        }
        return count;
    }
}