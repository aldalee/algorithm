package ds.tree;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static tool.BinaryTreeUtil.generateRandomBST;

public class LargestBSTSubtreeTest {
    final int MAX_LEVEL = 30;
    final int MAX_VALUE = 100;
    final int TEST_CASES = 1000000;
    LargestBSTSubtree tree = new LargestBSTSubtree();

    @Test
    public void testDiameterOfBinaryTree() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBST(MAX_LEVEL, MAX_VALUE);
            int subtree = tree.largestBSTSubtree(root);
            int subtree2 = tree.largestBSTSubtree2(root);
            assertEquals(subtree, subtree2);
        }
    }
}
