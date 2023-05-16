package ds.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归实现二叉树先序、中序、后序遍历
 * 测试链接:
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/
 * @author HuanyuLee
 * @date 2023/5/16
 */
public class RecursiveTraversalOfBinaryTree {
    // 测试时请修改该函数的名字
    public List<Integer> traversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        inorder(root, res);
        postorder(root, res);
        return res;
    }

    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    private void postorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }

    /**
     * 递归遍历模板
     * @param root 二叉树根节点
     */
    private static void f(TreeNode root) {
        if (root == null) return;
        // 1 preorder
        f(root.left);
        // 2 inorder
        f(root.right);
        // 3 postorder
    }
}
