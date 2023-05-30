package dp;

/**
 * https://leetcode.cn/problems/predict-the-winner/
 * 预测赢家
 * TODO: 如何对两张dp表进行空间优化？实际只会用到上三角区域
 * @author HuanyuLee
 * @date 2023/5/30
 */
public class Code0486_PredictTheWinner {
    public boolean PredictTheWinner(int[] cards) {
        if (cards == null || cards.length == 0) {
            return true;
        }
        int n = cards.length;
        int[][] fdp = new int[n][n];
        int[][] sdp = new int[n][n];
        for (int i = 0; i < n; i++) {
            fdp[i][i] = cards[i];
        }
        for (int col = 1; col < n; col++) {
            int l = 0;
            int r = col;
            while (r < n) {
                fdp[l][r] = Math.max(cards[l] + sdp[l + 1][r], cards[r] + sdp[l][r - 1]);
                sdp[l][r] = Math.min(fdp[l + 1][r], fdp[l][r - 1]);
                l++;
                r++;
            }
        }
        return fdp[0][n - 1] >= sdp[0][n - 1];
    }

    // ChatGPT
    // TODO: 没看懂
    public boolean PredictTheWinner2(int[] cards) {
        if (cards == null || cards.length == 0) {
            return true;
        }
        int n = cards.length;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = cards[i];
            for (int j = i + 1; j < n; j++) {
                int pickLeft = cards[i] - dp[j];
                int pickRight = cards[j] - dp[j - 1];
                dp[j] = Math.max(pickLeft, pickRight);
            }
        }
        return dp[n - 1] >= 0;
    }
}
