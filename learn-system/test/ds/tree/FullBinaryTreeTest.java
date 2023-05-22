package ds.tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static tool.BinaryTreeUtil.generateRandomBST;

public class FullBinaryTreeTest {
    final int MAX_LEVEL = 20;
    final int MAX_VALUE = 100;
    final int TEST_CASES = 1000000;
    FullBinaryTree tree = new FullBinaryTree();

    @Test
    public void testDiameterOfBinaryTree() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBST(MAX_LEVEL, MAX_VALUE);
            boolean full = tree.isFull(root);
            boolean full2 = tree.isFull2(root);
            assertEquals(full2, full);
        }
    }
}
