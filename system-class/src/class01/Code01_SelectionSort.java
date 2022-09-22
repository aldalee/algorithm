package class01;

import java.util.Arrays;

/**
 * 选择排序
 * @author HuanyuLee
 */
public class Code01_SelectionSort {
    /**
     * 升序
     * @param arr array
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {8, 9, 2, 4, 5, 1};
        System.out.println("arr = " + Arrays.toString(arr));
        selectionSort(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }
}
