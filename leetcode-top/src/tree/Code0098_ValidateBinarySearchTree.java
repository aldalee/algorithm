package tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/
 * 验证二叉搜索树
 * @author HuanyuLee
 * @date 2023/4/19
 */
public class Code0098_ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //方法一: 递归
    boolean isBST(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }
        return isBST(root.left, lower, root.val) && isBST(root.right, root.val, upper);
    }

    //方法二: 中序遍历
    boolean inOrderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        double inorder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}
