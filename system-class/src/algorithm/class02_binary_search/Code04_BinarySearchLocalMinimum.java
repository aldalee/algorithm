package algorithm.class02_binary_search;

import java.util.Arrays;

import static util.Utils.generateAdjacentNotEqualRandomArray;

/**
 * 二分搜索局部最小值
 * 数据特点：1、无序 2、任意相邻的两个数不相等
 * 局部最小值的定义：满足以下任意一个条件：
 * 1、边界条件：arr[0] < arr[1]
 * 2、边界条件：arr[n-2] > arr[n-1]
 * 3、一般情况：arr[i-1] > arr[i] < arr[i+1]
 * 只需要返回一个局部最小的位置即可
 * @author HuanyuLee
 * @date 2022/9/29
 */
public class Code04_BinarySearchLocalMinimum {
    public static int getLocalMinimumIndex(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;
        if (arr.length == 1 || arr[0] < arr[1])
            return 0;
        if (arr[arr.length - 1] < arr[arr.length - 2])
            return arr.length - 1;
        int L = 1, R = arr.length - 2;
        while (L < R) {
            int mid = ((R - L) >> 1) + L;
            if (arr[mid] > arr[mid - 1])
                R = mid - 1;
            else if (arr[mid] > arr[mid + 1])
                L = mid + 1;
            else
                return mid;
        }
        return L;
    }

    // Verification
    public static boolean isLocalMinimum(int[] arr, int index) {
        if (arr.length <= 1)
            return true;
        if (index == 0)
            return arr[0] < arr[1];
        if (index == arr.length - 1)
            return arr[index] < arr[index - 1];
        return arr[index] < arr[index - 1] && arr[index] < arr[index + 1];
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 10000;
        int maxValue = 1000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateAdjacentNotEqualRandomArray(maxSize, maxValue);
            int ans = getLocalMinimumIndex(arr);
            if (!isLocalMinimum(arr, ans)) {
                System.out.println("ans = " + ans);
                System.out.println("arr = " + Arrays.toString(arr));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
