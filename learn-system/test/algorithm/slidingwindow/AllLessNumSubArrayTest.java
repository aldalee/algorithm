package algorithm.slidingwindow;

import org.junit.Test;

import java.util.Arrays;

import static algorithm.slidingwindow.AllLessNumSubArray.bruteForceSolution;
import static algorithm.slidingwindow.AllLessNumSubArray.slidingWindow;
import static org.junit.Assert.fail;
import static tool.ArraysUtil.generateRandomArray;

public class AllLessNumSubArrayTest {
    @Test
    public void testSlidingWindow() {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int num = (int) (Math.random() * (arr.length + 1));
            int actual = slidingWindow(arr, num);
            int expected = bruteForceSolution(arr, num);
            if (actual != expected) {
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("num = " + num);
                System.out.println("expected = " + expected);
                System.out.println("actual = " + actual);
                fail();
            }
        }
    }
}
