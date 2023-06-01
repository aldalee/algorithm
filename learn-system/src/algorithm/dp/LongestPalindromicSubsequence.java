package algorithm.dp;

/**
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 * 最长回文子序列（范围上的尝试模型）
 * 子序列定义为: 不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列
 * @author HuanyuLee
 * @date 2023/6/1
 */
public class LongestPalindromicSubsequence {
    /**
     * 暴力递归
     */
    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return lps(s.toCharArray(), 0, s.length() - 1);
    }

    /**
     * 递归函数，求s[l...r]最长回文子序列的长度
     * @param s 字符串
     * @param l 左边界
     * @param r 右边界
     * @return 最长回文子序列的长度
     */
    private static int lps(char[] s, int l, int r) {
        if (l == r) return 1;
        if (l > r) return 0;

        if (s[l] == s[r]) {
            return lps(s, l + 1, r - 1) + 2;
        } else {
            return Math.max(lps(s, l, r - 1), lps(s, l + 1, r));
        }
    }

    /**
     * 动态规划
     */
    public static int longestPalindromeSubseq2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int n = str.length();
        char[] s = str.toCharArray();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 从下到上、从左到右依次填
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

    /**
     * 一个字符串和它的逆序字符串的最长公共子序列，就是原串的最长回文子序列
     */
    public static int longestPalindromeSubseq3(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        String reverse = new StringBuffer(str).reverse().toString();
        return longestCommonSubsequence(str, reverse);
    }

    // 最长公共子序列
    private static int longestCommonSubsequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
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


    public static void main(String[] args) {
        // String str = "a12b3c43def2ghi1kpm";
        // String str = "bbbab";
        String str = "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew";
        System.out.println(longestPalindromeSubseq(str));
        System.out.println(longestPalindromeSubseq2(str));
        System.out.println(longestPalindromeSubseq3(str));
    }
}
