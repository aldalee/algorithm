package ds.tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static tool.BinaryTreeUtil.generateRandomBST;

public class MaximumWidthOfBinaryTreeTest {
    final int MAX_LEVEL = 20;
    final int MAX_VALUE = 100;
    final int TEST_CASES = 1000000;
    MaximumWidthOfBinaryTree tree = new MaximumWidthOfBinaryTree();

    @Test
    public void test() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode head = generateRandomBST(MAX_LEVEL, MAX_VALUE);
            int width = tree.widthOfBinaryTree(head);
            int width2 = tree.widthOfBinaryTreeUseMap(head);
            int width3 = tree.widthOfBinaryTreeNoMap(head);
            assertEquals(width, width2);
            assertEquals(width, width3);
            assertEquals(width2, width3);
        }
    }
}
