package algorithm.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 桶排序
 * 桶排序的核心思想:
 *     1、将待排序的数据分到有限数量的桶中，
 *     2、然后对每个桶中的数据进行排序，
 *     3、最后将每个桶中的数据按顺序合并起来，得到有序数据
 * 时间复杂度为O(n+k)，其中n是待排序数组的长度，k是桶的数量
 * 适用情况分析:
 *     1、适用于数据量较大，但数据范围较小的情况，
 *     2、在数据范围较大的情况下，桶的数量可能需要过多，从而导致空间复杂度过高，
 *     3、如果数据分布不均匀，可能导致某些桶中的数据过多，从而影响排序效率。
 * @author HuanyuLee
 * @date 2023/5/10
 */
public class BucketSort {
    public static void bucketSort(int[] arr) {
        // 获取哈希码
        final int[] code = hash(arr);
        // 创建并初始化桶为ArrayList
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[code[1]];
        for (int i = 0; i < code[1]; i++) {
            buckets[i] = new ArrayList<>();
        }
        // 将数据分配到各个桶中
        for (int val : arr) {
            buckets[hash(val, code)].add(val);
        }
        // 对每个桶中的元素进行排序
        for (var bucket : buckets) {
            Collections.sort(bucket);
        }
        // 合并桶中的元素
        int idx = 0;
        for (var bucket : buckets) {
            for (int v : bucket) {
                arr[idx++] = v;
            }
        }
    }

    /**
     * 计算哈希码
     * @param arr 输入数组
     * @return 哈希码数组，第一个元素为最大值，第二个元素为桶数量
     */
    private static int[] hash(int[] arr) {
        int m = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (m < arr[i]) {
                m = arr[i];
            }
        }
        return new int[]{m, (int) Math.sqrt(arr.length)};
    }

    /**
     * 计算元素的哈希值
     * 线性映射函数，将元素均匀地分布到不同的桶中
     * @param val  元素
     * @param code 哈希码数组
     * @return 元素的哈希值
     */
    private static int hash(int val, int[] code) {
        return (int) ((double) val / code[0] * (code[1] - 1));
    }

    public static void main(String[] args) {
        int[] arr = {80, 50, 30, 10, 90, 60, 0, 70, 40, 20, 50};
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
