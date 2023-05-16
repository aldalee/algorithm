package ds.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 非递归实现二叉树先序、中序、后序遍历
 * 测试链接:
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/
 * @author HuanyuLee
 * @date 2023/5/16
 */
public class NonRecursiveTraversalOfBinaryTree {
    /**
     * 使用栈实现先序遍历
     * @param root 二叉树根节点
     * @return 遍历集合
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            root = stack.pop();
            res.add(root.val);      // 加入遍历到的节点
            // 有右先加右，有左再加左
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        return res;
    }

    /* ====================================================================================== */

    /**
     * 使用栈实现后序遍历
     * 方法一: 根据preorderTraversal的套路进行修改，使用两个栈
     * @param root 二叉树根节点
     * @return 遍历集合
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<TreeNode> ans = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            root = stack.pop();
            ans.push(root);     // ans栈得到后续遍历的逆序
            // 有左先加左，有右再加右
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        // ans栈弹出，得到后续遍历
        while (!ans.isEmpty()) {
            res.add(ans.pop().val);
        }
        return res;
    }

    /**
     * 使用栈实现后序遍历
     * 方法二: 使用一个栈
     * @param root 二叉树根节点
     * @return 遍历集合
     */
    public List<Integer> postorderTraversalII(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        TreeNode visited = root;    // 表示最近一次访问过的节点
        TreeNode cur;               // 表示栈顶节点
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.left != null && cur.left != visited && cur.right != visited) {
                // 如果左子节点不为空且未被访问过，将其入栈
                stack.push(cur.left);
            } else if (cur.right != null && cur.right != visited) {
                // 如果右子节点不为空且未被访问过，将其入栈
                stack.push(cur.right);
            } else {
                // 如果左右子节点都为空或已经被访问过时，将其出栈
                res.add(stack.pop().val);
                visited = cur;
            }
        }
        return res;
    }

    /* ====================================================================================== */

    /**
     * 使用栈实现中序遍历
     * @param root 二叉树根节点
     * @return 遍历集合
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    /* ====================================================================================== */

    /**
     * Morris遍历 空间复杂度O(1)
     * TODO: 这个太难了，不懂，后续课程弄懂（下面的代码来自LeetCode）
     * @param root 二叉树根节点
     * @return 遍历集合
     */
    public List<Integer> preorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        TreeNode p1 = root, p2;
        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    res.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                }
            } else {
                res.add(p1.val);
            }
            p1 = p1.right;
        }
        return res;
    }

    /**
     * Morris遍历 空间复杂度O(1)
     * TODO: 这个太难了，不懂，后续课程弄懂（下面的代码来自LeetCode）
     * @param root 二叉树根节点
     * @return 遍历集合
     */
    public List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor;

        while (root != null) {
            if (root.left != null) {
                // predecessor节点就是当前root节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // 让predecessor的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * Morris遍历 空间复杂度O(1)
     * TODO: 这个太难了，不懂，后续课程弄懂（下面的代码来自LeetCode）
     * @param root 二叉树根节点
     * @return 遍历集合
     */
    public List<Integer> postorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        TreeNode p1 = root, p2;
        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                    addPath(res, p1.left);
                }
            }
            p1 = p1.right;
        }
        addPath(res, root);
        return res;
    }

    private void addPath(List<Integer> res, TreeNode node) {
        int count = 0;
        while (node != null) {
            ++count;
            res.add(node.val);
            node = node.right;
        }
        int left = res.size() - count;
        int right = res.size() - 1;
        while (left < right) {
            int temp = res.get(left);
            res.set(left, res.get(right));
            res.set(right, temp);
            left++;
            right--;
        }
    }
}
