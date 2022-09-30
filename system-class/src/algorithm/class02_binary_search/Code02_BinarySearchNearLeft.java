package algorithm.class02_binary_search;

/**
 * 二分搜索的扩展
 * @author HuanyuLee
 * @date 2022/9/29
 */
public class Code02_BinarySearchNearLeft {
    /**
     * 在数组arr中，二分搜索 >= target最左的位置
     * @param arr    升序数组
     * @param target 目标值
     * @return >=target最左的索引
     */
    public static int binarySearchNearestLeft(int[] arr, int target) {
        int L = 0, R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = ((R - L) >> 1) + L;
            if (arr[mid] >= target) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    // for test
    public static int bruteForceSearchNearestLeft(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= target) {
                return i;
            }
        }
        return -1;
    }
}
