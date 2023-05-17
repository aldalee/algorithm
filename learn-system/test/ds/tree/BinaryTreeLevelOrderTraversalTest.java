package ds.tree;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static tool.BinaryTreePrinter.printTree;

public class BinaryTreeLevelOrderTraversalTest {
    BinaryTreeLevelOrderTraversal bt = new BinaryTreeLevelOrderTraversal();
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
    }

    @Test
    public void testLevelOrder() {
        List<Integer> levelOrder = bt.levelOrder(root);
        printTree(root);
        System.out.println("levelOrder = " + levelOrder);
        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8), levelOrder);
    }
}
