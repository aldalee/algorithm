package algorithm.greedy;

import org.junit.Test;

import static algorithm.greedy.MinimumLightsNumber.*;
import static org.junit.Assert.fail;

public class MinimumLightsNumberTest {
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    @Test
    public void testMinLights() {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String road = randomString(len);
            int expected = bruteForce(road);
            int actual = minLights(road);
            if (expected != actual) {
                System.out.println("Road = " + road);
                System.out.println("Expected = " + expected);
                System.out.println("Actual = " + actual);
                fail();
            }
        }
        System.out.println("Accepted!");
    }

    @Test
    public void testMinLight() {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String road = randomString(len);
            int expected = bruteForce(road);
            int actual = minLight(road);
            if (expected != actual) {
                System.out.println("Road = " + road);
                System.out.println("Expected = " + expected);
                System.out.println("Actual = " + actual);
                fail();
            }
        }
        System.out.println("Accepted!");
    }
}
