package ds.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
 * 判断一棵二叉树是否是完全二叉树
 * @author HuanyuLee
 * @date 2023/5/22
 */
public class CompleteBinaryTree {
    /**
     * 层序遍历判断是否是完全二叉树
     * @param root 二叉树根节点
     * @return 是否是完全二叉树
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.push(root);

        boolean leaf = false;           // 是否遇到过左右两个孩子不双全的节点
        TreeNode left, right;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            left = cur.left;
            right = cur.right;
            if ((left == null && right != null) || (leaf && left != null)) {
                return false;
            }
            if (left != null) queue.add(left);
            if (right != null) queue.add(right);
            if (left == null || right == null) {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 递归套路判断是否是完全二叉树
     * @param root 二叉树根节点
     * @return 是否是完全二叉树
     */
    public boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isCBT;
    }

    static class Info {
        boolean isFull;
        boolean isCBT;
        int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;

        // boolean p1 = isFull;
        boolean p2 = leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height;
        boolean p3 = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1;
        boolean p4 = leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1;
        boolean isCBT = isFull || p2 || p3 || p4;

        return new Info(isFull, isCBT, height);
    }
}
