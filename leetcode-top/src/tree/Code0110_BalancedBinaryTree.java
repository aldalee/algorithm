package tree;

/**
 * https://leetcode.cn/problems/balanced-binary-tree/
 * 平衡二叉树
 * @author HuanyuLee
 * @date 2023/4/19
 */
public class Code0110_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode root) {
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
