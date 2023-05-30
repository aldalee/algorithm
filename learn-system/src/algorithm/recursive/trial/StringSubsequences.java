package algorithm.recursive.trial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印一个字符串的全部子序列
 * @author HuanyuLee
 * @date 2023/5/30
 */
public class StringSubsequences {
    // 打印一个字符串的全部子序列
    public static List<String> printSub(String string) {
        char[] str = string.toCharArray();
        List<String> ans = new ArrayList<>();
        process(str, 0, "", ans);
        return ans;
    }

    /**
     * 递归函数，生成字符串的全部子序列
     * @param str  输入字符串的字符数组表示
     * @param idx  当前处理的字符索引
     * @param path 当前已生成的子序列
     * @param ans  保存所有子序列的列表
     */
    private static void process(char[] str, int idx, String path, List<String> ans) {
        if (idx == str.length) {
            ans.add(path);
            return;
        }
        process(str, idx + 1, path, ans);                    // 不要idx位置的字符
        process(str, idx + 1, path + str[idx], ans);    // 要了idx位置的字符
    }

    // 打印一个字符串的全部子序列（去重）
    public static List<String> printDistinctSub(String string) {
        char[] str = string.toCharArray();
        Set<String> set = new HashSet<>();          // HashSet去重
        process(str, 0, "", set);
        return new ArrayList<>(set);
    }

    private static void process(char[] str, int idx, String path, Set<String> set) {
        if (idx == str.length) {
            set.add(path);
            return;
        }
        process(str, idx + 1, path, set);                    // 不要idx位置的字符
        process(str, idx + 1, path + str[idx], set);    // 要了idx位置的字符
    }

    public static void main(String[] args) {
        String str = "accc";
        List<String> sub = printSub(str);
        System.out.println(sub);

        List<String> dSub = printDistinctSub(str);
        System.out.println(dSub);
    }
}
