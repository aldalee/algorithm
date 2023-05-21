package ds.tree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大宽度
 * 注意: 本代码没有计算空节点，如果计算空节点，参见LeetCode第662题
 * @author HuanyuLee
 * @date 2023/5/19
 */
public class MaximumWidthOfBinaryTree {
    /**
     * 根据层序遍历改写
     * @param root 二叉树根节点
     * @return 二叉树最大宽度
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int width = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int level = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level++;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            width = Math.max(width, level);
        }
        return width;
    }

    /**
     * 使用容器的方法
     * @param root 二叉树根节点
     * @return 二叉树最大宽度
     * @author 左程云
     */
    public int widthOfBinaryTreeUseMap(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // key在哪一层，value
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(root, 1);
        int curLevel = 1;           // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0;      // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    /**
     * 使用有限变量的方法
     * @param root 二叉树根节点
     * @return 二叉树最大宽度
     * @author 左程云
     */
    public int widthOfBinaryTreeNoMap(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curEnd = root;         // 当前层，最右节点是谁
        TreeNode nextEnd = null;        // 下一层，最右节点是谁
        int width = 0;
        int curLevelNodes = 0;          // 当前层的节点数
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                width = Math.max(width, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return width;
    }
}
