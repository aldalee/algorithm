package algorithm.class03_bitwise_operation;

/**
 * 找到出现奇数次、其余都出现偶数次的数
 * @author HuanyuLee
 * @date 2022/9/29
 */
public class Code03_EvenTimesOddTimes {
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

    // 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
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

    public static void main(String[] args) {
        int[] arr = {4, 3, 4, 2, 4, 3, 1, 2, 1, 1, 1, 3, 3};
        int[] arr2 = {-1, 6, -1, 6, -1, 3, 3, 3, 3, 3, 4, 4, 5, 5};
        printOddTimesNumber(arr);
        printOddTimesTwoNumber(arr2);
    }
}
