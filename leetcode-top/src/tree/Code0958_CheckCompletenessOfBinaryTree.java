package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
 * 二叉树的完全性检验
 * @author HuanyuLee
 * @date 2023/5/21
 */
public class Code0958_CheckCompletenessOfBinaryTree {
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
}
