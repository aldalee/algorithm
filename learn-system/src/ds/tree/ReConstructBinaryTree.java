package ds.tree;

import java.util.*;

/**
 * 重建二叉树
 * 1、根据先序遍历序列和中序遍历序列
 * 2、根据后序遍历序列和中序遍历序列
 * @author HuanyuLee
 * @date 2023/5/19
 */
public class ReConstructBinaryTree {
    /**
     * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     * 递归版本
     * @param preorder 先序遍历序列
     * @param inorder  中序遍历序列
     * @return 二叉树根节点
     */
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

    HashMap<Integer, Integer> map = new HashMap<>();  //<value, index>

    // 课上代码（已优化）
    public TreeNode buildBinaryTree(int[] pre, int[] in) {
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
    private TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
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

    /**
     * https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/
     * 非递归版本
     * @param preorder 先序遍历序列
     * @param inorder  中序遍历序列
     * @return 二叉树根节点
     */
    public TreeNode buildTreeNoRec(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);          // 先序序列的第一个元素是整棵树的根节点
        Deque<TreeNode> stack = new LinkedList<>();         // 使用栈来辅助构建二叉树
        stack.push(root);                                   // 将根节点入栈

        int index = 0;                                      // 中序遍历序列的索引，用于定位当前节点在中序遍历中的位置
        for (int i = 1; i < preorder.length; i++) {
            int preVal = preorder[i];
            TreeNode node = stack.peek();                   // 获取栈顶节点，即当前节点的父节点
            if (node.val != inorder[index]) {
                // 当前节点不是其父节点的左子节点？？？
                node.left = new TreeNode(preVal);           // 创建当前节点，并连接到父节点的左子节点
                stack.push(node.left);                      // 将当前节点入栈
            } else {
                // 当前节点是其父节点的右子节点？？？
                while (!stack.isEmpty() && stack.peek().val == inorder[index]) {
                    // 当前节点的值与中序遍历序列中的值相等，说明父节点的左子树已经构建完成
                    // 不断弹出栈顶节点，直到找到一个节点其值与中序遍历序列中的值不相等
                    node = stack.pop();
                    index++;
                }
                node.right = new TreeNode(preVal);          // 创建当前节点，并连接到父节点的右子节点
                stack.push(node.right);                     // 将当前节点入栈
            }
        }
        return root;
    }

    /**
     * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     * 递归版本
     * @param inorder 先序遍历序列
     * @param postorder 后序遍历序列
     * @return 二叉树根节点
     */
    public TreeNode buildBT(int[] inorder, int[] postorder) {
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
        root.left = build(inStart, rootIdx - 1, post, postIdx - rightSubtreeSize - 1, map);
        root.right = build(rootIdx + 1, inEnd, post, postIdx - 1, map);
        return root;
    }
}
