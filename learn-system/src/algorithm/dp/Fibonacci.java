package algorithm.dp;

/**
 * https://leetcode.cn/problems/fibonacci-number/
 * 斐波那契数列
 * @author HuanyuLee
 * @date 2023/5/30
 */
public class Fibonacci {
    /**
     * 暴力递归
     */
    public static int fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 记忆化搜索
     */
    public static int fibonacci2(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        return func(n, dp);
    }

    private static int func(int n, int[] dp) {
        if (dp[n] != 0) {
            return dp[n];
        }
        int ans = func(n - 1, dp) + func(n - 2, dp);
        dp[n] = ans;
        return ans;
    }

    /**
     * 状态转移
     */
    public static int fibonacci3(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 迭代计算
     */
    public static int fibonacci4(int n) {
        if (n < 2) {
            return n;
        }
        int a = 0, b = 1, c = 1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        // 1 1 2 3 5 8 13 21 34 55...
        int n = 1;
        System.out.println(fibonacci(n));
        System.out.println(fibonacci2(n));
        System.out.println(fibonacci3(n));
        System.out.println(fibonacci4(n));
    }
}
