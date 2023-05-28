package algorithm.sorting;

/**
 * 冒泡排序
 * @author HuanyuLee
 * @date 2023/5/10
 */
public class BubbleSort {
    public void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        boolean swapped;
        for (int i = 0; i < arr.length; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {     // 如果在一轮遍历中没有进行任何交换，说明数组已经有序
                break;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 使用异或，完成只使用两个变量的交换操作
    // 这种实现方式可能会受限于整数溢出的问题
    private void swap2(int[] arr, int i, int j) {
        if (i == j) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
