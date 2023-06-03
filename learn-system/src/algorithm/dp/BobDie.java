package algorithm.dp;

/**
 * 醉汉问题
 * 给定5个参数: N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 * @author HuanyuLee
 * @date 2023/6/3
 */
public class BobDie {
    /**
     * 暴力递归
     */
    public static double liveProbability(int row, int col, int k, int n, int m) {
        return live(row, col, k, n, m) / Math.pow(4, k);
    }

    /**
     * 递归函数
     * Bob目前在(row,col)位置，每走完一步如果还在区域中就获得一个生存点数
     * 现在他还有rest步要走，返回走完这rest步之后总的生存点数
     * @param row  当前位置的横坐标
     * @param col  当前位置的纵坐标
     * @param rest 剩余的步数
     * @param n    区域宽度
     * @param m    区域长度
     * @return 生存点数
     */
    private static long live(int row, int col, int rest, int n, int m) {
        if (row < 0 || row == n || col < 0 || col == m) {   // 越界掉沟里了，死！
            return 0;
        }
        if (rest == 0) {    // 还在区域里且不能再走，获得一个生存点数
            return 1;
        }
        // 还在区域里，还有步数要走
        long up = live(row - 1, col, rest - 1, n, m);
        long down = live(row + 1, col, rest - 1, n, m);
        long left = live(row, col - 1, rest - 1, n, m);
        long right = live(row, col + 1, rest - 1, n, m);
        return up + down + left + right;
    }
}
