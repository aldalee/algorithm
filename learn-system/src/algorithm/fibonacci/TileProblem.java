package algorithm.fibonacci;

/**
 * 铺瓷砖问题
 * 用1*2的瓷砖，把N*2的区域填满，返回铺瓷砖的方法数
 * @author HuanyuLee
 * @date 2023/8/31
 */
public class TileProblem {
    public static int ways(int n) {
        return f(n);
    }

    public static int f(int n) {
        if (n <= 2) {
            return n;
        }
        return f(n - 1) + f(n - 2);
    }

    public static int tileFill(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println(ways(n));
        System.out.println(tileFill(n));
    }
}
