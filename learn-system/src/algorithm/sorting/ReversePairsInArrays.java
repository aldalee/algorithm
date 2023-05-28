package algorithm.sorting;

/**
 * （牛客网）JZ51 数组中的逆序对
 * @author HuanyuLee
 * @date 2023/5/27
 */
public class ReversePairsInArrays {
    public int InversePairs(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int[] help = new int[array.length];     // 将辅助数组一次性创建
        int ans = process(array, 0, array.length - 1, help);
        return ans % 1000000007;
    }

    // arr[l..r]既要排好序，又要返回逆序对数量
    private int process(int[] arr, int l, int r, int[] help) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid, help) + process(arr, mid + 1, r, help) + merge(arr, l, mid, r, help);
    }

    private int merge(int[] arr, int l, int m, int r, int[] help) {
        int i = r - l;
        int p1 = m, p2 = r;     // 从右向左遍历
        int ans = 0;
        while (p1 >= l && p2 > m) {
            boolean comp = arr[p1] > arr[p2];
            if (comp) {
                ans += p2 - m;
                ans %= 1000000007;
            }
            help[i--] = comp ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        i = 0;
        for (int p = l; p <= r; p++) {
            arr[p] = help[i++];
        }
        return ans;
    }
}
