package ds.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中的中序后继
 * @author HuanyuLee
 * @date 2023/5/21
 */
public class InorderSuccessorInBST {
    /**
     * 优化暴力解
     * @param x 给定的二叉树节点
     * @return 该节点x的后继节点
     */
    public Node inorderSuccessor(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right != null) {                  // 有右子树
            Node successor = x.right;
            // 右树的最左孩子
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        } else {                                // 无右子树
            Node parent = x.parent;
            // 当前节点是其父亲节点的右孩子
            while (parent != null && parent.right == x) {
                x = parent;
                parent = x.parent;
            }
            return parent;
        }
    }

    /**
     * 按照定义求解（暴力解）
     * @param root 二叉树根节点
     * @param x    给定的二叉树节点
     * @return 该节点x的后继节点
     */
    public Node getSuccessorNode(Node root, Node x) {
        if (root == null || x == null) {
            return null;
        }
        List<Node> inorder = inTraversal(root);
        int n = inorder.size();
        Node node = null;
        for (int i = 0; i < n - 1; i++) {
            if (x == inorder.get(i)) {
                node = inorder.get(i + 1);
                break;
            }
        }
        return x == inorder.get(n - 1) ? null : node;
    }

    public List<Node> inTraversal(Node root) {
        List<Node> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(Node root, List<Node> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root);
        inorder(root.right, res);
    }
}