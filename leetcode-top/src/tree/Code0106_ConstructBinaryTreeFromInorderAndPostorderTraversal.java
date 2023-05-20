package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * 从中序与后序遍历序列构造二叉树
 * @author HuanyuLee
 * @date 2023/5/20
 */
public class Code0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            idxMap.put(inorder[i], i);
        }
        return build(0, inorder.length - 1, postorder, postorder.length - 1, idxMap);
    }

    /**
     * 辅助方法：根据中序遍历和后序遍历序列构建二叉树的递归函数
     * @param inStart 当前子树在中序遍历序列中的起始索引
     * @param inEnd   当前子树在中序遍历序列中的结束索引
     * @param post    后序遍历序列
     * @param postIdx 当前子树在后序遍历序列中的根节点索引
     * @param map     中序遍历序列中值到索引的映射
     * @return 构建的二叉树的根节点
     */
    private TreeNode build(int inStart, int inEnd, int[] post, int postIdx, Map<Integer, Integer> map) {
        if (inStart > inEnd) {
            return null;
        }
        int rootVal = post[postIdx];
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = map.get(rootVal);
        int rightSubtreeSize = inEnd - rootIdx;
        root.right = build(rootIdx + 1, inEnd, post, postIdx - 1, map);
        root.left = build(inStart, rootIdx - 1, post, postIdx - rightSubtreeSize - 1, map);
        return root;
    }
}
