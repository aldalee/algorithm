package ds.tree;

/**
 * 最大的BST子树
 * 学习本题的递归套路，今后应用
 * @author HuanyuLee
 * @date 2023/5/22
 */
public class LargestBSTSubtree {
    /**
     * 递归套路
     */
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxBSTSubtreeSize;
    }

    static class Info {
        int maxBSTSubtreeSize;
        int allSize;
        int min;
        int max;

        public Info(int maxBSTSubtreeSize, int allSize, int min, int max) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
            this.min = min;
            this.max = max;
        }
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int allSize = 1;
        int min = x.val;
        int max = x.val;
        int p1 = -1;    // 可能性p1: 不经过X，在左子树中找
        int p2 = -1;    // 可能性p2: 不经过X，在右子树中找
        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
            allSize += leftInfo.allSize;
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
            allSize += rightInfo.allSize;
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;    // 可能性p3: 经过X，在以X为根的整棵树中找
        boolean isLeftBST = leftInfo == null || (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean isRightBST = rightInfo == null || (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if (isLeftBST && isRightBST) {
            boolean isLeftMaxLessX = leftInfo == null || (leftInfo.max < x.val);
            boolean isRightMoreX = rightInfo == null || (rightInfo.min > x.val);
            if (isLeftMaxLessX && isRightMoreX) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        int maxBSTSubtreeSize = Math.max(Math.max(p1, p2), p3);     // 三种可能性做PK
        return new Info(maxBSTSubtreeSize, allSize, min, max);
    }

    /**
     * https://segmentfault.com/a/1190000007222101
     */
    public int largestBSTSubtree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isValidBST(root)) {
            return largestBSTSubtree2(root.left) + largestBSTSubtree2(root.right) + 1;
        } else {
            return Math.max(largestBSTSubtree2(root.left), largestBSTSubtree2(root.right));
        }
    }

    private boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }
}
