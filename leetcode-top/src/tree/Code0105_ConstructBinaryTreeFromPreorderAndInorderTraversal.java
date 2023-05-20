package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 从前序与中序遍历序列构造二叉树
 * @author HuanyuLee
 * @date 2023/4/17
 */
public class Code0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构建中序遍历序列中值到索引的映射
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inorderMap.putIfAbsent(inorder[i], i);
        }
        return build(preorder, 0, n - 1, 0, n - 1, inorderMap);
    }

    /**
     * 根据先序遍历和中序遍历序列构建二叉树
     * @param pre      先序遍历序列
     * @param preStart 先序遍历序列的起始索引
     * @param preEnd   先序遍历序列的结束索引
     * @param inStart  中序遍历序列的起始索引
     * @param inEnd    中序遍历序列的结束索引
     * @param map      中序遍历序列中值到索引的映射
     * @return 构建的二叉树的根节点
     */
    private TreeNode build(int[] pre, int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = pre[preStart];                    // 先序序列的第一个元素是当前子树的根节点
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = map.get(rootVal);               // 在中序序列中找到根节点的索引
        int leftSubtreeLen = rootIndex - inStart;       // 计算左子树的长度
        // 递归构建左子树
        root.left = build(pre, preStart + 1, preStart + leftSubtreeLen, inStart, rootIndex - 1, map);
        // 递归构建右子树
        root.right = build(pre, preStart + leftSubtreeLen + 1, preEnd, rootIndex + 1, inEnd, map);
        return root;
    }
}
