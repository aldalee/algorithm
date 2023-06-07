package recall;

/**
 * N皇后问题
 * @author HuanyuLee
 * @date 2023/6/6
 */
public class NQueens {
    /**
     * 暴力递归（尝试）
     */
    public static int totalNQueens(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return func(0, record, n);
    }

    /**
     * 递归函数，在第x行上放皇后，所有列都尝试
     * 但是必须保证之前所有的皇后都不能相互攻击
     * @param x      当前的皇后来到第x行 [0,n)
     * @param record 之前皇后的摆放信息，
     *               record[x] = y 表示第x个皇后放在了(x,y)位置
     * @param n      一共的行数
     * @return 从x行之后一共的合法摆放方法数
     */
    private static int func(int x, int[] record, int n) {
        if (x == n) {
            return 1;
        }
        int res = 0;
        // 第x行的皇后放在那一列（y）呢？全部尝试一遍
        for (int y = 0; y < n; y++) {
            if (!isHit(record, x, y)) {
                record[x] = y;
                res += func(x + 1, record, n);
            }
        }
        return res;
    }

    /**
     * 检查[0, x-1]行之间的皇后是否相互攻击
     * @param record 之前皇后的摆放信息
     * @param x      当前的皇后来到第x行
     * @param y      当前的皇后来到第y列
     * @return 皇后是否相互攻击
     */
    private static boolean isHit(int[] record, int x, int y) {
        for (int k = 0; k < x; k++) {
            if (y == record[k] || Math.abs(record[k] - y) == Math.abs(x - k)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用位运算，但不要超过32
     */
    public static int totalNQueens2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return func(limit, 0, 0, 0);
    }

    /**
     * 使用位运算，不使用数本身的值，只用它的状态
     * @param limit       n皇后的初始状态，
     *                    比如 n = 7，limit = 0...0 1 1 1 1 1 1 1
     * @param colLim      之前皇后的列影响
     * @param leftDiaLim  之前皇后的左下角影响
     * @param rightDiaLim 之前皇后的右下角影响
     * @return 合法摆放方法数
     */
    private static int func(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // pos中所有是[1]的位置，是可以尝试摆放皇后的位置
        int pos = (~(colLim | leftDiaLim | rightDiaLim)) & limit;
        int mostRightOne = 0;
        int res = 0;
        // 尝试所有可能的[1]，将结果数返回
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);    // 提取pos中最右侧的[1]
            pos = pos - mostRightOne;
            res += func(limit,
                    colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }


    public static void main(String[] args) {
        int n = 14;
        System.out.println(totalNQueens(n));
        System.out.println(totalNQueens2(n));
    }
}
