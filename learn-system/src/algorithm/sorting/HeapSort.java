package algorithm.sorting;

/**
 * 堆排序
 * @author HuanyuLee
 * @date 2023/5/7
 */
public class HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int heapSize = arr.length;
        for (int i = heapSize - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);  //O(n)
        }
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        while (true) {
            int L = 2 * index + 1;
            int R = 2 * index + 2;
            int largest = index;
            if (L < heapSize && arr[L] > arr[largest]) largest = L;
            if (R < heapSize && arr[R] > arr[largest]) largest = R;
            if (largest == index) break;
            swap(arr, index, largest);
            index = largest;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
