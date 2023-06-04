package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/stickers-to-spell-word/
 * 贴纸拼词
 * ============ 例题，感受从暴力递归到动态规划的过程 =============
 * 没有表结构依赖的dp，怎么办？=> 傻缓存！！！
 * @author HuanyuLee
 * @date 2023/5/31
 */
public class StickersToSpellWord {
    /**
     * 暴力递归
     */
    public static int minStickers(String[] stickers, String target) {
        int ans = func(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int func(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = minus(target, first);
            if (rest.length() != target.length()) {
                min = Math.min(min, func(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String minus(String target, String cur) {
        char[] s1 = target.toCharArray();
        char[] s2 = cur.toCharArray();
        int[] count = new int[26];
        for (char ch : s1) {
            count[ch - 'a']++;
        }
        for (char ch : s2) {
            count[ch - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                builder.append(String.valueOf((char) (i + 'a')).repeat(Math.max(0, count[i])));
            }
        }
        return builder.toString();
    }

    /**
     * 暴力递归（优化）
     */
    public static int minStickers2(String[] stickers, String target) {
        int n = stickers.length;
        // 用词频表替代贴纸数组（关键优化）
        int[][] counts = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (char ch : stickers[i].toCharArray()) {
                counts[i][ch - 'a']++;
            }
        }
        int ans = func(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int func(int[][] stickers, String target) {
        if (target.length() == 0) {
            return 0;
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
            min = Math.min(min, func(stickers, rest));
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    /**
     * 记忆化搜索（傻缓存）
     */
    public static int minStickers3(String[] stickers, String target) {
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

    private static int func(int[][] stickers, String target, Map<String, Integer> dp) {
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

    public static void main(String[] args) {
        // String[] stickers = {"with", "example", "science"};
        // String target = "thehat";
        String[] stickers = {"control", "heart", "interest", "stream", "sentence", "soil", "wonder", "them", "month",
                "slip", "table", "miss", "boat", "speak", "figure", "no", "perhaps", "twenty", "throw", "rich",
                "capital", "save", "method", "store", "meant", "life", "oil", "string", "song", "food", "am", "who",
                "fat", "if", "put", "path", "come", "grow", "box", "great", "word", "object", "stead", "common",
                "fresh", "the", "operate", "where", "road", "mean"};
        String target = "stoodcrease";

        System.out.println(minStickers(stickers, target));
        System.out.println(minStickers2(stickers, target));
        System.out.println(minStickers3(stickers, target));
    }
}
