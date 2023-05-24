package algorithm.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/ipo/
 * IPO（项目投资问题）
 * @author HuanyuLee
 * @date 2023/5/24
 */
public class IPO {
    static class Project {
        int p;
        int c;

        public Project(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    /**
     * @param K       最多可投资的项目
     * @param W       初始资金
     * @param profits 纯利润
     * @param capital 投资资本
     * @return 最大的资本
     */
    public int findMaximizedCapital(int K, int W, int[] profits, int[] capital) {
        PriorityQueue<Project> minCapitalQ = new PriorityQueue<>(Comparator.comparingInt(o -> o.c));
        PriorityQueue<Project> maxProfitQ = new PriorityQueue<>((o1, o2) -> o2.p - o1.p);
        // 先将所有项目加入小根堆
        for (int i = 0; i < profits.length; i++) {
            minCapitalQ.add(new Project(profits[i],capital[i]));
        }
        // 进行K轮项目
        for (int i = 0; i < K; i++) {
            while (!minCapitalQ.isEmpty() && minCapitalQ.peek().c <= W) {
                maxProfitQ.add(minCapitalQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }
}
