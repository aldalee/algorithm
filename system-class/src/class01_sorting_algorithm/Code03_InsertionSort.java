package class01_sorting_algorithm;

/**
 * 插入排序
 * Ω(n)
 * O(n^2)
 * 算法稳定
 * @author HuanyuLee
 * @date 2022/9/27
 */
public class Code03_InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            // arr[j+1] 是待插入的元素
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // i 和 j 是一个位置，会出错
    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
