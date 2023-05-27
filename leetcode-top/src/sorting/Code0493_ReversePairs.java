package sorting;

/**
 * https://leetcode.cn/problems/reverse-pairs/
 * 翻转对
 * 给定一个数组nums，如果i < j且nums[i] > 2*nums[j]，我们就将（i, j）称作一个重要翻转对
 * 你需要返回给定数组中的重要翻转对的数量。
 * @author HuanyuLee
 * @date 2023/5/27
 */
public class Code0493_ReversePairs {
    /**
     * 归并排序求解
     */
    public int reversePairs(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    // arr[l..r]既要排好序，又要返回翻转对数量
    private int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private int merge(int[] arr, int l, int m, int r) {
        int ans = 0;
        int window = m + 1;     // 目前囊括进来的数，[m+1, window)
        // 先进行统计
        for (int i = l; i <= m; i++) {
            while (window <= r && (arr[i] > ((long) arr[window] << 1))) {
                window++;
            }
            ans += window - m - 1;
        }
        // 再进行合并
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
        return ans;
    }

    /**
     * 暴力求解
     */
    public int bruteForceSolution(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > ((long) nums[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
