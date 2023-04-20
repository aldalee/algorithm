import java.util.Arrays;
/**
 * @author HuanyuLee
 * @date 2021_11_4_week
 */
public class Code0001_RetainTree_Test {
    public static void main(String[] args) {
        Code0001_RetainTree.Node n1 = new Code0001_RetainTree.Node(1, false);
        Code0001_RetainTree.Node n2 = new Code0001_RetainTree.Node(2, true);
        Code0001_RetainTree.Node n3 = new Code0001_RetainTree.Node(3, false);
        Code0001_RetainTree.Node n4 = new Code0001_RetainTree.Node(4, false);
        Code0001_RetainTree.Node n5 = new Code0001_RetainTree.Node(5, false);
        Code0001_RetainTree.Node n6 = new Code0001_RetainTree.Node(6, true);
        Code0001_RetainTree.Node n7 = new Code0001_RetainTree.Node(7, true);
        Code0001_RetainTree.Node n8 = new Code0001_RetainTree.Node(8, false);
        Code0001_RetainTree.Node n9 = new Code0001_RetainTree.Node(9, false);
        Code0001_RetainTree.Node n10 = new Code0001_RetainTree.Node(10, false);
        Code0001_RetainTree.Node n11 = new Code0001_RetainTree.Node(11, false);
        Code0001_RetainTree.Node n12 = new Code0001_RetainTree.Node(12, false);
        Code0001_RetainTree.Node n13 = new Code0001_RetainTree.Node(13, true);

        n1.children.addAll(Arrays.asList(n2, n3));
        n2.children.addAll(Arrays.asList(n4, n5));
        n3.children.addAll(Arrays.asList(n6, n7));
        n6.children.addAll(Arrays.asList(n8, n9, n10));
        n7.children.addAll(Arrays.asList(n11, n12));
        n9.children.add(n13);
        //test
        Code0001_RetainTree tree = new Code0001_RetainTree();
        Code0001_RetainTree.Node root = tree.retain(n1);
        tree.preOrderPrint(root);
    }
}