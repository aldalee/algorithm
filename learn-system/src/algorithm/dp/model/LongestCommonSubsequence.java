package algorithm.dp.model;

/**
 * 最长公共子序列（多样本位置全对应的尝试模型）
 * ============ 例题，感受从暴力递归到动态规划的过程 =============
 * https://leetcode.cn/problems/longest-common-subsequence/
 * @author HuanyuLee
 * @date 2023/5/31
 */
public class LongestCommonSubsequence {
    /**
     * 暴力递归
     */
    public static int longestCommonSubsequence(String s1, String s2) {
        return func(s1.toCharArray(), s2.toCharArray(), s1.length() - 1, s2.length() - 1);
    }

    /**
     * 求s1[0...i] 与 s2[0...j]的最长公共子序列长度
     * 以 s1[i] 与 s2[j] 是否相等这个角度展开讨论
     * @param s1 第一个字符串
     * @param s2 第二个字符串
     * @param i  当前比较的索引值（s1）
     * @param j  当前比较的索引值（s2）
     * @return 最长公共子序列长度
     */
    private static int func(char[] s1, char[] s2, int i, int j) {
        if (i < 0 || j < 0) {       // 当索引值小于0时，子序列为空，长度为0
            return 0;
        }
        if (s1[i] == s2[j]) {       // 当前字符相等，递归调用下一个字符的比较
            return func(s1, s2, i - 1, j - 1) + 1;
        } else {                    // 当前字符不等，分别递归调用减少一个字符的比较，然后取最大值
            return Math.max(func(s1, s2, i - 1, j), func(s1, s2, i, j - 1));
        }
    }

    /**
     * 动态规划
     */
    public static int dpLongestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        // dp[i][j]表示s1的前i个字符和s2的前j个字符的LCS
        int[][] dp = new int[n][m];
        // 初始化边界条件
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = s1[0] == s2[j] ? 1 : dp[0][j - 1];
        }
        // 动态规划状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (s1[i] == s2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    // 课上版本
    // 以[i]和[j]要与不要 这个角度展开的讨论
    private static int lcs(char[] s1, char[] s2, int i, int j) {
        if (i == 0 && j == 0) {
            return 0;
        } else if (i == 0) {
            if (s1[i] == s2[j]) {
                return 1;
            } else {
                return lcs(s1, s2, i, j - 1);
            }
        } else if (j == 0) {
            if (s1[i] == s2[j]) {
                return 1;
            } else {
                return lcs(s1, s2, i - 1, j);
            }
        }
        int p1 = lcs(s1, s2, i - 1, j);     // 不要[i]位置，要[j]位置
        int p2 = lcs(s1, s2, i, j - 1);     // 要[i]位置，不要[j]位置
        // 要[i]位置，要[j]位置
        int p3 = 0;
        if (s1[i] == s2[j]) {
            p3 = 1 + lcs(s1, s2, i - 1, j - 1);
        }
        // 不要[i]位置，不要[j]位置
        // p4 <= p1，p4 <= p2
        // 题目是让求最长公共子序列，故p4的存在是多余的
        int p4 = lcs(s1, s2, i - 1, j - 1);
        return Math.max(Math.max(p1, p2), p3);
    }

    /**
     * 动态规划
     */
    public static int dpLongestCommonSubsequence2(String string1, String string2) {
        int n = string1.length();
        int m = string2.length();
        char[] s1 = string1.toCharArray();
        char[] s2 = string2.toCharArray();
        int[][] dp = new int[n][m];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = s1[0] == s2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = s1[i] == s2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }
        return dp[n - 1][m - 1];
    }


    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(longestCommonSubsequence(s1, s2));
        System.out.println(dpLongestCommonSubsequence(s1, s2));
        System.out.println(dpLongestCommonSubsequence2(s1, s2));
    }
}
