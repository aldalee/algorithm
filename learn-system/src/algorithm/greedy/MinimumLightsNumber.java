package algorithm.greedy;

import java.util.HashSet;

/**
 * 灯泡点亮问题
 * 题目描述:
 *    给定一个字符串str，只由'X'和'.'两种字符构成。
 *    'X'表示墙，不能放灯，也不需要点亮
 *    '.'表示居民点，可以放灯，需要点亮
 *    如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 * @author HuanyuLee
 * @date 2023/5/24
 */
public class MinimumLightsNumber {
    public static int bruteForce(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }
    /**
     * 递归函数，计算放置灯泡的最小数量。
     * 解释:
     * str[idx....]位置，自由选择放灯还是不放灯
     * str[0..idx-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
     * 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
     * @param str    道路字符串的字符数组表示
     * @param idx    当前处理的索引位置
     * @param lights 存储已放置灯泡的位置集合
     * @return 最少需要的灯泡数量
     */
    public static int process(char[] str, int idx, HashSet<Integer> lights) {
        if (idx == str.length) {        // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') {    // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {                        // str还没结束
            int no = process(str, idx + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[idx] == '.') {
                lights.add(idx);
                yes = process(str, idx + 1, lights);
                lights.remove(idx);
            }
            return Math.min(no, yes);
        }
    }

    /**
     * 贪心策略
     */
    public static int minLights(String road) {
        char[] str = road.toCharArray();
        int light = 0;
        int idx = 0;
        while (idx < str.length) {
            if (str[idx] == 'X') {      // 如果idx位置是墙"X"，不用加灯，跳过
                idx++;
            } else {                    // 如果idx位置是灯"."，需对加灯位置进行讨论
                light++;                // 下面所有的情况都需要加灯，只是加的位置不同
                if (idx + 1 >= str.length) break;   // 边界检查，防止越界
                if (str[idx + 1] == 'X') {          // 如果 idx+1 位置是墙"X"，idx 位置放灯
                    idx = idx + 2;
                } else {                            // 如果 idx+1 位置是灯"."，idx+1 位置放灯
                    idx = idx + 3;
                }
            }
        }
        return light;
    }

    /**
     * 两个X之间，数一下.的数量，然后除以3，向上取整，把灯数累加
     */
    public static int minLight(String road) {
        char[] str = road.toCharArray();
        int light = 0;
        int cur = 0;
        for (var c : str) {
            if (c == 'X') {
                light += (cur + 2) / 3;
                cur = 0;
            } else {
                cur++;
            }
        }
        light += (cur + 2) / 3;
        return light;
    }
}
