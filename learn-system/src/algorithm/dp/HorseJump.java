package algorithm.dp;

/**
 * 跳马问题
 * 请同学们自行搜索或者想象一个象棋的棋盘
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个参数 x，y，k
 * 返回马从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 * @author HuanyuLee
 * @date 2023/6/1
 */
public class HorseJump {
    /**
     * 暴力递归
     */
    public static int jump(int x, int y, int k) {
        return func(0, 0, k, x, y);
    }

    /**
     * @param x    马当前位置的横坐标 x ∈ [0,9]
     * @param y    马当前位置的纵坐标 y ∈ [0,8]
     * @param rest 剩余的步数
     * @param X    马目标位置的横坐标
     * @param Y    马目标位置的纵坐标
     * @return 多少种跳法
     */
    public static int func(int x, int y, int rest, int X, int Y) {
        if (rest == 0) {
            return (x == X && y == Y) ? 1 : 0;
        }
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        int p1 = func(x - 1, y + 2, rest - 1, X, Y);
        int p2 = func(x + 1, y + 2, rest - 1, X, Y);
        int p3 = func(x - 2, y + 1, rest - 1, X, Y);
        int p4 = func(x + 2, y + 1, rest - 1, X, Y);
        int p5 = func(x - 2, y - 1, rest - 1, X, Y);
        int p6 = func(x + 2, y - 1, rest - 1, X, Y);
        int p7 = func(x - 1, y - 2, rest - 1, X, Y);
        int p8 = func(x + 1, y - 2, rest - 1, X, Y);
        return p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
    }


    /**
     * 动态规划
     */
    public static int jump2(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[x][y][0] = 1;
        for (int r = 1; r <= k; r++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][r] = pick(dp, i - 1, j + 2, r - 1);
                    dp[i][j][r] += pick(dp, i + 1, j + 2, r - 1);
                    dp[i][j][r] += pick(dp, i - 2, j + 1, r - 1);
                    dp[i][j][r] += pick(dp, i + 2, j + 1, r - 1);
                    dp[i][j][r] += pick(dp, i - 2, j - 1, r - 1);
                    dp[i][j][r] += pick(dp, i + 2, j - 1, r - 1);
                    dp[i][j][r] += pick(dp, i - 1, j - 2, r - 1);
                    dp[i][j][r] += pick(dp, i + 1, j - 2, r - 1);
                }
            }
        }
        return dp[0][0][k];
    }

    private static int pick(int[][][] dp, int x, int y, int r) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][r];
    }


    public static void main(String[] args) {
        int x = 7, y = 7, k = 10;
        System.out.println(jump(x, y, k));
        System.out.println(jump2(x, y, k));
    }
}
