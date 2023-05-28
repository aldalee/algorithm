package algorithm.sorting;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 快速排序
 * @author HuanyuLee
 * @date 2023/5/10
 */
public class QuickSort {
    /**
     * 随机快排（递归版本）
     */
    public void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    public void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int[] equalArea = DutchNationalFlag(arr, l, r);
        sort(arr, l, equalArea[0] - 1);
        sort(arr, equalArea[1] + 1, r);
    }

    /**
     * 荷兰国旗问题，以arr[r]做划分值（也就是partition）
     * @param arr 数组
     * @param l   数组左边界
     * @param r   数组右边界
     * @return 等于划分值的区域
     */
    public int[] DutchNationalFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;           // 小于划分值区域右边界
        int more = r;               // 大于划分值区域左边界
        int idx = l;                // 当前位置
        while (idx < more) {
            if (arr[idx] == arr[r]) {
                idx++;
            } else if (arr[idx] < arr[r]) {
                swap(arr, idx++, ++less);
            } else {
                swap(arr, idx, --more);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 随机快排（迭代版本）
     */
    static class Op {
        int l, r;

        public Op(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        Deque<Op> stack = new ArrayDeque<>();
        stack.push(new Op(0, n - 1));
        do {
            Op op = stack.pop();
            if (op.l < op.r) {
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                int[] equalArea = DutchNationalFlag(arr, op.l, op.r);
                int el = equalArea[0];
                int er = equalArea[1];
                stack.push(new Op(op.l, el - 1));
                stack.push(new Op(er + 1, op.r));
            }
        } while (!stack.isEmpty());
    }
}
