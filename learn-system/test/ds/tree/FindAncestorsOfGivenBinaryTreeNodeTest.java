package ds.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static tool.BinaryTreePrinter.printTree;

public class FindAncestorsOfGivenBinaryTreeNodeTest {
    FindAncestorsOfGivenBinaryTreeNode f = new FindAncestorsOfGivenBinaryTreeNode();
    TreeNode root, target;

    // @Before
    public void initBinaryTree() {
        root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;

        target = node9;
        // [5, 2, 1]
    }

    @Before
    public void initBinaryTree2() {
        root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        root.right.left.left = new TreeNode(8);

        target = root.right.left.left;
        // [5, 2, 0]
    }

    @Test
    public void findAncestors() {
        List<Integer> ancestors = f.findAncestors(root, target);
        List<Integer> ancestors2 = f.getAncestors(root, target);
        printTree(root);
        System.out.println("target = " + target.val);
        System.out.println("ancestors = " + ancestors);
        assertEquals(ancestors, ancestors2);
    }
}
