package tree;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 从前序与中序遍历序列构造二叉树
 * @author HuanyuLee
 * @date 2023/4/17
 */
public class Code0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    HashMap<Integer, Integer> map = new HashMap<>();  //<value, index>

    public TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return f(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    /**
     * 利用先序数组和中序数组构建二叉树并返回根节点
     * @param pre 先序遍历数组
     * @param L1  pre的下标开始位置
     * @param R1  pre的下标结束位置
     * @param in  中序遍历数组
     * @param L2  in的下标开始位置
     * @param R2  in的下标结束位置
     * @return TreeNode 树的根节点
     */
    TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        if (L1 > R1) {
            return null;
        }
        TreeNode root = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return root;
        }
        int find = map.get(pre[L1]);
        root.left = f(pre, L1 + 1, L1 + find - L2, in, L2, find - 1);
        root.right = f(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);
        return root;
    }
}
