package algorithm.dp;

/**
 * 整数分裂问题
 * 给定一个正整数n，求n的裂开方法数，规定: 后面的数不能比前面的数小
 * 比如[4]的裂开方法有: 1+1+1+1、1+1+2、1+3、2+2、4，一共5种，返回5
 * @author HuanyuLee
 * @date 2023/6/5
 */
public class SplitNumber {
    /**
     * 暴力递归
     */
    public static int splitWays(int n) {
        if (n <= 1) {
            return Math.max(0, n);
        }
        return func(1, n);
    }

    /**
     * 递归函数
     * @param pre  上一个拆出来的数
     * @param rest 还剩余需要拆分的数
     * @return 拆解的方法数
     */
    private static int func(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += func(first, rest - first);
        }
        return ways;
    }


    /**
     * 动态规划
     */
    public static int splitWays2(int n) {
        if (n <= 1) {
            return Math.max(0, n);
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int first = pre; first <= rest; first++) {
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    /**
     * 动态规划（状态压缩）
     */
    public static int splitWays3(int n) {
        if (n <= 1) {
            return Math.max(0, n);
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }


    public static void main(String[] args) {
        int n = 8;
        System.out.println(splitWays(n));
        System.out.println(splitWays2(n));
        System.out.println(splitWays3(n));
    }
}
