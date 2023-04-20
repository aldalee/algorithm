package tree;

/**
 * https://leetcode.cn/problems/same-tree/
 * 相同的树
 * @author HuanyuLee
 * @date 2023/4/16
 */
public class Code0100_SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
