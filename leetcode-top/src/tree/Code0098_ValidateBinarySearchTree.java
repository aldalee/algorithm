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

    //课上代码
    static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    Info f(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info L = f(x.left);
        Info R = f(x.right);
        int max = x.val;
        int min = x.val;
        if (L != null) {
            max = Math.max(L.max, max);
            min = Math.min(L.min, min);
        }
        if (R != null) {
            max = Math.max(R.max, max);
            min = Math.min(R.min, min);
        }
        boolean isBST = false;
        boolean leftIsBST = L == null || L.isBST;
        boolean rightIsBST = R == null || R.isBST;
        boolean leftMaxLessX = L == null || (L.max < x.val);
        boolean rightMinMoreX = R == null || (R.min > x.val);
        if (leftIsBST && rightIsBST && leftMaxLessX && rightMinMoreX) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }
}
