package algorithm.dp;

/**
 * https://leetcode.cn/problems/decode-ways/
 * 解码方法
 * @author HuanyuLee
 * @date 2023/5/31
 */
public class ConvertToLetterString {
    /**
     * 暴力递归
     */
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return decode(s.toCharArray(), 0);
    }

    // str[0...idx-1]已经转化好了
    // str[idx...n]去转化，可以得到的转换方法数
    private static int decode(char[] str, int idx) {
        if (idx == str.length) {    // 这一趟的转化是OK的，因此收集到1个
            return 1;
        }
        if (str[idx] == '0') {      // 之前的转化有问题，因此收集到0个
            return 0;
        }
        // 可能性一，[idx]位置的字符单独转
        int ways = decode(str, idx + 1);
        // 可能性二，[idx,idx+1]位置的字符一块转（但它必须是合法的）
        if (idx + 1 < str.length && (str[idx] - '0') * 10 + (str[idx + 1] - '0') < 27) {
            ways += decode(str, idx + 2);
        }
        return ways;
    }

    /**
     * 动态规划
     */
    public static int dpNumDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
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


    public static void main(String[] args) {
        System.out.println(numDecodings("111111111111111111111111111111111111111111111"));
        System.out.println(dpNumDecodings("111111111111111111111111111111111111111111111"));

        System.out.println(numDecodings("06"));
        System.out.println(dpNumDecodings("06"));
    }
}
