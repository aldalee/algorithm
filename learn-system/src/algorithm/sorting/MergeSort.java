package algorithm.sorting;

/**
 * 归并排序
 * @author HuanyuLee
 * @date 2022/10/2
 */
public class MergeSort {
    /**
     * 递归版本，自顶向下
     */
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 将辅助数组一次性创建，避免merge过程中的频繁创建和销毁，从而减少系统开销
        int[] help = new int[arr.length];
        sort(arr, 0, arr.length - 1, help);
    }

    /**
     * 把arr[l..r]排有序
     * T(n) = 2 * T(n/2) + O(n)
     * 根据主定理公式，时间复杂度 O(n*logn)
     * @param arr  待排序数组
     * @param l    数组左边界
     * @param r    数组右边界
     * @param help 辅助数组
     */
    private void sort(int[] arr, int l, int r, int[] help) {
        if (l == r) {
            return;
        }
        int m = ((r - l) >> 1) + l;
        sort(arr, l, m, help);
        sort(arr, m + 1, r, help);
        merge(arr, l, m, r, help);
    }

    /**
     * 将局部有序的数组arr整体有序
     * @param arr  arr[l...m]有序，arr[m+1...r]有序
     * @param l    数组左边界
     * @param m    数组中点位置
     * @param r    数组右边界
     * @param help 辅助数组
     */
    private void merge(int[] arr, int l, int m, int r, int[] help) {
        int i = 0;                          // 为help服务的索引
        int p1 = l, p2 = m + 1;             // 设置左右两部分的指针
        while (p1 <= m && p2 <= r) {        // p1和p2都不能越界
            // 谁小拷贝谁，它的指针下移
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界，要么p2越界，只会执行一个
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        // 将辅助数组的值拷贝回原数组，使其从[l,r]整体有序
        i = 0;
        for (int p = l; p <= r; p++) {
            arr[p] = help[i++];
        }
    }

    /**
     * 迭代版本，自底向上
     */
    public void mergeSort2(int[] arr) {
        int n = arr.length;
        int[] help = new int[n];
        for (int step = 1; step < n; step <<= 1) {
            if (step < 0) {     // 防止溢出
                break;
            }
            for (int l = 0; l < n - step; l += 2 * step) {
                merge(arr, l, l + step - 1, Math.min(l + 2 * step - 1, n - 1), help);
            }
        }
    }


    // 下面的代码是为了便于理解，实际书写可以以上面的版本为主
    public void mergeSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        int[] help = new int[n];                // 一次性开辟辅助数组空间
        int step = 1;                           // 步长
        int l;                                  // 当前左组的第一个位置
        int m;                                  // 当前左组的最后一个位置
        int r;                                  // 当前右组的最后一个位置
        while (step < n) {
            l = 0;
            while (l < n) {
                m = l + step - 1;
                if (m >= n) {                       // 左组不够，就停止
                    break;
                }
                r = Math.min(m + step, n - 1);
                merge(arr, l, m, r, help);          // [l...m] [m+1...r]
                l = r + 1;
            }
            if (step > n / 2) {                     // 防止溢出
                break;
            }
            step <<= 1;
        }
    }
}
