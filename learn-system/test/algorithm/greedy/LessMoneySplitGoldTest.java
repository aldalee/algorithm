package algorithm.greedy;

import org.junit.Test;

import java.util.Arrays;

import static algorithm.greedy.LessMoneySplitGold.bruteForceEnumerate;
import static algorithm.greedy.LessMoneySplitGold.lessMoney;
import static tool.ArraysUtil.generateRandomArray;
import static org.junit.Assert.fail;

public class LessMoneySplitGoldTest {
    @Test
    public void testLessMoney() {
        int maxSize = 6;
        int maxValue = 1000;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int expected = bruteForceEnumerate(arr);
            int actual = lessMoney(arr);
            if (expected != actual) {
                System.out.println("Arr = " + Arrays.toString(arr));
                System.out.println("Expected = " + expected);
                System.out.println("Actual = " + actual);
                fail();
            }
        }
        System.out.println("Accepted!");
    }

    @Test
    public void testBoundary() {
        // int[] arr = null;
        int[] arr = {};
        // int[] arr = {10};
        int expected = bruteForceEnumerate(arr);
        int actual = lessMoney(arr);
        if (expected != actual) {
            System.out.println("Arr = " + Arrays.toString(arr));
            System.out.println("Expected = " + expected);
            System.out.println("Actual = " + actual);
            fail();
        }
        System.out.println("Accepted!");
    }
}
