package algorithm.dp.model;

import java.util.Arrays;

/**
 * 卡片博弈（范围上的尝试模型）
 * ============ 例题，感受从暴力递归到动态规划的过程 =============
 * 给定int[] cards，代表数值不同的纸牌，玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿，但每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明，请返回最后获胜者的分数。
 * 注意: cards[i]都是正整数
 * LeetCode近乎原题: https://leetcode.cn/problems/predict-the-winner/
 * @author HuanyuLee
 * @date 2023/5/30
 */
public class CardsInLine {
    /**
     * LeetCode
     */
    public boolean PredictTheWinner(int[] cards) {
        if (cards == null || cards.length == 0) {
            return true;
        }
        int n = cards.length;
        int[][] fmap = new int[n][n];
        int[][] smap = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(fmap[i], -1);
            Arrays.fill(smap[i], -1);
        }
        int firstPlayer = firstPlayer(cards, 0, cards.length - 1, fmap, smap);
        int secondPlayer = secondPlayer(cards, 0, cards.length - 1, fmap, smap);
        return firstPlayer >= secondPlayer;
    }


    /**
     * 纯暴力
     * 根据游戏规则，返回获胜者的分数
     */
    public static int winnerScore1(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        int firstPlayer = firstPlayer(cards, 0, cards.length - 1);
        int secondPlayer = secondPlayer(cards, 0, cards.length - 1);
        return Math.max(firstPlayer, secondPlayer);
    }

    // 在cards[l...r]上，返回先手获得的最好分数
    private static int firstPlayer(int[] cards, int l, int r) {
        if (l == r) {           // 只剩下一张牌，直接拿走
            return cards[l];
        }
        int p1 = cards[l] + secondPlayer(cards, l + 1, r);  // 我选择L位置的牌
        int p2 = cards[r] + secondPlayer(cards, l, r - 1);  // 我选择R位置的牌
        return Math.max(p1, p2);                            // 决策权在我手里
    }

    // 在cards[l...r]上，返回后手获得的最好分数
    private static int secondPlayer(int[] cards, int l, int r) {
        if (l == r) {           // 只剩下一张牌，没得选，只能被先手拿走
            return 0;
        }
        int p1 = firstPlayer(cards, l + 1, r);  // 先手拿走了L位置的牌
        int p2 = firstPlayer(cards, l, r - 1);  // 先手拿走了R位置的牌
        return Math.min(p1, p2);                  // 决策权不在我手里，只能被迫接受
    }


    /**
     * 记忆化搜索（傻缓存）
     */
    public static int winnerScore2(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        int n = cards.length;
        int[][] fmap = new int[n][n];
        int[][] smap = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(fmap[i], -1);
            Arrays.fill(smap[i], -1);
        }
        int firstPlayer = firstPlayer(cards, 0, cards.length - 1, fmap, smap);
        int secondPlayer = secondPlayer(cards, 0, cards.length - 1, fmap, smap);
        return Math.max(firstPlayer, secondPlayer);
    }

    // 在cards[l...r]上，返回先手获得的最好分数
    private static int firstPlayer(int[] cards, int l, int r, int[][] fmap, int[][] smap) {
        if (fmap[l][r] != -1) {
            return fmap[l][r];
        }
        int ans = 0;
        if (l == r) {
            ans = cards[l];
        } else {
            int p1 = cards[l] + secondPlayer(cards, l + 1, r, fmap, smap);
            int p2 = cards[r] + secondPlayer(cards, l, r - 1, fmap, smap);
            ans = Math.max(p1, p2);
        }
        fmap[l][r] = ans;
        return ans;
    }

    // 在cards[l...r]上，返回后手获得的最好分数
    private static int secondPlayer(int[] cards, int l, int r, int[][] fmap, int[][] smap) {
        if (smap[l][r] != -1) {
            return smap[l][r];
        }
        int ans = 0;
        if (l != r) {
            int p1 = firstPlayer(cards, l + 1, r, fmap, smap);
            int p2 = firstPlayer(cards, l, r - 1, fmap, smap);
            ans = Math.min(p1, p2);
        }
        smap[l][r] = ans;
        return ans;
    }


    /**
     * 表结构依赖的动态规划
     */
    public static int winnerScore3(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        int n = cards.length;
        int[][] fdp = new int[n][n];
        int[][] sdp = new int[n][n];
        for (int i = 0; i < n; i++) {
            fdp[i][i] = cards[i];
        }
        int l, r;
        for (int col = 1; col < n; col++) {
            l = 0;
            r = col;
            while (r < n) {
                fdp[l][r] = Math.max(cards[l] + sdp[l + 1][r], cards[r] + sdp[l][r - 1]);
                sdp[l][r] = Math.min(fdp[l + 1][r], fdp[l][r - 1]);
                l++;
                r++;
            }
        }
        return Math.max(fdp[0][n - 1], sdp[0][n - 1]);
    }


    public static void main(String[] args) {
        int[] cards = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(winnerScore1(cards));
        System.out.println(winnerScore2(cards));
    }
}
