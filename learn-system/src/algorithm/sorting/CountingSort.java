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
        if (arr == null || arr.length < 2) return;
        int max = Integer.MIN_VALUE;
        for (int value : arr) {
            max = Math.max(max, value);
        }
        int[] bucket = new int[max + 1];
        for (int i : arr) {
            bucket[i]++;
        }
        for (int i = 0, j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 2, 4, 6, 10};
        countingSort(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }
}
