package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * 二叉树的先序遍历
 * @author HuanyuLee
 * @date 2023/5/16
 */
public class Code0144_BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    // 递归实现
    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }
}
