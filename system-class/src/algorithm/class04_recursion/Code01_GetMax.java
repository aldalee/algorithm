package algorithm.class04_recursion;

/**
 * 引例，递归实现求数组中的最大值
 * @author HuanyuLee
 * @date 2022/10/2
 */
public class Code01_GetMax {
    public static int getMax(int[] arr) {
        return f(arr, 0, arr.length - 1);
    }

    private static int f(int[] arr, int L, int R) {
        if (L == R) {    // base case
            return arr[L];
        }
        int mid = ((R - L) >> 1) + L;
        int leftMax = f(arr, L, mid);
        int rightMax = f(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    public static int testGetMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 2};
        System.out.println(getMax(arr));
        System.out.println(testGetMax(arr));
    }
}
