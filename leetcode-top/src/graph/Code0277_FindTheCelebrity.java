package graph;

/**
 * https://leetcode.cn/problems/find-the-celebrity/
 * 搜寻名人
 * @author HuanyuLee
 * @date 2023/7/15
 */
public class Code0277_FindTheCelebrity {
    /**
     * The knows API is already defined for you.
     * boolean knows (int a, int b);
     */
    private boolean knows(int a, int b) {
        return true;
    }

    public int findCelebrity(int n) {
        int res = 0;
        for (int i = 1; i < n; i++) {   // search
            if (knows(res, i)) {
                res = i;
            }
        }

        for (int i = 0; i < n; i++) {   // verify
            if (i == res) continue;
            if (knows(res, i) || !knows(i, res)) {
                return -1;
            }
        }
        return res;
    }
}
