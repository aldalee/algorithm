package algorithm.sorting;

import java.util.ArrayList;

/**
 * （牛客网）NC349 计算数组的小和
 * @author HuanyuLee
 * @date 2023/5/27
 */
public class SmallSumOfArrays {
    /**
     * 归并排序求解小和问题（牛客网）
     */
    public long calArray(ArrayList<Integer> nums) {
        int[] arr = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            arr[i] = nums.get(i);
        }
        return process(arr, 0, nums.size() - 1);
    }

    // arr[l...r]既要排好序，又要返回小和
    private long process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private long merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l, p2 = m + 1;
        long sum = 0;
        while (p1 <= m && p2 <= r) {
            boolean comp = arr[p1] <= arr[p2];
            if (comp) {
                sum += (long) (r - p2 + 1) * arr[p1];
            }
            help[i++] = comp ? arr[p1++] : arr[p2++];
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
        return sum;
    }

    /**
     * 暴力求解
     */
    public long bruteForceSolution(int[] nums) {
        long sum = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    sum += nums[j];
                }
            }
        }
        return sum;
    }
}
