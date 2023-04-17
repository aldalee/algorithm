/**
 * https://leetcode.cn/problems/symmetric-tree
 * 对称二叉树
 * @author HuanyuLee
 * @date 2023/4/16
 */
public class Code0101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public static boolean isMirror(TreeNode h1, TreeNode h2) {
        if (h1 == null && h2 == null) {
            return true;
        }
        if (h1 == null ^ h2 == null) {
            return false;
        }
        return h1.val == h2.val && isMirror(h1.left, h2.right) && isMirror(h1.right, h2.left);
    }
}
