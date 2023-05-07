package algorithm.sorting;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static tool.ArraysUtil.*;

public class SortArrayDistanceLessKTest {
    private static final int VALUE = 9999;
    private static final int SIZE = 10000;
    private static final int TEST_CASES = 100000;

    private SortArrayDistanceLessK s;

    @Before
    public void init() {
        s = new SortArrayDistanceLessK();
    }

    @Test
    public void testSortArrayDistanceLessK() {
        for (int i = 0; i < TEST_CASES; i++) {
            int K = (int) (Math.random() * SIZE) + 1;
            int[] arr1 = randomArrayNoMoveMoreK(SIZE, VALUE, K);
            int[] arr2 = copyArray(arr1);
            s.sortedArrayDistanceLessK(arr1, K);
            comparator(arr2);
            assertArrayEquals(arr1, arr2);
        }
    }
}
