package algorithm.recursive.trial;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 * @author HuanyuLee
 * @date 2023/5/30
 */
public class FullPermutation {
    // 打印一个字符串的全部排列
    public static List<String> printPermutation(String string) {
        List<String> ans = new ArrayList<>();
        if (string == null || string.length() == 0) {
            return ans;
        }
        char[] str = string.toCharArray();
        process(str, 0, ans);
        return ans;
    }

    private static void process(char[] str, int idx, List<String> ans) {
        if (idx == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        for (int i = idx; i < str.length; i++) {
            swap(str, idx, i);
            process(str, idx + 1, ans);
            swap(str, idx, i);      // 恢复现场
        }
    }

    // 打印一个字符串的全部排列（去重）
    public static List<String> printDistinctPermutation(String string) {
        List<String> ans = new ArrayList<>();
        if (string == null || string.length() == 0) {
            return ans;
        }
        char[] str = string.toCharArray();
        // fuc(str, 0, ans);
        boolean[] visit = new boolean[256];
        fuc(str, 0, ans, visit);
        return ans;
    }

    private static void fuc(char[] str, int idx, List<String> ans) {
        if (idx == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        boolean[] visit = new boolean[256];
        for (int i = idx; i < str.length; i++) {
            if (!visit[str[i]]) {         // 分支限界
                visit[str[i]] = true;
                swap(str, idx, i);
                fuc(str, idx + 1, ans);
                swap(str, idx, i);
            }
        }
    }

    // 下面的代码是错误的！visit不能共享！
    private static void fuc(char[] str, int idx, List<String> ans, boolean[] visit) {
        if (idx == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        for (int i = idx; i < str.length; i++) {
            if (!visit[str[i]]) {         // 分支限界
                visit[str[i]] = true;
                swap(str, idx, i);
                fuc(str, idx + 1, ans, visit);
                swap(str, idx, i);
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public static void main(String[] args) {
        String str = "a44";
        // String str = "aaab";
        List<String> permutation = printPermutation(str);
        System.out.println(permutation);
        System.out.println(permutation.size()); // n！

        List<String> permutation2 = printDistinctPermutation(str);
        System.out.println(permutation2);
    }
}
