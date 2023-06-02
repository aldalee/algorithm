package dp;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/coin-change/
 * 零钱兑换
 * @author HuanyuLee
 * @date 2023/6/2
 */
public class Code0322_CoinChange {
    /**
     * 暴力递归
     */
    public int coinChange(int[] coins, int amount) {
        return func(coins, amount);
    }

    /**
     * 计算并返回可以凑成rest金额所需的最少的硬币个数
     * @param coins 货币数组
     * @param rest  待凑成的钱数
     * @return 最少的硬币个数
     */
    private int func(int[] coins, int rest) {
        if (rest < 0) {     // 无法凑成目标金额
            return -1;
        }
        if (rest == 0) {    // 已经凑成目标金额
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rest - coin);
            if (res != -1) {
                minCoins = Math.min(minCoins, res + 1);
            }
        }
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    /**
     * 动态规划
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int rest = 1; rest <= amount; rest++) {
            for (int coin : coins) {
                if (coin <= rest) {
                    dp[rest] = Math.min(dp[rest], dp[rest - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
