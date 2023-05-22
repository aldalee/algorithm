package ds.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 * 二叉树的直径（二叉树的最大距离）
 * 学习本题的递归套路，今后应用
 * @author HuanyuLee
 * @date 2023/5/22
 */
public class DiameterOfBinaryTree {
    static class Info {
        int distance;
        int height;

        public Info(int distance, int height) {
            this.distance = distance;
            this.height = height;
        }
    }

    /**
     * 定义沿途节点数为二叉树最大距离
     */
    public int diameterOfBinaryTree(TreeNode root) {
        return process(root).distance;
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftSubtree = process(x.left);
        Info rightSubtree = process(x.right);
        int height = Math.max(leftSubtree.height, rightSubtree.height) + 1;
        // 不经过节点x
        int p1 = leftSubtree.distance;
        int p2 = rightSubtree.distance;
        // 经过节点x
        // int p3 = leftSubtree.height + rightSubtree.height + 1;
        int p3 = leftSubtree.height + rightSubtree.height;
        int distance = Math.max(Math.max(p1, p2), p3);
        return new Info(distance, height);
    }

    /**
     * 暴力求解，枚举任意两个节点间的距离
     */
    public int maxDistance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = getPreList(root);
        HashMap<TreeNode, TreeNode> parentMap = getParentMap(root);
        int maxDist = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                maxDist = Math.max(maxDist, distance(arr.get(i), arr.get(j), parentMap));
            }
        }
        return maxDist;
    }

    private ArrayList<TreeNode> getPreList(TreeNode root) {
        ArrayList<TreeNode> arr = new ArrayList<>();
        fillPreList(root, arr);
        return arr;
    }

    private void fillPreList(TreeNode root, ArrayList<TreeNode> arr) {
        if (root == null) {
            return;
        }
        arr.add(root);
        fillPreList(root.left, arr);
        fillPreList(root.right, arr);
    }

    private HashMap<TreeNode, TreeNode> getParentMap(TreeNode root) {
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        map.put(root, null);
        fillParentMap(root, map);
        return map;
    }

    private void fillParentMap(TreeNode root, HashMap<TreeNode, TreeNode> parentMap) {
        if (root.left != null) {
            parentMap.put(root.left, root);
            fillParentMap(root.left, parentMap);
        }
        if (root.right != null) {
            parentMap.put(root.right, root);
            fillParentMap(root.right, parentMap);
        }
    }

    private int distance(TreeNode node1, TreeNode node2, HashMap<TreeNode, TreeNode> parentMap) {
        HashSet<TreeNode> o1Set = new HashSet<>();
        TreeNode cur = node1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = node2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        TreeNode lowestAncestor = cur;
        cur = node1;
        int distance1 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance1++;
        }
        cur = node2;
        int distance2 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance2++;
        }
        return distance1 + distance2 - 2;
    }
}
