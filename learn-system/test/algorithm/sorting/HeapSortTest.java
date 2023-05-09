package algorithm.sorting;

import org.junit.Test;

import static algorithm.sorting.HeapSort.heapSort;
import static org.junit.Assert.assertArrayEquals;
import static tool.ArraysUtil.*;

public class HeapSortTest {
    private static final int VALUE = 9999;
    private static final int SIZE = 10000;
    private static final int TEST_CASES = 100000;

    @Test
    public void testHeapSort() {
        for (int i = 0; i < TEST_CASES; i++) {
            int[] arr1 = generateRandomArray(SIZE, VALUE);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            assertArrayEquals(arr1, arr2);
        }
    }
}
