package ds.tree;

import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.assertTrue;
import static tool.BinaryTreeUtil.generateRandomBST;

public class SerializeAndReconstructTreeTest {
    final int MAX_LEVEL = 6;
    final int MAX_VALUE = 100;
    final int TEST_CASES = 100000;
    SerializeAndReconstructTree bt = new SerializeAndReconstructTree();

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    @Test
    public void testPreorderSerialize() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBST(MAX_LEVEL,MAX_VALUE);
            Queue<String> preQueue = bt.preorderSerialize(root);
            TreeNode head = bt.preorderDeserialize(preQueue);
            assertTrue(isSameTree(root, head));
        }
    }

    @Test
    public void testLevelOrderSerialize() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBST(MAX_LEVEL,MAX_VALUE);
            Queue<String> preQueue = bt.levelOrderSerialize(root);
            TreeNode head = bt.levelOrderDeserialize(preQueue);
            assertTrue(isSameTree(root, head));
        }
    }

    @Test
    public void testPostOrderSerialize() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBST(MAX_LEVEL,MAX_VALUE);
            Queue<String> preQueue = bt.postOrderSerialize(root);
            TreeNode head = bt.postOrderDeserialize(preQueue);
            assertTrue(isSameTree(root, head));
        }
    }
}
