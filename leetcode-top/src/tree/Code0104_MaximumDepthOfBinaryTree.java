package tree;

/**
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 * 二叉树的最大深度
 * @author HuanyuLee
 * @date 2023/4/16
 */
public class Code0104_MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
