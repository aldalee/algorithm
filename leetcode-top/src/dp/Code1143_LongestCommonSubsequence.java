package dp;

/**
 * https://leetcode.cn/problems/longest-common-subsequence/
 * 最长公共子序列
 * @author HuanyuLee
 * @date 2023/5/31
 */
public class Code1143_LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        // dp[i][j]表示s1的前i个字符和s2的前j个字符的LCS
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }
}
