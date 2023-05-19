package tree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://leetcode.cn/problems/maximum-width-of-binary-tree/
 * 二叉树最大宽度
 * @author HuanyuLee
 * @date 2023/5/19
 */
public class Code0662_MaximumWidthOfBinaryTree {
    Map<Integer, Integer> levelMin = new HashMap<>();

    /**
     * 深度优先搜索 递归版本
     * @param root 二叉树根节点
     * @return 二叉树最大宽度
     */
    public int widthOfBinaryTree(TreeNode root) {
        return DFS(root, 1, 1);
    }

    private int DFS(TreeNode root, int depth, int index) {
        if (root == null) {
            return 0;
        }
        // 每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值
        levelMin.putIfAbsent(depth, index);
        return Math.max(index - levelMin.get(depth) + 1,
                Math.max(
                        DFS(root.left, depth + 1, index * 2),
                        DFS(root.right, depth + 1, index * 2 + 1)
                ));
    }

    /**
     * 广度优先搜索 非递归版本
     * @param root 二叉树根节点
     * @return 二叉树最大宽度
     */
    public int widthOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int width = 1;
        Deque<Info> queue = new LinkedList<>();
        queue.offer(new Info(root, 1));
        while (!queue.isEmpty()) {
            int size = queue.size();
            width = Math.max(width, queue.peekLast().index - queue.peekFirst().index + 1);
            for (int i = 0; i < size; i++) {
                Info info = queue.pollFirst();
                if (info.node.left != null) {
                    queue.offer(new Info(info.node.left, 2 * info.index));
                }
                if (info.node.right != null) {
                    queue.offer(new Info(info.node.right, 2 * info.index + 1));
                }
            }
        }
        return width;
    }

    static class Info {
        public TreeNode node;
        public int index;

        public Info(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
