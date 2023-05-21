package ds.tree;

/**
 * https://leetcode.cn/problems/balanced-binary-tree/
 * 平衡二叉树
 * 学习本题的递归套路，今后应用
 * @author HuanyuLee
 * @date 2023/5/21
 */
public class BalancedBinaryTree {
    static class Info {
        boolean balance;
        int height;

        public Info(boolean balance, int height) {
            this.balance = balance;
            this.height = height;
        }
    }

    /**
     * 判断是否是平衡二叉树
     * @param root 根节点
     * @return boolean
     */
    public boolean isBalanced(TreeNode root) {
        return process(root).balance;
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return new Info(true, 0);
        }
        Info leftSubtree = process(x.left);
        Info rightSubtree = process(x.right);
        int height = Math.max(leftSubtree.height, rightSubtree.height) + 1;
        boolean isBalanced = leftSubtree.balance && rightSubtree.balance
                && Math.abs(leftSubtree.height - rightSubtree.height) <= 1;
        return new Info(isBalanced, height);
    }
}
