package ds.tree;

/**
 * @author HuanyuLee
 * @date 2023/5/21
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public Node(int val, Node left, Node right, Node parent) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
