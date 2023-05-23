package algorithm.greedy;

import org.junit.Test;

import static algorithm.greedy.LowestLexicography.bruteForceSolution;
import static algorithm.greedy.LowestLexicography.lowestString;
import static org.junit.Assert.assertEquals;
import static tool.StringUtil.copyStringArray;
import static tool.StringUtil.generateRandomStringArray;

public class LowestLexicographyTest {
    int arrLen = 10;
    int strLen = 10;
    int testTimes = 100000;

    @Test
    public void testLowestString() {
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            String string1 = lowestString(arr1);
            String string2 = bruteForceSolution(arr2);
            assertEquals(string2, string1);
        }
    }
}
