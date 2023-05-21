package tool;

import ds.tree.TreeNode;

import java.util.*;

public class BinaryTreeUtil {
    /**
     * 生成一棵二叉树
     * @param maxLevel 最大层数
     * @param maxValue 最大值
     * @return 二叉树根节点
     */
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    /**
     * 生成一棵值不同的二叉树
     * @param maxLevel 最大层数
     * @param maxValue 最大值
     * @return 二叉树根节点
     */
    public static TreeNode generateRandomBT(int maxLevel, int maxValue) {
        Set<Integer> usedValues = new HashSet<>();
        return generateRandomBT(maxLevel, maxValue, usedValues);
    }

    private static TreeNode generateRandomBT(int maxLevel, int maxValue, Set<Integer> usedValues) {
        if (maxLevel <= 0) {
            return null;
        }
        if (usedValues.size() == maxValue + 1) {
            return null; // 所有可用的值都已使用，终止生成
        }
        Random random = new Random();
        int val;
        do {
            val = random.nextInt(maxValue + 1);
        } while (usedValues.contains(val));
        usedValues.add(val);
        TreeNode node = new TreeNode(val);
        node.left = generateRandomBT(maxLevel - 1, maxValue, usedValues);
        node.right = generateRandomBT(maxLevel - 1, maxValue, usedValues);
        return node;
    }

    /**
     * 判断是否是相同的二叉树
     * @param p 第一个二叉树根节点
     * @param q 第二个二叉树根节点
     * @return 是否相同
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 生成二叉树的先序遍历序列，数组保存
     * @param root 二叉树根节点
     * @return 先序遍历序列
     */
    public static int[] preorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        pre(root, res);
        return change(res);
    }

    private static void pre(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        pre(root.left, res);
        pre(root.right, res);
    }

    /**
     * 生成二叉树的中序遍历序列，数组保存
     * @param root 二叉树根节点
     * @return 中序遍历序列
     */
    public static int[] inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        in(root, res);
        return change(res);
    }

    private static void in(TreeNode root, List<Integer> res) {
        if (root == null) return;
        in(root.left, res);
        res.add(root.val);
        in(root.right, res);
    }

    /**
     * 生成二叉树的后序遍历序列，数组保存
     * @param root 二叉树根节点
     * @return 后序遍历序列
     */
    public static int[] postorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        post(root, res);
        return change(res);
    }

    private static void post(TreeNode root, List<Integer> res) {
        if (root == null) return;
        post(root.left, res);
        post(root.right, res);
        res.add(root.val);
    }

    private static int[] change(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }


    /**
     * 打印二叉树的形状
     * @param root 二叉树根节点
     */
    public static void printTree(TreeNode root) {
        int maxLevel = maxLevel(root);
        printTreeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printTreeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) {
            return;
        }
        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;
        printWhitespaces(firstSpaces);
        List<TreeNode> newNodes = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println("");
        for (int i = 1; i <= edgeLines; i++) {
            for (TreeNode node : nodes) {
                printWhitespaces(firstSpaces - i);
                if (node == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }
                if (node.left != null) {
                    System.out.print("/");
                } else {
                    printWhitespaces(1);
                }
                printWhitespaces(i + i - 1);
                if (node.right != null) {
                    System.out.print("\\");
                } else {
                    printWhitespaces(1);
                }
                printWhitespaces(edgeLines + edgeLines - i);
            }
            System.out.println("");
        }
        printTreeInternal(newNodes, level + 1, maxLevel);
    }

    private static int maxLevel(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private static boolean isAllElementsNull(List<TreeNode> list) {
        for (Object object : list) {
            if (object != null) {
                return false;
            }
        }
        return true;
    }
}
