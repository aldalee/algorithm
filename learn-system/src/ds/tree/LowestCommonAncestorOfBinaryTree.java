package ds.tree;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 * 二叉树的最近公共祖先
 * @author HuanyuLee
 * @date 2023/5/22
 */
public class LowestCommonAncestorOfBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).ans;
    }

    static class Info {
        boolean findA;
        boolean findB;
        TreeNode ans;

        public Info(boolean findA, boolean findB, TreeNode ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    private Info process(TreeNode x, TreeNode a, TreeNode b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);

        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        TreeNode ans = null;                    // 可能性p0: x不是答案，a和b不同时存在
        if (leftInfo.ans != null) {             // 可能性p1: x不是答案，左子树有答案
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {     // 可能性p2: x不是答案，右子树有答案
            ans = rightInfo.ans;
        } else {
            if (findA && findB) ans = x;        // 可能性p3: x是答案，a和b必定同时存在
        }
        return new Info(findA, findB, ans);
    }
}
