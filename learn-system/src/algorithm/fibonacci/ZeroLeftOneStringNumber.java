package algorithm.fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 达标的字符串
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 * 如果某个字符串，任何0字符的左边都有1紧挨着，认为这个字符串达标
 * 返回有多少达标的字符串
 * @author HuanyuLee
 * @date 2023/8/31
 */
public class ZeroLeftOneStringNumber {
    // 暴力解 观察法发现规律: F(n) = F(n-1) + F(n-2)
    public static List<String> generateCombination(int n) {
        List<String> res = new ArrayList<>();
        char[] path = new char[n];
        f(path, 0, res);
        return res;
    }

    // 字符串只有0和1组成，指定长度n，得到所有的组合情况
    public static void f(char[] path, int idx, List<String> res) {
        if (idx == path.length) {
            if (isValid(path)) {
                res.add(String.valueOf(path));
            }
        } else {
            path[idx] = '0';
            f(path, idx + 1, res);
            path[idx] = '1';
            f(path, idx + 1, res);
        }
    }

    // 0的左边必须是1，1本身是合法的
    public static boolean isValid(char[] path) {
        if (path[0] == '0') {
            return false;
        }
        for (int i = 1; i < path.length; i++) {
            if (path[i] == '0' && path[i - 1] == '0') {
                return false;
            }
        }
        return true;
    }

    // 范围尝试模型 得到递推方程: F(n) = F(n-1) + F(n-2)
    public static int getNumber(int n) {
        return F(n - 1);
    }

    // 左边必须是1，还剩余长度为x要填
    public static int F(int x) {
        if (x == 0) {
            return 1;
        }
        if (x == 1) {
            return 2;
        }
        return F(x - 1) + F(x - 2);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            System.out.println("n = " + n);
            List<String> res = generateCombination(n);
            System.out.println("res = " + res);
            System.out.println("sum = " + res.size());
            System.out.println(getNumber(n));
            System.out.println("--- --- --- --- ---");
        }
        in.close();
    }
}
