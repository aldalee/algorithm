package tool;

import ds.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
