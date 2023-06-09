package algorithm.slidingwindow;

import java.util.LinkedList;

/**
 * 滑动窗口最大值
 * 假设一个固定大小为W的窗口，依次划过arr
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回: [5,5,5,4,6,7]
 * @author HuanyuLee
 * @date 2023/6/9
 */
public class SlidingWindowMaximum {
    /**
     * 暴力求解
     * @param nums 数组
     * @param w    窗口大小
     * @return 每一次滑出状况的最大值
     */
    public static int[] bruteForceSolution(int[] nums, int w) {
        if (nums == null || nums.length < w || w < 1) {
            return null;
        }
        int n = nums.length;
        int[] res = new int[n - w + 1];
        int idx = 0;
        int l = 0, r = w - 1;
        while (r < n) {
            int max = nums[l];
            for (int i = l + 1; i <= r; i++) {
                max = Math.max(max, nums[i]);
            }
            res[idx++] = max;
            l++;
            r++;
        }
        return res;
    }

    /**
     * 滑动窗口
     */
    public static int[] maxSlidingWindow(int[] nums, int w) {
        if (nums == null || nums.length < w || w < 1) {
            return null;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int n = nums.length;
        int[] res = new int[n - w + 1];
        int idx = 0;
        for (int r = 0; r < n; r++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[r]) {
                queue.pollLast();
            }
            queue.addLast(r);
            if (queue.peekFirst() == r - w) {
                queue.pollFirst();
            }
            if (r >= w - 1) {
                res[idx++] = nums[queue.peekFirst()];
            }
        }
        return res;
    }
}
