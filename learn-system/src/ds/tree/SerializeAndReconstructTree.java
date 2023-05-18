package ds.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树序列化和反序列化
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化
 * 注意:
 *    二叉树无法通过中序遍历的方式实现序列化和反序列化，
 *    因为不同的两棵树，可能得到同样的中序序列，即便补了空位置
 *                     2       1
 *                    /         \
 *                   1           2
 *    中序序列化结果都为[null, 1, null, 2, null]
 * @author HuanyuLee
 * @date 2023/5/17
 */
public class SerializeAndReconstructTree {
    /**
     * 先序序列化
     * @param root 二叉树的根节点
     * @return 序列化结果Queue
     */
    public Queue<String> preorderSerialize(TreeNode root) {
        Queue<String> ans = new LinkedList<>();
        pres(root, ans);
        return ans;
    }

    private void pres(TreeNode root, Queue<String> ans) {
        if (root == null) {
            ans.add(null);  // ans.add("#");
            return;
        }
        ans.add(String.valueOf(root.val));
        pres(root.left, ans);
        pres(root.right, ans);
    }

    /**
     * 根据先序序列化结果进行反序列化
     * @param preList 先序序列化序列
     * @return 二叉树根节点
     */
    public TreeNode preorderDeserialize(Queue<String> preList) {
        if (preList == null || preList.size() == 0) {
            return null;
        }
        return preb(preList);
    }

    private TreeNode preb(Queue<String> preList) {
        String value = preList.poll();
        if (value == null) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(value));
        root.left = preb(preList);
        root.right = preb(preList);
        return root;
    }

    /* ====================================================================================== */

    /**
     * 层序序列化
     * @param root 二叉树的根节点
     * @return 序列化结果Queue
     */
    public Queue<String> levelOrderSerialize(TreeNode root) {
        Queue<String> ans = new LinkedList<>();
        if (root == null) {
            ans.add(null);
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        ans.add(String.valueOf(root.val));
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // 如果左孩子不为空，则将其序列化并加入队列，否则仅序列化
            if (cur.left != null) {
                ans.add(String.valueOf(cur.left.val));
                queue.offer(cur.left);
            } else {
                ans.offer(null);
            }
            // 如果右孩子不为空，则将其序列化并加入队列，否则仅序列化
            if (cur.right != null) {
                ans.add(String.valueOf(cur.right.val));
                queue.offer(cur.right);
            } else {
                ans.offer(null);
            }
        }
        return ans;
    }

    /**
     * 根据层序序列化结果进行反序列化
     * @param levelList 层序序列化序列
     * @return 二叉树根节点
     */
    public TreeNode levelOrderDeserialize(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        TreeNode root = generateNode(levelList.poll());
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            cur.left = generateNode(levelList.poll());
            cur.right = generateNode(levelList.poll());
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return root;
    }

    private TreeNode generateNode(String val) {
        return val == null ? null : new TreeNode(Integer.parseInt(val));
    }

    /* ====================================================================================== */

    /**
     * 后序序列化
     * @param root 二叉树的根节点
     * @return 序列化结果Queue
     */
    public Queue<String> postOrderSerialize(TreeNode root) {
        Queue<String> ans = new LinkedList<>();
        poss(root, ans);
        return ans;
    }

    private void poss(TreeNode root, Queue<String> ans) {
        if (root == null) {
            ans.add(null);
            return;
        }
        poss(root.left, ans);
        poss(root.right, ans);
        ans.add(String.valueOf(root.val));
    }

    /**
     * 根据后序序列化结果进行反序列化
     * @param posList 后序序列化序列
     * @return 二叉树根节点
     */
    public TreeNode postOrderDeserialize(Queue<String> posList) {
        if (posList == null || posList.size() == 0) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!posList.isEmpty()) {
            stack.push(posList.poll());
        }
        return posb(stack);
    }

    private TreeNode posb(Stack<String> posStack) {
        String value = posStack.pop();
        if (value == null) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.right = posb(posStack);
        head.left = posb(posStack);
        return head;
    }
}
