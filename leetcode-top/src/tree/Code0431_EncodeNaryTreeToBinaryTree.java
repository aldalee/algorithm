package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree/ （付费）
 * https://www.lintcode.com/problem/1530/ （免费）
 * 将N叉树编码为二叉树
 * @author HuanyuLee
 * @date 2023/5/17
 */
public class Code0431_EncodeNaryTreeToBinaryTree {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // Encode an n-ary tree to a binary tree
    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.val);
        // root所有的孩子挂在head左树右边界上
        head.left = en(root.children);
        return head;
    }

    private TreeNode en(List<Node> children) {
        TreeNode root = null;
        TreeNode cur = null;
        // 深度优先遍历
        for (Node child : children) {
            TreeNode node = new TreeNode(child.val);
            if (root == null) {
                root = node;
            } else {
                cur.right = node;
            }
            cur = node;
            cur.left = en(child.children);
        }
        return root;
    }

    // Decode your binary tree to an n-ary tree
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        return new Node(root.val, de(root.left));
    }

    private List<Node> de(TreeNode root) {
        List<Node> children = new ArrayList<>();
        while (root != null) {
            Node cur = new Node(root.val, de(root.left));
            children.add(cur);
            root = root.right;
        }
        return children;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));