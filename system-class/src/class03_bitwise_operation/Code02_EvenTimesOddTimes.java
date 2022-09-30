package class03_bitwise_operation;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author HuanyuLee
 * @date 2022/9/29
 */
public class Code02_EvenTimesOddTimes {
    // arr[] 中只有一种数出现奇数次，其余数都出现偶数次
    // 找到这种出现奇数次的数并打印
    // 例如：arr = [4, 3, 4, 2, 4, 3, 1, 2, 1, 1, 1, 3, 3]
    // 应该打印 4
    public static void printOddTimesNumber(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        System.out.println(eor);
    }

    public static void printOddTimesTwoNumber(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        // 提取出eor最右侧的1（二进制）
        int rightOne = eor & (-eor);
        int eor2 = 0;
        for (int j : arr) {
            if ((j & rightOne) != 0) {
                eor2 ^= j;
            }
        }
        System.out.println(eor2 + " " + (eor2 ^ eor));
    }

    // 保证arr中，只有一种数出现了K次，其他数都出现了M次
    public static int onlyKTimes(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i <= 31; i++) {
                // if (((num >> i) & 1) != 0) { // num在i位置是1
                //     t[i]++;
                // }
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((t[i] % m) != 0) {   // 在第i位上有 1
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static int hash(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    public static int[] randomArray(int maxKinds, int maxValue, int k, int m) {

        return null;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 4, 2, 4, 3, 1, 2, 1, 1, 1, 3, 3};
        int[] arr2 = {-1, 6, -1, 6, -1, 3, 3, 3, 3, 3, 4, 4, 5, 5};
        printOddTimesNumber(arr);
        printOddTimesTwoNumber(arr2);
        int maxKinds = 9;
        int maxValue = 200;
        int testTimes = 100000;
        int max = 9;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int a = (int) (Math.random() * max) + 1;    // a ∈ [1,9]
            int b = (int) (Math.random() * max) + 1;    // b ∈ [1,9]
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr3 = randomArray(maxKinds, maxValue, k, m);
            int ans1 = hash(arr3, k, m);
            int ans2 = onlyKTimes(arr3, k, m);
            if (ans1 != ans2) {
                System.out.println("arr3 = " + Arrays.toString(arr3));
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
