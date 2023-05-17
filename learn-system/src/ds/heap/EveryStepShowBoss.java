package ds.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆练习: 抽奖的TopK问题（国外的真实笔试题）
 * @author HuanyuLee
 * @date 2023/5/9
 */
public class EveryStepShowBoss {
    /**
     * 使用加强堆实现，优化暴力求解
     * 时间复杂度: O(N * (logN+logK+K))
     * @param arr 用户数组
     * @param op  购买行为
     * @param k   Top k
     * @return 每个事件发生的得奖名单
     */
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        WhoIsWinner winner = new WhoIsWinner(k);
        for (int i = 0; i < arr.length; i++) {
            winner.operate(i, arr[i], op[i]);
            ans.add(winner.getAwardees());
        }
        return ans;
    }

    /**
     * 暴力求解，只是模拟过程，不优化
     * @param arr 用户数组
     * @param op  购买行为
     * @param k   Top k
     * @return 每个事件发生的得奖名单
     */
    public static List<List<Integer>> bruteForce(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> customers = new HashMap<>();
        List<Customer> cands = new ArrayList<>();       // 候选区
        List<Customer> awards = new ArrayList<>();      // 得奖区
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            if (!buyOrRefund && !customers.containsKey(id)) {
                ans.add(getCurAns(awards));
                continue;
            }
            // 没有发生用户购买数为零并且又退货的事件
            // 因此发生下面的情况:
            //   1、用户之前购买数等于0，此时发生买货事件
            //   2、用户之前购买数大于0，此时发生买货事件
            //   3、用户之前购买数大于0，此时发生退货事件
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer c = customers.get(id);
            if (buyOrRefund) {      // 重新调整用户的buy值
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buy == 0) customers.remove(id);
            // 新来的用户发生购买行为
            if (!cands.contains(c) && !awards.contains(c)) {
                c.time = i;
                if (awards.size() < k) {
                    awards.add(c);
                } else {
                    cands.add(c);
                }
            }
            cleanZeroArea(cands);
            cleanZeroArea(awards);
            cands.sort(new CandidateComparator());
            awards.sort(new AwardeeComparator());
            move(cands, awards, k, i);
            ans.add(getCurAns(awards));
        }
        return ans;
    }

    /**
     * 获取得奖区的数组
     * @param awards 得奖区
     * @return List<Integer>
     */
    private static List<Integer> getCurAns(List<Customer> awards) {
        List<Integer> ans = new ArrayList<>();
        for (Customer c : awards) {
            ans.add(c.id);
        }
        return ans;
    }

    /**
     * 清除购买数为零的区域
     * @param arr 得奖区或候选区数组
     */
    private static void cleanZeroArea(List<Customer> arr) {
        List<Customer> noZero = new ArrayList<>();
        for (Customer c : arr) {
            if (c.buy != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        arr.addAll(noZero);
    }

    /**
     * 替换得奖区的方法（暴力求解）
     * @param cands  候选区
     * @param awards 得奖区
     * @param k      Top k
     * @param time   进入时间
     */
    private static void move(List<Customer> cands, List<Customer> awards, int k, int time) {
        if (cands.isEmpty()) return;        // 候选区为空，不涉及替换
        if (awards.size() < k) {            // 得奖区未满，候选区有人
            Customer c = cands.get(0);
            c.time = time;
            awards.add(c);
            cands.remove(0);
        } else {                            // 得奖区已满，候选区有人
            if (cands.get(0).buy > awards.get(0).buy) {
                Customer oldAwardee = awards.get(0);
                awards.remove(0);
                Customer newAwardee = cands.get(0);
                cands.remove(0);
                newAwardee.time = time;
                oldAwardee.time = time;
                awards.add(newAwardee);
                cands.add(oldAwardee);
            }
        }
    }
}

/**
 * 使用加强堆实现，优化暴力求解
 */
class WhoIsWinner {
    private HashMap<Integer, Customer> customers;
    private HeapGreater<Customer> candsHeap;    // 候选区加强堆
    private HeapGreater<Customer> awardsHeap;   // 得奖区加强堆
    private final int k;                        // TopK名用户

    public WhoIsWinner(int k) {
        customers = new HashMap<>();
        candsHeap = new HeapGreater<>(new CandidateComparator());   // 大根堆
        awardsHeap = new HeapGreater<>(new AwardeeComparator());    // 小根堆
        this.k = k;
    }

    public void operate(int time, int id, boolean buyOrRefund) {
        if (!buyOrRefund && !customers.containsKey(id)) return;
        if (!customers.containsKey(id)) {
            customers.put(id, new Customer(id, 0, 0));
        }
        Customer c = customers.get(id);
        if (buyOrRefund) {
            c.buy++;
        } else {
            c.buy--;
        }
        if (c.buy == 0) customers.remove(id);
        // 下面是优化的内容
        if (!candsHeap.contains(c) && !awardsHeap.contains(c)) {    // 新用户发生购买行为
            c.time = time;
            if (awardsHeap.size() < k) {
                awardsHeap.push(c);
            } else {
                candsHeap.push(c);
            }
        } else if (candsHeap.contains(c)) {                         // 当前用户在候选区
            if (c.buy == 0) {
                candsHeap.remove(c);
            } else {
                candsHeap.resign(c);
            }
        } else {                                                    // 当前用户在得奖区
            if (c.buy == 0) {
                awardsHeap.remove(c);
            } else {
                awardsHeap.resign(c);
            }
        }
        move(time);
    }

    public List<Integer> getAwardees() {
        List<Customer> customers = awardsHeap.getAllElements();
        List<Integer> ans = new ArrayList<>();
        for (Customer c : customers) ans.add(c.id);
        return ans;
    }

    /**
     * 替换得奖区的方法（时间复杂度降低了！）
     */
    private void move(int time) {
        if (candsHeap.isEmpty()) return;
        if (awardsHeap.size() < k) {
            Customer p = candsHeap.pop();
            p.time = time;
            awardsHeap.push(p);
        } else {
            if (candsHeap.peek().buy > awardsHeap.peek().buy) {
                Customer oldAwardee = awardsHeap.pop();
                Customer newAwardee = candsHeap.pop();
                oldAwardee.time = time;
                newAwardee.time = time;
                awardsHeap.push(newAwardee);
                candsHeap.push(oldAwardee);
            }
        }
    }
}

/**
 * 用户类
 */
class Customer {
    int id;         // 用户的ID
    int buy;        // 用户购买的商品数
    int time;       // 用户进入得奖区或候选区的时间

    public Customer(int id, int buy, int time) {
        this.id = id;
        this.buy = buy;
        this.time = time;
    }
}

/**
 * 候选区比较器
 */
class CandidateComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return c1.buy != c2.buy ? c2.buy - c1.buy : c1.time - c2.time;
    }
}

/**
 * 获奖区比较器
 */
class AwardeeComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return c1.buy != c2.buy ? c1.buy - c2.buy : c1.time - c2.time;
    }
}
