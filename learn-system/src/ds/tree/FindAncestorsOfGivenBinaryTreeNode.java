package ds.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 求二叉树给定节点的所有祖先节点
 * @author HuanyuLee
 * @date 2023/5/17
 */
public class FindAncestorsOfGivenBinaryTreeNode {
    /**
     * 递归实现
     * @param root   树的根节点
     * @param target 查找的目标节点
     * @return target的所有祖先节点值的集合
     */
    public List<Integer> findAncestors(TreeNode root, TreeNode target) {
        List<Integer> ancestors = new ArrayList<>();
        find(root, target, ancestors);
        return ancestors;
    }

    private boolean find(TreeNode root, TreeNode target, List<Integer> ancestors) {
        if (root == null) return false;
        if (root == target) return true;
        if (find(root.left, target, ancestors) || find(root.right, target, ancestors)) {
            ancestors.add(root.val);
            return true;
        }
        return false;
    }

    /**
     * 非递归实现
     * https://www.prepbytes.com/blog/tree/print-ancestors-of-a-given-binary-tree-node-without-recursion/
     * @param root   树的根节点
     * @param target 查找的目标节点
     * @return target的所有祖先节点值的集合
     */
    public List<Integer> getAncestors(TreeNode root, TreeNode target) {
        List<Integer> ancestors = new ArrayList<>();
        if (root == null) {
            return ancestors;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        // 后序遍历整个树，直到找到目标
        while (true) {
            while (cur != null && cur != target) {      // 遍历左边界，将所有节点压入栈中
                stack.push(cur);
                cur = cur.left;
            }
            if (cur != null) break;                     // 如果找到target节点，则停止
            if (stack.peek().right == null) {           // 如果栈顶节点不包含右子树，出栈
                cur = stack.pop();
                // 如果弹出的节点是栈顶的右孩子，则出栈。因为顶部的左孩子之前处理过
                while (!stack.empty() && stack.peek().right == cur) {
                    cur = stack.pop();
                }
            }
            // 如果堆栈不为空，则只需将cur设置为顶部的右孩子并开始遍历右子树。
            cur = stack.empty() ? null : stack.peek().right;
        }
        while (!stack.empty()) {
            ancestors.add(stack.pop().val);
        }
        return ancestors;
    }
}
