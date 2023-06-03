package algorithm.dp;

/**
 * 零钱兑换I
 * @author HuanyuLee
 * @date 2023/6/2
 */
public class CoinsWayEveryPaperDifferent {
    /**
     * 暴力递归
     */
    public static int coinWays(int[] coins, int amount) {
        return func(coins, 0, amount);
    }

    /**
     * 递归函数，coins[idx...n]，每个面值都只能选一张，组成rest这些钱的方法数
     * @param coins 货币数组
     * @param idx   当前位置
     * @param rest  组成剩余的钱
     * @return 方法数
     */
    private static int func(int[] coins, int idx, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (idx == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        return func(coins, idx + 1, rest) + func(coins, idx + 1, rest - coins[idx]);
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
                dp[idx][rest] = dp[idx + 1][rest];
                int down = rest - coins[idx];
                if (down >= 0) {    // idx下边的不越界
                    dp[idx][rest] += dp[idx + 1][down];
                }
            }
        }
        return dp[0][amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 1, 1};
        int amount = 2;
        System.out.println(coinWays(coins, amount));
        System.out.println(coinWays2(coins, amount));
    }
}
