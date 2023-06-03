package algorithm.dp;

/**
 * https://leetcode.cn/problems/coin-change-ii/
 * 零钱兑换II
 * 记忆化搜索和表结构依赖的DP，在没有枚举行为时，同样地优秀！
 * @author HuanyuLee
 * @date 2023/6/2
 */
public class CoinsWayNoLimit {
    /**
     * 暴力递归
     */
    public static int coinWays(int[] coins, int amount) {
        return func(coins, 0, amount);
    }

    /**
     * 递归函数（暴力枚举）
     * coins[idx...n]所有的面值，每个面值都可以选择任意张数，组成rest这些钱的方法数
     * @param coins 货币数组
     * @param idx   当前位置
     * @param rest  组成剩余的钱
     * @return 方法数
     */
    private static int func(int[] coins, int idx, int rest) {
        if (idx == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int num = 0; num * coins[idx] <= rest; num++) {  // num表示[idx]位置使用货币的张数
            ways += func(coins, idx + 1, rest - num * coins[idx]);
        }
        return ways;
    }

    /**
     * 动态规划
     */
    public static int coinWays2(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[n][0] = 1;
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= amount; rest++) {
                int ways = 0;
                for (int num = 0; num * coins[idx] <= rest; num++) {
                    ways += dp[idx + 1][rest - num * coins[idx]];
                }
                dp[idx][rest] = ways;
            }
        }
        return dp[0][amount];
    }


    /**
     * 动态规划（状态压缩）
     */
    public static int coinWays3(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[n][0] = 1;
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= amount; rest++) {
                dp[idx][rest] = dp[idx + 1][rest];
                int left = rest - coins[idx];
                if (left >= 0) {    // idx左边的不越界
                    dp[idx][rest] += dp[idx][left];
                }
            }
        }
        return dp[0][amount];
    }


    public static void main(String[] args) {
        int[] coins = {3, 5, 7, 8, 9, 10, 11};
        int amount = 500;
        System.out.println(coinWays(coins, amount));
        System.out.println(coinWays2(coins, amount));
        System.out.println(coinWays3(coins, amount));
    }
}
