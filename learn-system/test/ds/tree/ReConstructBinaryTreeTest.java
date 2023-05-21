package ds.tree;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static tool.BinaryTreeUtil.*;

public class ReConstructBinaryTreeTest {
    final int MAX_LEVEL = 10;
    final int MAX_VALUE = 1000;
    final int TEST_CASES = 100000;
    ReConstructBinaryTree tree = new ReConstructBinaryTree();

    @Test
    public void testBuildTreeByPreorderAndInorder() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBT(MAX_LEVEL, MAX_VALUE);
            int[] preorder = preorder(root);
            int[] inorder = inorder(root);
            TreeNode head = tree.buildTree(preorder, inorder);
            assertTrue(isSameTree(root, head));
        }
    }

    @Test
    public void testBuildTreeByPreorderAndInorder2() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBT(MAX_LEVEL, MAX_VALUE);
            int[] preorder = preorder(root);
            int[] inorder = inorder(root);
            TreeNode head = tree.buildBinaryTree(preorder, inorder);
            assertTrue(isSameTree(root, head));
        }
    }

    @Test
    public void testBuildTreeByPreorderAndInorderNoRec() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBT(MAX_LEVEL, MAX_VALUE);
            int[] preorder = preorder(root);
            int[] inorder = inorder(root);
            TreeNode head = tree.buildTreeNoRec(preorder, inorder);
            assertTrue(isSameTree(root, head));
        }
    }

    @Test
    public void testBuildTreeByInorderAndPostorder() {
        for (int i = 0; i < TEST_CASES; i++) {
            TreeNode root = generateRandomBT(MAX_LEVEL, MAX_VALUE);
            int[] inorder = inorder(root);
            int[] postorder = postorder(root);
            TreeNode head = tree.buildBT(inorder, postorder);
            assertTrue(isSameTree(root, head));
        }
    }

    @Test
    public void test() {
        // 如果有相同值，则不能通过测试
        int[] inorder = {42, 92, 69, 39, 17, 51, 42};
        int[] postorder = {92, 39, 69, 17, 42, 42, 51};
        TreeNode head = tree.buildBT(inorder, postorder);
        printTree(head);                    // test
    }
}
