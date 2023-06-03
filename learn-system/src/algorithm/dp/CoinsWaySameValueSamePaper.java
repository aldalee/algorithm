package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 零钱兑换III
 * @author HuanyuLee
 * @date 2023/6/2
 */
public class CoinsWaySameValueSamePaper {
    private static class Info {
        int[] coins;    // 面值
        int[] nums;     // 张数

        public Info(int[] coins, int[] nums) {
            this.coins = coins;
            this.nums = nums;
        }
    }

    private static Info wordCount(int[] arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int val : arr) {
            if (!counts.containsKey(val)) {
                counts.put(val, 1);
            } else {
                counts.put(val, counts.get(val) + 1);
            }
        }
        int n = counts.size();
        int[] coins = new int[n];
        int[] nums = new int[n];
        int idx = 0;
        for (var entry : counts.entrySet()) {
            coins[idx] = entry.getKey();
            nums[idx++] = entry.getValue();
        }
        return new Info(coins, nums);
    }

    /**
     * 暴力递归
     */
    public static int coinsWay(int[] coins, int amount) {
        Info info = wordCount(coins);
        return func(info.coins, info.nums, 0, amount);
    }

    /**
     * 递归函数（暴力枚举）
     * coins[idx...n]所有的面值，在张数nums[]规定好的情况下，组成rest这些钱的方法数
     * @param coins 面值数组（正数且去重）
     * @param nums  张数数组（每种面值对应的张数）
     * @param idx   当前位置
     * @param rest  组成剩余的钱
     * @return 方法数
     */
    private static int func(int[] coins, int[] nums, int idx, int rest) {
        if (idx == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int num = 0; num * coins[idx] <= rest; num++) {
            if (num <= nums[idx]) {
                ways += func(coins, nums, idx + 1, rest - num * coins[idx]);
            }
        }
        return ways;
    }

    /**
     * 动态规划（山寨版）
     */
    public static int coinsWay2(int[] arr, int amount) {
        Info info = wordCount(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[n][0] = 1;
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= amount; rest++) {
                int ways = 0;
                for (int num = 0; num * coins[idx] <= rest; num++) {
                    if (num <= nums[idx]) {
                        ways += dp[idx + 1][rest - num * coins[idx]];
                    }
                }
                dp[idx][rest] = ways;
            }
        }
        return dp[0][amount];
    }

    /**
     * 动态规划（优化枚举行为）
     */
    public static int coinsWay3(int[] arr, int amount) {
        Info info = wordCount(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
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
                int more = rest - coins[idx] * (nums[idx] + 1);
                if (more >= 0) {    // 重复的部分不越界
                    dp[idx][rest] -= dp[idx + 1][more];
                }
            }
        }
        return dp[0][amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 1, 1, 2, 1, 2};
        int amount = 4;
        System.out.println(coinsWay(coins, amount));
        System.out.println(coinsWay2(coins, amount));
        System.out.println(coinsWay3(coins, amount));
    }
}
