package algorithm.fibonacci;

/**
 * 由斐波那契数列讲述矩阵快速幂技巧
 * https://leetcode.cn/problems/fibonacci-number
 * @author HuanyuLee
 * @date 2023/8/29
 */
public class FibonacciProblem {
    // O(2^n)
    public static int fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // O(n)
    public static int f(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0;  // f(n-2)
        int q = 0;  // f(n-1)
        int r = 1;  // f(n)
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    // O(logn)
    public static int fib(int n) {
        int[][] M = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(M, n);
        return res[1][0];
    }

    private static int[][] matrixPower(int[][] matrix, int n) {
        int[][] res = new int[matrix.length][matrix.length];
        // 初始化为单位矩阵
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = matrix;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = multiply(res, t);
            }
            t = multiply(t, t);
            n >>= 1;
        }
        return res;
    }

    // A[n*k] B[k*m]
    private static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int k = A[0].length;
        int m = B[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int x = 0; x < k; x++) {
                    res[i][j] += A[i][x] * B[x][j];
                }
            }
        }
        return res;
    }
}
