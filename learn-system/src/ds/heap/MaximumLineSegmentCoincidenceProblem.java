package ds.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题（用堆实现）
 * 题目描述：给定很多线段，每个线段都有两个数[start, end]，表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 *    1）线段的开始和结束位置一定都是整数值
 *    2）线段重合区域的长度必须>=1
 * 要求：返回线段最多重合区域中，包含了几条线段
 * @author HuanyuLee
 * @date 2023/5/8
 */
public class MaximumLineSegmentCoincidenceProblem {
    public static int bruteForce(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int cover = 0;
        for (int[] ints : lines) {
            min = Math.min(min, ints[0]);
            max = Math.max(max, ints[1]);
        }
        for (double p = min + 0.5; p < max; p++) {
            int cur = 0;
            for (int[] line : lines) {
                if (line[0] < p && line[1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    public static int maxCover(int[][] lines) {
        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int cover = 0;
        for (int[] line : lines) {
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();
            }
            heap.add(line[1]);
            cover = Math.max(cover, heap.size());
        }
        return cover;
    }
}
