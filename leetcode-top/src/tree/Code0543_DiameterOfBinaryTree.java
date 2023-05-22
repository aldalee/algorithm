package tree;

/**
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 * 二叉树的直径
 * @author HuanyuLee
 * @date 2023/5/22
 */
public class Code0543_DiameterOfBinaryTree {
    int maxDist = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxDist;
    }

    private int depth(TreeNode x) {
        if (x == null) {
            return 0;
        }
        int leftSubtree = depth(x.left);
        int rightSubtree = depth(x.right);
        maxDist = Math.max(leftSubtree + rightSubtree, maxDist);
        return Math.max(leftSubtree, rightSubtree) + 1;
    }
}
