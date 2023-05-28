package algorithm.sorting;

import java.util.Arrays;

/**
 * 计数排序
 * 不基于比较的排序，适用于数据很特殊的情况
 * 时间复杂度O(N)
 * @author HuanyuLee
 * @date 2023/5/10
 */
public class CountingSort {
    /**
     * The value of arr only in [1,200].
     */
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 寻找数组中的最大值
        int max = Integer.MIN_VALUE;
        for (int value : arr) {
            max = Math.max(max, value);
        }
        // 创建计数数组并统计每个元素的频率
        int[] bucket = new int[max + 1];
        for (int num : arr) {
            bucket[num]++;
        }
        // 根据计数数组重构原始数组
        int idx = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                arr[idx++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 2, 4, 6, 10};
        countingSort(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }
}
