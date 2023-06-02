package algorithm.dp.space_compression;

/**
 * https://leetcode.cn/problems/minimum-path-sum/
 * 最小路径和
 * ============ 空间压缩 =============
 * 1、滚动数组（准备两个数组）
 * 2、自我更新（准备一个数组）
 * @author HuanyuLee
 * @date 2023/6/2
 */
public class MinimumPathSum {
    /**
     * 暴力递归
     */
    public static int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        return func(grid, 0, 0, n - 1, m - 1);
    }

    /**
     * 从[x,y] -> [X,Y]的最小路径和
     * @param grid 网格
     * @param x    当前位置的横坐标
     * @param y    当前位置的纵坐标
     * @param X    目标位置的横坐标
     * @param Y    目标位置的纵坐标
     * @return 最小路径和
     */
    private static int func(int[][] grid, int x, int y, int X, int Y) {
        if (x > X || y > Y) {
            return 0;
        }
        if (x == X) {
            return func(grid, X, y + 1, X, Y) + grid[X][y];
        }
        if (y == Y) {
            return func(grid, x + 1, Y, X, Y) + grid[x][Y];
        }
        int p1 = func(grid, x + 1, y, X, Y) + grid[x][y];   // 向下走
        int p2 = func(grid, x, y + 1, X, Y) + grid[x][y];   // 向右走
        return Math.min(p1, p2);
    }

    /**
     * 动态规划
     */
    public static int minPathSum2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];     // 从[x,y] -> [右下角]的最小路径和
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

    /**
     * 动态规划（另一个版本）
     */
    public static int minPathSum3(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }


    /**
     * 动态规划（空间压缩）
     */
    public static int minPathSum4(int[][] grid) {
        int n = grid.length;        // row
        int m = grid[0].length;     // col
        int[] dp = new int[m];
        dp[0] = grid[0][0];
        for (int j = 1; j < m; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        for (int i = 1; i < n; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[m - 1];
    }


    public static void main(String[] args) {
        // int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        // int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid = {
                {3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3},
                {0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2},
                {8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9},
                {1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7},
                {8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8},
                {4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9},
                {6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1},
                {8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3},
                {9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3},
                {0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8},
                {4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3},
                {2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3},
                {0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3},
                {0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5},
                {6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2},
                {7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0},
                {3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0},
                {5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7}};
        System.out.println(minPathSum(grid));
        System.out.println(minPathSum2(grid));
        System.out.println(minPathSum3(grid));
        System.out.println(minPathSum4(grid));
    }
}
