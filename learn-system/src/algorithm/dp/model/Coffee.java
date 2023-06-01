package algorithm.dp.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 咖啡机问题（寻找业务限制的尝试模型）
 * ============ 例题，感受从暴力递归到动态规划的过程 =============
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 四个参数: int[] arr、int N，int a、int b
 * @author HuanyuLee
 * @date 2023/6/2
 */
public class Coffee {

    /**
     * 彻底的暴力，很慢但是绝对正确
     */
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    private static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int wash, int air) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, wash, air, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, wash, air));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    private static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一: 当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二: 当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }


    private static class Machine {
        int free;
        int work;

        public Machine(int free, int work) {
            this.free = free;
            this.work = work;
        }
    }


    public static int[] getDrinks(int[] arr, int n) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(Comparator.comparingInt(o -> (o.free + o.work)));
        for (int time : arr) {
            heap.add(new Machine(0, time));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.free += cur.work;
            drinks[i] = cur.free;
            heap.add(cur);
        }
        return drinks;
    }

    /**
     * 暴力递归 + 贪心
     */
    public static int minTime(int[] arr, int n, int a, int b) {
        int[] drinks = getDrinks(arr, n);
        return func(drinks, a, b, 0, 0);
    }

    /**
     * 递归函数，从第[idx]号人开始全部杯子洗干净需要的最少时间
     * @param drinks 所有人开始洗咖啡杯的时间数组
     * @param wash   咖啡机洗一杯需要的时间（只能串行）
     * @param air    咖啡杯自己挥发需要的时间（可以并行）
     * @param idx    当前来到第几号人的咖啡杯
     * @param free   洗咖啡杯的机器何时可用
     * @return 最少时间
     */
    private static int func(int[] drinks, int wash, int air, int idx, int free) {
        if (idx == drinks.length) {
            return 0;
        }
        // idx号人决定让机器洗
        int selfTime = Math.max(drinks[idx], free) + wash;
        int otherTime = func(drinks, wash, air, idx + 1, selfTime);
        int p1 = Math.max(selfTime, otherTime);

        // idx号人决定让杯子挥发
        int selfTime2 = drinks[idx] + air;
        int otherTime2 = func(drinks, wash, air, idx + 1, free);
        int p2 = Math.max(selfTime2, otherTime2);
        return Math.min(p1, p2);
    }


    /**
     * 动态规划
     */
    public static int minTimeDP(int[] arr, int n, int wash, int air) {
        int[] drinks = getDrinks(arr, n);
        // 因为递归中free无法确定具体的变化规律，因此根据业务，估计出它的上限
        // 如果实在改不出来或者非常难改，直接不改了！直接上傻缓存！！！
        int maxFree = 0;
        for (int i = 0; i < n; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + wash;
        }
        int[][] dp = new int[n + 1][maxFree + 1];
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int free = 0; free <= maxFree; free++) {
                int selfTime = Math.max(drinks[idx], free) + wash;
                if (selfTime <= maxFree) {
                    int otherTime = dp[idx + 1][selfTime];
                    int p1 = Math.max(selfTime, otherTime);
                    int selfTime2 = drinks[idx] + air;
                    int otherTime2 = dp[idx + 1][free];
                    int p2 = Math.max(selfTime2, otherTime2);
                    dp[idx][free] = Math.min(p1, p2);
                }
            }
        }
        return dp[0][0];
    }
}
