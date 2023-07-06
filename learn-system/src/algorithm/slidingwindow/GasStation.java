package algorithm.slidingwindow;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 加油站问题（升级版）
 * 测试链接: https://leetcode.cn/problems/gas-station/
 * @author HuanyuLee
 * @date 2023/7/5
 */
public class GasStation {

    /**
     * 暴力求解 O(n^2)
     * @param gas  gas[i]表示当前加油站可以加的油
     * @param cost cost[i]表示从当前加油站到下一个加油站的要花费的代价
     * @return boolean[]
     */
    public static boolean[] bf(int[] gas, int[] cost) {
        int n = gas.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
        }
        boolean[] res = new boolean[n];
        for (int idx = 0; idx < n; idx++) {
            int sum = arr[idx];
            int next = (idx + 1) % n;
            for (int cnt = 0; cnt < n; cnt++) {
                if (sum < 0) break;
                sum += arr[next];
                next = (next + 1) % n;
            }
            if (sum >= 0) {
                res[idx] = true;
            }
        }
        return res;
    }

    // 滑动窗口
    public static boolean[] f(int[] gas, int[] cost) {
        int n = gas.length;
        int m = 2 * n;
        int[] arr = new int[m];
        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + n] = gas[i] - cost[i];
        }
        for (int i = 1; i < m; i++) {
            arr[i] += arr[i - 1];
        }
        LinkedList<Integer> window = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!window.isEmpty() && arr[window.peekLast()] >= arr[i]) {
                window.pollLast();
            }
            window.addLast(i);
        }

        boolean[] res = new boolean[n];
        for (int j = n, offset = 0, i = 0; j < m; offset = arr[i++], j++) {
            if (arr[window.peekFirst()] - offset >= 0) {
                res[i] = true;
            }
            if (window.peekFirst() == i) {
                window.pollFirst();
            }
            while (!window.isEmpty() && arr[window.peekLast()] >= arr[j]) {
                window.pollLast();
            }
            window.addLast(j);
        }
        return res;
    }

    public static void main(String[] args) {
        // [F, F, T, F]
        int[] gas = {1, 1, 3, 1};
        int[] cost = {2, 2, 1, 1};

        // [F, F, F]
        // int[] gas = {2, 3, 4};
        // int[] cost = {3, 4, 3};

        // [F, F, F, T, F]
        // int[] gas = {1, 2, 3, 4, 5};
        // int[] cost = {3, 4, 5, 1, 2};

        // [T]
        // int[] gas = {2};
        // int[] cost = {2};

        System.out.println(Arrays.toString(bf(gas, cost)));
        System.out.println(Arrays.toString(f(gas, cost)));
    }
}
