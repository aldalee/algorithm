package algorithm.class03_bitwise_operation;

import static algorithm.class03_bitwise_operation.Code04_KM.hash;

/**
 * 如果一个数组中有一种数出现K次，其他数都出现了M次，找到出现K次的数
 * 如果一个数组中有一种数没有出现K次，其他数都出现了M次，返回-1
 * @author HuanyuLee
 * @date 2022/9/30
 */
public class Code05_KM2 {
    public static int isKTimes(int[] arr, int k, int m) {
        // t[i] 代表num第i位置的1出现了几个
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i <= 31; i++) {
                t[i] += (num >> i) & 1; // num在i位置是1，累加
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((t[i] % m) == k) {   // 出现K次的数在第i位上是 1
                ans |= (1 << i);    // 利用或运算将第i位上的1设置进去
            }
        }
        // 单独验证 0 出现了几次
        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }
}
