package class02_binary_search;

import java.util.Arrays;

import static util.Utils.generateRandomArray;

/**
 * 二分搜索
 * @author HuanyuLee
 * @date 2022/9/29
 */
public class Code01_BinarySearch {
    /**
     * 在有序数组arr中二分搜索目标值target所在的索引下标
     * @param arr    升序数组
     * @param target 目标值
     * @return 如果找到了返回索引下标，找不到返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            return -1;
        int L = 0, R = arr.length - 1;
        while (L <= R) {
            // 防止溢出，位运算加快计算速度
            int mid = ((R - L) >> 1) + L;
            if (arr[mid] == target)
                return mid;
            else if (arr[mid] > target)
                R = mid - 1;
            else
                L = mid + 1;
        }
        return -1;
    }

    // for test
    public static int bruteForceSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int maxSize = 10000;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int target = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            int bs = binarySearch(arr, target);
            int bf = bruteForceSearch(arr, target);
            if (bs != -1 && bf !=-1 && arr[bf] != arr[bs]) {
                succeed = false;
                System.out.println("target = " + target);
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("bf = " + bf);
                System.out.println("bs = " + bs);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}