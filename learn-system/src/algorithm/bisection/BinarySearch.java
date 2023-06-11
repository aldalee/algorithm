package algorithm.bisection;

import java.util.Arrays;

/**
 * 二分搜索
 * @author HuanyuLee
 * @date 2023/6/11
 */
public class BinarySearch {
    /**
     * 标准的二分搜索
     * @param arr    升序数组
     * @param target 查找目标值
     * @return 目标值在arr中的索引，找不到返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 暴力搜索
     * @param arr    升序数组
     * @param target 查找目标值
     * @return 目标值在arr中的索引，找不到返回-1
     */
    public static int search(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int[] generateRandomSortedArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomSortedArray(maxSize, maxValue);
            int target = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            int actual = binarySearch(arr, target);
            int expected = search(arr, target);
            if (actual != expected) {
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("target = " + target);
                System.out.println("actual = " + actual);
                System.out.println("expected = " + expected);
                break;
            }
        }
    }
}
