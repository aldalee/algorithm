package ds.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.assertTrue;
import static tool.BinaryTreeUtil.printTree;

public class SerializeAndReconstructTreeTest {
    SerializeAndReconstructTree bt = new SerializeAndReconstructTree();
    TreeNode root;

    @Before
    public void initBT() {
        root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        root.right.left.left = new TreeNode(8);
        printTree(root);
    }

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
        Queue<String> queue = bt.preorderSerialize(root);
        System.out.println("queue = " + queue);

        TreeNode head = bt.preorderDeserialize(queue);
        assertTrue(isSameTree(root, head));
    }

    @Test
    public void testLevelOrderSerialize() {
        Queue<String> queue = bt.levelOrderSerialize(root);
        System.out.println("queue = " + queue);

        TreeNode head = bt.levelOrderDeserialize(queue);
        assertTrue(isSameTree(root, head));
    }

    @Test
    public void testPostOrderSerialize() {
        Queue<String> queue = bt.postOrderSerialize(root);
        System.out.println("queue = " + queue);

        TreeNode head = bt.postOrderDeserialize(queue);
        assertTrue(isSameTree(root, head));
    }
}
