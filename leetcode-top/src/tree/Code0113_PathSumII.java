package tree;

import java.util.*;

/**
 * https://leetcode.cn/problems/path-sum-ii
 * 路径总和 II
 * @author HuanyuLee
 * @date 2023/4/20
 */
public class Code0113_PathSumII {
    List<List<Integer>> ans = new LinkedList<>();
    Deque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        DFS(root, targetSum);
        return ans;
    }

    //方法一: LeetCode
    void DFS(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            ans.add(new LinkedList<>(path));
        }
        DFS(root.left, target);
        DFS(root.right, target);
        path.pollLast();
    }

    //方法二: 课上代码
    List<List<Integer>> res = new LinkedList<>();

    /* public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return res;
        }
        List<Integer> path = LinkedList <>();
        f(root, path, 0, targetSum);
        return ans;
    } */

    void f(TreeNode x, List<Integer> path, int preSum, int targetSum) {
        if (x.left == null && x.right == null && preSum + x.val == targetSum) {
            path.add(x.val);
            res.add(List.copyOf(path));
            path.remove(path.size() - 1);
            return;
        }
        path.add(x.val);
        preSum += x.val;
        if (x.left != null) {
            f(x.left, path, preSum, targetSum);
        }
        if (x.right != null) {
            f(x.right, path, preSum, targetSum);
        }
        path.remove(path.size() - 1);
    }
}
