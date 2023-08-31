package algorithm.fibonacci;

import java.util.Scanner;

/**
 * 母牛的故事
 * https://www.nowcoder.com/pat/2/problem/255
 * @author HuanyuLee
 * @date 2023/8/30
 */
public class TheStoryOfCow {
    // 暴力解
    public static int f(int n) {
        if (n < 5) {
            return n;
        }
        return f(n - 1) + f(n - 3);
    }

    // 矩阵快速幂
    public static int cows(int n) {
        int[][] M = {
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        int[][] res = matrixPower(M, n - 1);
        return res[0][2] * 3 + res[1][2] * 2 + res[2][2];
    }

    private static int[][] matrixPower(int[][] matrix, int n) {
        int[][] res = new int[matrix.length][matrix.length];
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

    private static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int k = A[0].length;
        int m = B[0].length;
        int[][] C = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int c = 0; c < k; c++) {
                    C[i][j] += A[i][c] * B[c][j];
                }
            }
        }
        return C;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            System.out.println(cows(n));
        }
        in.close();
    }
}
