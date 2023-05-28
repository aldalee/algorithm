package algorithm.sorting;

/**
 * 基数排序
 * 不基于比较的排序，适用于样本是10进制的正整数（经典的）
 * @author HuanyuLee
 * @date 2023/5/10
 */
public class RadixSort {
    /**
     * Only for no-negative value.
     * 如果有负数，可以先找到其中最小的值MIN_VALUE，
     * 然后将所有值都加上MIN_VALUE使得它们变为非负整数，
     * 接下来按照下面的方法进行排序，
     * 排好顺序后，再依次减去MIN_VALUE恢复现场。
     * 但是注意: 这样的处理方式可能会导致数据溢出问题！
     */
    public void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    /**
     * 在arr[L...R]范围上排序
     * @param arr   数组
     * @param L     arr索引的范围L
     * @param R     arr索引的范围L
     * @param digit arr最大值的十进制位数
     */
    public void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int[] help = new int[R - L + 1];        // 有多少个数就准备多少个辅助空间
        for (int d = 1; d <= digit; d++) {
            // count[i]表示arr中第d位<=i的总数量
            int[] count = new int[radix];
            for (int i = L; i <= R; i++) {
                count[getDigit(arr[i], d)]++;
            }
            for (int i = 1; i < radix; i++) {   // 累加和，省去传统的10个桶
                count[i] = count[i] + count[i - 1];
            }
            // 从右向左遍历，决定当前数字该放在哪个桶里
            for (int i = R; i >= L; i--) {
                int j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (int i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    private int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    private int getDigit(int x, int d) {
        return (x / (int) Math.pow(10, d - 1)) % 10;
    }
}
