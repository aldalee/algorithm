package tree;

/**
 * https://leetcode.cn/problems/balanced-binary-tree/
 * 平衡二叉树
 * @author HuanyuLee
 * @date 2023/4/19
 */
public class Code0110_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return f(root).balance;
    }
    //方法一: 课上代码
    static class Info {
        boolean balance;
        int height;

        public Info(boolean balance, int height) {
            this.balance = balance;
            this.height = height;
        }
    }

    Info f(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info L = f(root.left);
        Info R = f(root.right);
        int height = Math.max(L.height, R.height) + 1;
        boolean isBalanced = L.balance && R.balance && Math.abs(L.height - R.height) <= 1;
        return new Info(isBalanced, height);
    }

    //方法2: LeetCode 效率更高
    //调用时: return height(root) >= 0;
    int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int LH = height(root.left);
        int RH = height(root.right);
        if (LH == -1 || RH == -1 || Math.abs(LH - RH) > 1) {
            return -1;
        } else {
            return Math.max(LH, RH) + 1;
        }
    }
}
