package algorithm.greedy;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 字典序最小的字符串
 * 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来
 * 返回所有可能的拼接结果中，字典序最小的结果
 * @author HuanyuLee
 * @date 2023/5/23
 */
public class LowestLexicography {
    /**
     * 贪心策略
     * @param strs 字符串数组
     * @return 所有可能的拼接结果中字典序最小的结果
     */
    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();
    }

    /**
     * 暴力求解（太TM暴力了，很容易栈溢出）
     * @param strs 字符串数组
     * @return 所有可能的拼接结果中字典序最小的结果
     */
    public static String bruteForceSolution(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }

    // strs中所有字符串全排列，返回所有可能的结果
    private static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> set = process(nexts);
            for (String str : set) {
                ans.add(first + str);
            }
        }
        return ans;
    }

    /**
     * {"abc", "cks", "bct"}
     * removeIndexString(arr , 1) -> {"abc", "bct"}
     */
    private static String[] removeIndexString(String[] arr, int idx) {
        int n = arr.length;
        String[] ans = new String[n - 1];
        int ansIdx = 0;
        for (int i = 0; i < n; i++) {
            if (i != idx) {
                ans[ansIdx++] = arr[i];
            }
        }
        return ans;
    }
}
