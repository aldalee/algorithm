package sorting;

/**
 * https://leetcode.cn/problems/sort-an-array
 * 排序数组
 * 可以检测多种排序算法的正确性
 * @author HuanyuLee
 * @date 2023/5/27
 */
public class Code0912_SortAnArray {
    public int[] sortArray(int[] arr) {
        // Arrays.sort(arr);
        mergeSort(arr);
        // heapSort(arr);
        return arr;
    }

    // ================================== 归并排序 ==================================
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 把arr[l..r]排有序
     * @param arr 待排序数组
     * @param l   数组左边界
     * @param r   数组右边界
     */
    private void sort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int m = ((r - l) >> 1) + l;
        sort(arr, l, m);
        sort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    /**
     * 将局部有序的数组arr整体有序
     * @param arr arr[l...m]有序，arr[m+1...r]有序
     * @param l   数组左边界
     * @param m   数组中点位置
     * @param r   数组右边界
     */
    private void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l, p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    // ================================== 快速排序 ==================================


    // =================================== 堆排序 ===================================
    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int heapSize = arr.length;
        for (int i = heapSize - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);  //O(n)
        }
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    private void heapify(int[] arr, int index, int heapSize) {
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

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
