package week_2023_07_4;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 将数组和减半的最少操作次数
 * 题目描述: 给你一个正整数数组nums。每一次操作中，
 * 你可以从nums中选择任意一个数并将它减小到恰好一半。
 * 注意，在后续操作中你可以对减半过的数继续执行操作
 * 请你返回将nums数组和至少减少一半的最少操作数。
 * 测试链接: https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/
 * @author HuanyuLee
 * @date 2023/7/27
 */
public class MinimumOperationsToHalveArraySum {
    // 方法一: 利用系统实现的堆
    public int halveArray(int[] nums) {
        PriorityQueue<Double> heap = new PriorityQueue<>(Comparator.reverseOrder());
        double sum = 0;
        for (int num : nums) {
            heap.add((double) num);
            sum += num;
        }
        sum /= 2;
        int cnt = 0;
        double minus = 0, cur;
        while (minus < sum) {
            cur = heap.poll() / 2;
            heap.add(cur);
            minus += cur;
            cnt++;
        }
        return cnt;
    }

    // 方法二: 手动建堆，并完善精度问题
    public int halveArray2(int[] nums) {
        long[] heap = new long[100001];
        int size = nums.length;
        long sum = 0;
        for (int i = size - 1; i >= 0; i--) {
            heap[i] = (long) nums[i] << 20;
            sum += heap[i];
            heapify(heap, i, size);
        }
        sum /= 2;
        int cnt = 0;
        for (long minus = 0; minus < sum; cnt++) {
            heap[0] /= 2;
            minus += heap[0];
            heapify(heap, 0, size);
        }
        return cnt;
    }

    private void heapify(long[] heap, int idx, int size) {
        while (true) {
            int l = 2 * idx + 1;
            int r = 2 * idx + 2;
            int max = idx;
            if (l < size && heap[l] > heap[max]) max = l;
            if (r < size && heap[r] > heap[max]) max = r;
            if (max == idx) break;
            swap(heap, idx, max);
            idx = max;
        }
    }

    private void swap(long[] heap, int i, int j) {
        long tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
