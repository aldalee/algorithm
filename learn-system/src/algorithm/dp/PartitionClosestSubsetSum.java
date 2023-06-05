package algorithm.dp;

import java.util.Arrays;

/**
 * 数组分割成累加和尽量相等的两个集合（0-1背包问题）
 * 给定一个正数数组nums，请把nums中所有的数分成两个集合，个数随意
 * 尽量让两个集合的累加和接近，返回最接近的情况下，较小集合的累加和
 * @author HuanyuLee
 * @date 2023/6/5
 */
public class PartitionClosestSubsetSum {
    /**
     * 暴力递归
     */
    public static int partition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        return func(nums, 0, sum / 2);
    }

    /**
     * 递归函数
     * nums[idx...]可以自由选择，在累加和尽量接近sum但<=sum的情况下
     * 最接近的累加和是多少
     * @param nums 正数数组
     * @param idx  当前位置
     * @param sum  剩余的累加和
     * @return 最接近rest的累加和
     */
    private static int func(int[] nums, int idx, int sum) {
        if (idx == nums.length) {
            return 0;
        }
        int p1 = func(nums, idx + 1, sum);  // 不选择当前位置
        int p2 = 0;                             // 选择当前位置
        if (nums[idx] <= sum) {
            p2 = nums[idx] + func(nums, idx + 1, sum - nums[idx]);
        }
        return Math.max(p1, p2);
    }

    /**
     * 动态规划
     */
    public static int partition2(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int n = nums.length;
        int m = sum / 2;
        int[][] dp = new int[n + 1][m + 1];
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= m; rest++) {
                int p1 = dp[idx + 1][rest];
                int p2 = 0;
                if (nums[idx] <= rest) {
                    p2 = nums[idx] + dp[idx + 1][rest - nums[idx]];
                }
                dp[idx][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][m];
    }


    public static void main(String[] args) {
        int[] nums = {100, 2, 5, 6};
        System.out.println(partition(nums));
        System.out.println(partition2(nums));
    }
}
