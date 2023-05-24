package algorithm.greedy;

import java.util.PriorityQueue;

/**
 * 黄金分割问题
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。一群人想整分整块金条，怎么分最省铜板?
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10、20、30三个部分。
 * 如果先把长度60的金条分成10和50，花费60;再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20，花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 * @author HuanyuLee
 * @date 2023/5/24
 */
public class LessMoneySplitGold {
    /**
     * 暴力枚举
     */
    public static int bruteForceEnumerate(int[] golds) {
        if (golds == null || golds.length == 0) {
            return 0;
        }
        return process(golds, 0);
    }

    /**
     * 暴力求解
     * @param golds 待合并的数
     * @param pre   之前合并产生的代价
     * @return 当前合并的最小总代价
     */
    private static int process(int[] golds, int pre) {
        if (golds.length == 1) {
            return pre;
        }
        int cost = Integer.MAX_VALUE;
        for (int i = 0; i < golds.length; i++) {
            for (int j = i + 1; j < golds.length; j++) {
                int next = process(mergedArray(golds, i, j), pre + golds[i] + golds[j]);
                cost = Math.min(cost, next);
            }
        }
        return cost;
    }

    private static int[] mergedArray(int[] arr, int a, int b) {
        int[] array = new int[arr.length - 1];
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != a && i != b) {
                array[idx++] = arr[i];
            }
        }
        array[idx] = arr[a] + arr[b];
        return array;
    }

    /**
     * 贪心策略 哈夫曼编码
     * @param golds 要合并的各段长度
     * @return 合并的最小总代价
     */
    public static int lessMoney(int[] golds) {
        if (golds == null) return 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (var gold : golds) {
            heap.add(gold);
        }
        int cost = 0;
        while (heap.size() > 1) {
            int cur = heap.poll() + heap.poll();
            cost += cur;
            heap.add(cur);
        }
        return cost;
    }
}
