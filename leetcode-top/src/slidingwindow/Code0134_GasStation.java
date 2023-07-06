package slidingwindow;

import java.util.LinkedList;

/**
 * 加油站问题
 * https://leetcode.cn/problems/gas-station/
 * @author HuanyuLee
 * @date 2023/7/6
 */
public class Code0134_GasStation {
    // 滑动窗口（课上版本）
    public int canCompleteCircuit(int[] gas, int[] cost) {
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

        int res = -1;
        for (int j = n, offset = 0, i = 0; j < m; offset = arr[i++], j++) {
            if (arr[window.peekFirst()] - offset >= 0) {
                res = i;
                break;
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
}
