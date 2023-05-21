package ds.tree;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/
 * 二叉搜索树
 * 学习本题的递归套路，今后应用
 * @author HuanyuLee
 * @date 2023/5/21
 */
public class BinarySearchTree {
    static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    /**
     * 判断是否是二叉搜索树
     * @param root 根节点
     * @return boolean
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftSubtree = process(x.left);
        Info rightSubtree = process(x.right);
        int max = x.val;
        int min = x.val;
        if (leftSubtree != null) {
            max = Math.max(leftSubtree.max, max);
            min = Math.min(leftSubtree.min, min);
        }
        if (rightSubtree != null) {
            max = Math.max(rightSubtree.max, max);
            min = Math.min(rightSubtree.min, min);
        }
        boolean isBST = false;
        boolean leftIsBST = leftSubtree == null || leftSubtree.isBST;
        boolean rightIsBST = rightSubtree == null || rightSubtree.isBST;
        boolean leftMaxLessX = leftSubtree == null || (leftSubtree.max < x.val);
        boolean rightMinMoreX = rightSubtree == null || (rightSubtree.min > x.val);
        if (leftIsBST && rightIsBST && leftMaxLessX && rightMinMoreX) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }
}
