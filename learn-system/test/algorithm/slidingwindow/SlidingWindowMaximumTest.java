package algorithm.slidingwindow;

import org.junit.Test;

import java.util.Arrays;

import static algorithm.slidingwindow.SlidingWindowMaximum.bruteForceSolution;
import static algorithm.slidingwindow.SlidingWindowMaximum.maxSlidingWindow;
import static org.junit.Assert.fail;
import static tool.ArraysUtil.generateRandomArray;
import static tool.ArraysUtil.isEqual;

public class SlidingWindowMaximumTest {

    @Test
    public void testGetMaxWindow() {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] actual = maxSlidingWindow(arr, w);
            int[] expected = bruteForceSolution(arr, w);
            if (!isEqual(actual, expected)) {
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("w = " + w);
                System.out.println("expected = " + Arrays.toString(expected));
                System.out.println("actual = " + Arrays.toString(actual));
                fail();
            }
        }
    }
}
