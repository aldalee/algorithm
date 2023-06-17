package algorithm.slidingwindow;

import java.util.LinkedList;

/**
 * 达标子数组的数量
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足:
 * sub中最大值 – sub中最小值 <= num
 * 返回arr中达标子数组的数量
 * @author HuanyuLee
 * @date 2023/6/17
 */
public class AllLessNumSubArray {
    /**
     * 暴力求解
     */
    public static int bruteForceSolution(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        int n = arr.length;
        int count = 0;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                int max = arr[l];
                int min = arr[l];
                for (int i = l + 1; i <= r; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= num) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 滑动窗口求解
     */
    public static int slidingWindow(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        int count = 0;
        // 窗口是[L, R)
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        int n = arr.length;
        int r = 0;
        for (int l = 0; l < n; l++) {
            // r 一直向右扩，直到初次不达标，停止
            while (r < n) {
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[r]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(r);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[r]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(r);
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > num) {
                    break;
                } else {
                    r++;
                }
            }
            count += r - l;
            // 如果 l 即将过期，让其滚蛋
            if (maxWindow.peekFirst() == l) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == l) {
                minWindow.pollFirst();
            }
        }
        return count;
    }
}
