package ds.heap;

import org.junit.Test;

import static ds.heap.MaximumLineSegmentCoincidenceProblem.*;
import static org.junit.Assert.assertEquals;
import static tool.ArraysUtil.generateLines;

public class MaximumLineSegmentCoincidenceProblemTest {
    private static final int N = 100;
    private static final int L = 0;
    private static final int R = 200;
    private static final int TEST_CASES = 200000;

    @Test
    public void testMaxCover() {
        for (int i = 0; i < TEST_CASES; i++) {
            int[][] lines = generateLines(N, L, R);
            assertEquals(bruteForce(lines), maxCover(lines));
        }
    }
}
