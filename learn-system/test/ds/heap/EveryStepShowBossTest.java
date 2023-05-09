package ds.heap;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static ds.heap.EveryStepShowBoss.bruteForce;
import static ds.heap.EveryStepShowBoss.topK;
import static org.junit.Assert.fail;

public class EveryStepShowBossTest {
    private static final int MAX_VALUE = 100;
    private static final int MAX_LEN = 5000;
    private static final int MAX_K = 10;
    private static final int TEST_CASES = 100000;

    private static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] arr, boolean[] op) {
            this.arr = arr;
            this.op = op;
        }
    }

    public Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5;
        }
        return new Data(arr, op);
    }

    public boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort(Comparator.comparingInt(a -> a));
            cur2.sort(Comparator.comparingInt(a -> a));
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testTopK(){
        for (int i = 0; i < TEST_CASES; i++) {
            Data testData = randomData(MAX_VALUE, MAX_LEN);
            int k = (int) (Math.random() * MAX_K) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = bruteForce(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + "," + op[j]);
                }
                System.out.println("k = " + k);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                fail();
                break;
            }
        }
    }
}
