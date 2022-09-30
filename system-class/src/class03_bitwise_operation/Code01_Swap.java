package class03_bitwise_operation;

import java.util.Arrays;

/**
 * 不用额外变量交换两个数
 * @author HuanyuLee
 * @date 2022/9/29
 */
public class Code01_Swap {
    public static void swap(int[] arr, int i, int j) {
        // 防止同一个内存区域，出现错误
        if (i != j){
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println("交换前：arr = " + Arrays.toString(arr));
        swap(arr, 0, 1);
        System.out.println("交换前：arr = " + Arrays.toString(arr));
    }
}
