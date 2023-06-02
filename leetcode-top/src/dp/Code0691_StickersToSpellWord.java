package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/stickers-to-spell-word/
 * 贴纸拼词
 * @author HuanyuLee
 * @date 2023/5/31
 */
public class Code0691_StickersToSpellWord {
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        // 用词频表替代贴纸数组（关键优化）
        int[][] counts = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (char ch : stickers[i].toCharArray()) {
                counts[i][ch - 'a']++;
            }
        }
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = func(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int func(int[][] stickers, String target, Map<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        char[] tg = target.toCharArray();
        int[] tc = new int[26];
        for (char ch : tg) {
            tc[ch - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[tg[0] - 'a'] <= 0) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < tc.length; i++) {
                if (tc[i] > 0) {
                    int num = tc[i] - sticker[i];
                    builder.append(String.valueOf((char) (i + 'a')).repeat(Math.max(0, num)));
                }
            }
            String rest = builder.toString();
            min = Math.min(min, func(stickers, rest, dp));
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, ans);
        return ans;
    }
}
