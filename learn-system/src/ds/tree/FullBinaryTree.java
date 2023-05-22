package ds.tree;

/**
 * 判断一棵二叉树是否是满二叉树
 * @author HuanyuLee
 * @date 2023/5/22
 */
public class FullBinaryTree {
    /**
     * 按照满二叉树的定义（性质）判断
     * 只有满二叉树满足: 2^h - 1 == n
     */
    public boolean isFull(TreeNode root) {
        if (root == null) {
            return true;
        }
        Info all = process(root);
        return (1 << all.height) - 1 == all.nodes;
    }

    static class Info {
        int height;
        int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftSubtree = process(x.left);
        Info rightSubtree = process(x.right);
        int height = Math.max(leftSubtree.height, rightSubtree.height) + 1;
        int nodes = leftSubtree.nodes + rightSubtree.nodes + 1;
        return new Info(height, nodes);
    }

    /**
     * 搜集子树是否是满二叉树、子树的高度
     * 左子树是满二叉树 && 右子树是满二叉树 && 左右子树高度一样
     */
    public boolean isFull2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process2(root).isFull;
    }

    static class Info2 {
        boolean isFull;
        int height;

        public Info2(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    private Info2 process2(TreeNode x) {
        if (x == null) {
            return new Info2(true, 0);
        }
        Info2 leftSubtree = process2(x.left);
        Info2 rightSubtree = process2(x.right);
        boolean isFull = leftSubtree.isFull && rightSubtree.isFull && leftSubtree.height == rightSubtree.height;
        int height = Math.max(leftSubtree.height, rightSubtree.height) + 1;
        return new Info2(isFull, height);
    }
}
