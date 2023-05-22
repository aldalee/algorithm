package ds.tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static tool.BinaryTreeUtil.generateRandomBST;

public class DiameterOfBinaryTreeTest {
    final int MAX_LEVEL = 20;
    final int MAX_VALUE = 100;
    final int TEST_CASES = 100000;
    DiameterOfBinaryTree tree = new DiameterOfBinaryTree();

    @Test
    public void testDiameterOfBinaryTree() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBST(MAX_LEVEL, MAX_VALUE);
            int dist1 = tree.maxDistance(root);
            int dist2 = tree.diameterOfBinaryTree(root);
            assertEquals(dist1, dist2);
        }
    }
}
