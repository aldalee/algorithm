package algorithm.class02_binary_search;

import java.util.Arrays;

import static util.Utils.generateRandomArray;

/**
 * 二分搜索的扩展
 * @author HuanyuLee
 * @date 2022/9/29
 */
public class Code03_BinarySearchNearRight {
    /**
     * 在数组arr中，二分搜索 <= target最右的位置
     * @param arr    升序数组
     * @param target 目标值
     * @return <=target最右的索引
     */
    public static int binarySearchNearestRight(int[] arr, int target) {
        int L = 0, R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = ((R - L) >> 1) + L;
            if (arr[mid] <= target) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    // for test
    public static int bruteForceSearchNearestRight(int[] arr, int target) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= target) {
                return i;
            }
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
            // int bs = binarySearchNearestLeft(arr, target);
            // int bf = bruteForceSearchNearestLeft(arr, target);
            int bs = binarySearchNearestRight(arr, target);
            int bf = bruteForceSearchNearestRight(arr, target);
            if (bs != bf) {
                System.out.println("target = " + target);
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("bf = " + bf);
                System.out.println("bs = " + bs);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
