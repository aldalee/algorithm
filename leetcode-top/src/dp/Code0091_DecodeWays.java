package dp;

/**
 * https://leetcode.cn/problems/decode-ways/
 * 解码方法
 * @author HuanyuLee
 * @date 2023/5/31
 */
public class Code0091_DecodeWays {
    public int numDecodings(String s) {
        int n = s.length();
        char[] str = s.toCharArray();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int idx = n - 1; idx >= 0; idx--) {
            if (str[idx] != '0') {
                int ways = dp[idx + 1];
                if (idx + 1 < n && (str[idx] - '0') * 10 + (str[idx + 1] - '0') < 27) {
                    ways += dp[idx + 2];
                }
                dp[idx] = ways;
            }
        }
        return dp[0];
    }
}
