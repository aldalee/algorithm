package ds.tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static tool.BinaryTreeUtil.Result;
import static tool.BinaryTreeUtil.generateRandomBSTWithParent2;

public class SuccessorNodeTest {
    final int MAX_LEVEL = 20;
    final int MAX_VALUE = 10000;
    final int TEST_CASES = 1000000;
    SuccessorNode bst = new SuccessorNode();

    @Test
    public void testInorderSuccessor(){
        for (int i = 0; i < TEST_CASES; i++) {
            Result result = generateRandomBSTWithParent2(MAX_LEVEL, MAX_VALUE);
            Node x = result.randomNode;
            Node node = bst.inorderSuccessor(x);
            Node node2 = bst.getSuccessorNode(result.root, x);
            assertEquals(node,node2);
        }
    }
}
