package dp;

/**
 * https://leetcode.cn/problems/minimum-path-sum/
 * 最小路径和
 * @author HuanyuLee
 * @date 2023/6/2
 */
public class Code0064_MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = grid[n - 1][m - 1];
        for (int y = m - 2; y >= 0; y--) {
            dp[n - 1][y] = dp[n - 1][y + 1] + grid[n - 1][y];
        }
        for (int x = n - 2; x >= 0; x--) {
            dp[x][m - 1] = dp[x + 1][m - 1] + grid[x][m - 1];
        }
        for (int x = n - 2; x >= 0; x--) {
            for (int y = m - 2; y >= 0; y--) {
                dp[x][y] = Math.min(dp[x + 1][y], dp[x][y + 1]) + grid[x][y];
            }
        }
        return dp[0][0];
    }

    // 空间压缩
    public int minPathSum2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dp = new int[m];
        dp[m - 1] = grid[n - 1][m - 1];
        for (int y = m - 2; y >= 0; y--) {
            dp[y] = dp[y + 1] + grid[n - 1][y];
        }
        for (int x = n - 2; x >= 0; x--) {
            dp[m - 1] += grid[x][m - 1];
            for (int y = m - 2; y >= 0; y--) {
                dp[y] = Math.min(dp[y], dp[y + 1]) + grid[x][y];
            }
        }
        return dp[0];
    }
}
