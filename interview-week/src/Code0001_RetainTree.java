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

    public static void main(String[] args) {
        //init
        Node n1 = new Node(1, false);
        Node n2 = new Node(2, true);
        Node n3 = new Node(3, false);
        Node n4 = new Node(4, false);
        Node n5 = new Node(5, false);
        Node n6 = new Node(6, true);
        Node n7 = new Node(7, true);
        Node n8 = new Node(8, false);
        Node n9 = new Node(9, false);
        Node n10 = new Node(10, false);
        Node n11 = new Node(11, false);
        Node n12 = new Node(12, false);
        Node n13 = new Node(13, true);
        //construct
        n1.children.addAll(Arrays.asList(n2, n3));
        n2.children.addAll(Arrays.asList(n4, n5));
        n3.children.addAll(Arrays.asList(n6, n7));
        n6.children.addAll(Arrays.asList(n8, n9, n10));
        n7.children.addAll(Arrays.asList(n11, n12));
        n9.children.add(n13);
        //test
        Code0001_RetainTree tree = new Code0001_RetainTree();
        Node root = tree.retain(n1);
        tree.preOrderPrint(root);
    }
}
