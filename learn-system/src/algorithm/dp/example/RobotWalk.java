package algorithm.dp.example;

import java.util.Arrays;

/**
 * 机器人走步
 * ============ 例题，感受从暴力递归到动态规划的过程 =============
 * 假设有排成一行的N个位置，记为1~N（N>=2）
 * 开始时机器人在其中的M位置上（M ∈ [1,N]）
 * 机器人的移动规则:
 *    1、如果机器人来到1位置，那么下一步只能往右来到2位置；
 *    2、如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 *    3、如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置（P ∈ [1,N]）的方法有多少种？
 * 给定四个参数 N、M、K、P，返回方法数。
 * @author HuanyuLee
 * @date 2023/5/30
 */
public class RobotWalk {
    /**
     * 暴力递归
     * @param N 一共N个位置
     * @param M 机器人起始位置
     * @param P 机器人目标位置
     * @param K 规定的机器人的步数
     * @return 机器人走步的方法数
     */
    public static int ways1(int N, int M, int P, int K) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return 0;
        }
        return move(M, K, P, N);
    }

    /**
     * 递归函数
     * 机器人从cur出发走过rest步后、最终停在aim位置的方法数
     * @param cur  机器人当前的位置
     * @param rest 机器人剩余的步数
     * @param aim  机器人要去的目标位置
     * @param n    一共N个位置
     * @return 方法数
     */
    private static int move(int cur, int rest, int aim, int n) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {     // 当前来到[1]位置，只能走到[2]位置
            return move(2, rest - 1, aim, n);
        }
        if (cur == n) {     // 当前来到[N]位置，只能走到[N-1]位置
            return move(n - 1, rest - 1, aim, n);
        }
        // 当前来到中间位置，既可以向左移动，也可以向右移动
        return move(cur - 1, rest - 1, aim, n) + move(cur + 1, rest - 1, aim, n);
    }

    /**
     * 记忆化搜索
     * @param N 一共N个位置
     * @param M 机器人起始位置
     * @param P 机器人目标位置
     * @param K 规定的机器人的步数
     * @return 机器人走步的方法数
     */
    public static int ways2(int N, int M, int P, int K) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return 0;
        }
        // dp就是缓存表
        // dp[cur][rest] == -1  =>  move(cur,rest)之前没算过
        // dp[cur][rest] != -1  =>  move(cur,rest)之前算过了
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }
        return move(M, K, P, N, dp);
    }

    private static int move(int cur, int rest, int aim, int n, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = move(2, rest - 1, aim, n, dp);
        } else if (cur == n) {
            ans = move(n - 1, rest - 1, aim, n, dp);
        } else {
            ans = move(cur - 1, rest - 1, aim, n, dp) + move(cur + 1, rest - 1, aim, n, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /**
     * 状态转移，完成动态规化
     * @param N 一共N个位置
     * @param M 机器人起始位置
     * @param P 机器人目标位置
     * @param K 规定的机器人的步数
     * @return 机器人走步的方法数
     */
    public static int ways3(int N, int M, int P, int K) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[P][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[M][K];
    }


    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }
}
