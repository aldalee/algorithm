package dp;

/**
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 * 最长回文子序列
 * @author HuanyuLee
 * @date 2023/6/1
 */
public class Code0516_LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String str) {
        int n = str.length();
        char[] s = str.toCharArray();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int l = n - 2; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                if (s[l] == s[r]) {
                    dp[l][r] = dp[l + 1][r - 1] + 2;
                } else {
                    dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
