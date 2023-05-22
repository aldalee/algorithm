package ds.tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static tool.BinaryTreeUtil.generateRandomBST;

public class CompleteBinaryTreeTest {
    final int MAX_LEVEL = 4;
    final int MAX_VALUE = 100;
    final int TEST_CASES = 100000;
    CompleteBinaryTree tree = new CompleteBinaryTree();

    @Test
    public void testIsCompleteBinaryTree() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBST(MAX_LEVEL, MAX_VALUE);
            boolean ans = tree.isCompleteTree(root);
            boolean res = tree.isCompleteBinaryTree(root);
            assertEquals(ans, res);
        }
    }
}
