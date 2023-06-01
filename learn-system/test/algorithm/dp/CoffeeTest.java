package algorithm.dp;

import org.junit.Test;

import java.util.Arrays;

import static algorithm.dp.model.Coffee.*;
import static org.junit.Assert.fail;

public class CoffeeTest {
    int len = 10;
    int max = 10;
    int testTime = 10;

    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }


    @Test
    public void testMinTime() {
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = minTime(arr, n, a, b);
            if (ans1 != ans2) {
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("n = " + n);
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                fail();
                break;
            }
        }
    }

    @Test
    public void testMinTimeDP() {
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = minTimeDP(arr, n, a, b);
            if (ans1 != ans2) {
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("n = " + n);
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                fail();
                break;
            }
        }
    }
}
