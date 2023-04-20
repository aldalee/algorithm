package tree;

/**
 * https://leetcode.cn/problems/path-sum/
 * 路径求和
 * @author HuanyuLee
 * @date 2023/4/20
 */
public class Code0112_PathSum {
    public boolean hasPathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == target;
        }
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    //方法二: 课上代码
    //调用时:  f(root, 0, target); return isSum;
    boolean isSum = false;

    void f(TreeNode x, int preSum, int targetSum) {
        if (x.left == null && x.right == null) {
            isSum |= x.val + preSum == targetSum;   //注意，必须是 |=
        }
        //非叶节点
        preSum += x.val;
        if (x.left != null) {
            f(x.left, preSum, targetSum);
        }
        if (x.right != null) {
            f(x.right, preSum, targetSum);
        }
    }
}
