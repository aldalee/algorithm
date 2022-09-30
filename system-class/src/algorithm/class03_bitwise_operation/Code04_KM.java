package algorithm.class03_bitwise_operation;

import java.util.Arrays;
import java.util.HashMap;

import static algorithm.class03_bitwise_operation.Code05_KM2.isKTimes;
import static util.Utils.generateKMRandomArray;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次，找到出现K次的数
 * @author HuanyuLee
 * @date 2022/9/30
 */
public class Code04_KM {
    // 保证arr中，只有一种数出现了K次，其他数都出现了M次
    public static int onlyKTimes(int[] arr, int k, int m) {
        // t[i] 代表num第i位置的1出现了几个
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i <= 31; i++) {
                t[i] += (num >> i) & 1; // num在i位置是1，累加
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((t[i] % m) != 0) {   // 出现K次的数在第i位上是 1
                ans |= (1 << i);    // 利用或运算将第i位上的1设置进去
            }
        }
        return ans;
    }

    // for test 哈希表进行词频统计
    public static int hash(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // System.out.println(map);
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int maxKinds = 9;
        int maxValue = 200;
        int testTimes = 10000;
        int max = 9;
        double p = 0.5;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int a = (int) (Math.random() * max) + 1;    // a ∈ [1,9]
            int b = (int) (Math.random() * max) + 1;    // b ∈ [1,9]
            // 保证 M > 1,K < M
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr = generateKMRandomArray(maxKinds, maxValue, k, p, m);
            int ans1 = hash(arr, k, m);
            // int ans2 = onlyKTimes(arr, k, m);
            int ans2 = isKTimes(arr, k, m);
            if (ans1 != ans2) {
                System.out.println("k = " + k);
                System.out.println("m = " + m);
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
