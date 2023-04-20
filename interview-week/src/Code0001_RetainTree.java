import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一棵多叉树的头节点head，每个节点只有黑白两色
 * 所有黑节点都保留，所有从头节点到黑节点路径上的点都保留
 * 返回处理后树的头节点
 * @author HuanyuLee
 * @date 2021_11_4_week
 */
public class Code0001_RetainTree {
    static class Node {
        int value;
        boolean retain;
        List<Node> children;

        public Node(int value, boolean retain) {
            this.value = value;
            this.retain = retain;
            children = new LinkedList<>();
        }
    }

    Node retain(Node root) {
        if (root.children.isEmpty()) {
            return root.retain ? root : null;
        }
        LinkedList<Node> newChildren = new LinkedList<>();
        for (Node child : root.children) {
            Node res = retain(child);
            if (res != null) {
                newChildren.add(res);
            }
        }
        if (!newChildren.isEmpty() || root.retain) {
            root.children = newChildren;
            return root;
        }
        return null;
    }

    void preOrderPrint(Node root) {
        System.out.print(root.value + " ");
        for (Node child : root.children) {
            preOrderPrint(child);
        }
    }
}
