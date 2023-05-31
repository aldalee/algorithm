package algorithm.dp;

/**
 * 0-1背包问题
 * @author HuanyuLee
 * @date 2023/5/31
 */
public class Knapsack {
    /**
     * 暴力递归
     * @param w   货物重量
     * @param v   货物价值
     * @param bag 背包容量
     * @return 最大价值
     */
    public static int knapsack(int[] w, int[] v, int bag) {
        return maxValue(w, v, 0, bag);
        // return f(w, v, 0, bag);
    }

    /**
     * 当前考虑到idx号货物，[idx...n]所有的货物可以自由选择
     * 做的选择不超过背包剩余的容量rest，返回最大价值
     * @param w    货物重量
     * @param v    货物价值
     * @param idx  当前第idx号货物
     * @param rest 背包剩余的容量
     * @return 最大价值
     */
    private static int maxValue(int[] w, int[] v, int idx, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (idx == w.length) {
            return 0;
        }
        int p1 = maxValue(w, v, idx + 1, rest);
        int p2 = 0;
        int next = maxValue(w, v, idx + 1, rest - w[idx]);
        if (next != -1) {
            p2 = v[idx] + next;
        }
        return Math.max(p1, p2);
    }

    private static int f(int[] w, int[] v, int idx, int rest) {
        if (idx == w.length) {
            return 0;
        }
        int p1 = 0, p2 = 0;
        if (rest < w[idx]) {
            p1 = f(w, v, idx + 1, rest);
        } else {
            p2 = Math.max(f(w, v, idx + 1, rest), f(w, v, idx + 1, rest - w[idx]) + v[idx]);
        }
        return Math.max(p1, p2);
    }

    /**
     * 动态规划
     * @param w   货物重量
     * @param v   货物价值
     * @param bag 背包容量
     * @return 最大价值
     */
    public static int dpKnapsack(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || bag < 0) {
            return 0;
        }
        int[][] dp = new int[w.length + 1][bag + 1];
        for (int idx = w.length - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[idx + 1][rest];
                int p2 = 0;
                int next = rest - w[idx] < 0 ? -1 : dp[idx + 1][rest - w[idx]];
                if (next != -1) {
                    p2 = next + v[idx];
                }
                dp[idx][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    /**
     * 计算01背包问题的结果（NC145 01背包）
     * @param V  int整型 背包的体积
     * @param n  int整型 物品的个数
     * @param vw int整型二维数组 第一维度为n,第二维度为2的二维数组,vw[i][0],vw[i][1]分别描述i+1个物品的vi,wi
     * @return int整型
     */
    public int knapsack(int V, int n, int[][] vw) {
        int[][] dp = new int[n + 1][V + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= V; rest++) {
                int p1 = dp[i + 1][rest];
                int p2 = 0;
                int next = rest - vw[i][0] < 0 ? -1 : dp[i + 1][rest - vw[i][0]];
                if (next != -1) {
                    p2 = next + vw[i][1];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][V];
    }

    public static void main(String[] args) {
        int[] w = {1, 10};
        int[] v = {3, 4};
        int bag = 10;
        System.out.println(knapsack(w, v, bag));
        System.out.println(dpKnapsack(w, v, bag));
    }
}
