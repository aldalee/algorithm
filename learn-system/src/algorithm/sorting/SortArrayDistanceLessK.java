package algorithm.sorting;

import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组，对其进行排序:
 * 几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k
 * k相对于数组长度来说是比较小的。
 * 请选择一个合适的排序策略，对这个数组进行排序
 * @author HuanyuLee
 * @date 2023/5/7
 */
public class SortArrayDistanceLessK {
    public void sortedArrayDistanceLessK(int[] arr, int k) {
        if (k == 0 || arr == null || arr.length < 2) return;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int n = arr.length;
        int index = 0;
        while (index <= Math.min(n - 1, k - 1)) {
            heap.add(arr[index++]);
        }
        int i = 0;
        while (index < n) {
            heap.add(arr[index++]);
            arr[i++] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}
