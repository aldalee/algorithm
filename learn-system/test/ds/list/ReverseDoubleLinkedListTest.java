package ds.list;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static tool.LinkedListUtil.generateRandomDoubleList;

public class ReverseDoubleLinkedListTest {
    private static final int LENGTH = 50;
    private static final int VALUE = 100;
    private static final int TEST_CASES = 100000;
    private ReverseDoubleLinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new ReverseDoubleLinkedList<>();
    }

    @org.junit.Test
    public void testReverseDoubleList() {
        for (int i = 0; i < TEST_CASES; i++) {
            DoubleListNode<Integer> doubleList = generateRandomDoubleList(LENGTH, VALUE);
            List<Integer> origin = getDoubleListOriginOrder(doubleList);
            DoubleListNode<Integer> reverse = list.reverseDoubleList(doubleList);
            if (!checkDoubleListReverse(origin, reverse)) {
                fail();
            }
        }
    }

    private List<Integer> getDoubleListOriginOrder(DoubleListNode<Integer> head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    private boolean checkDoubleListReverse(List<Integer> origin, DoubleListNode<Integer> head) {
        DoubleListNode<Integer> end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (Integer integer : origin) {
            assert end != null;
            if (!integer.equals(end.value)) {
                return false;
            }
            end = end.prev;
        }
        return true;
    }

    private void printDoubleList(DoubleListNode<Integer> head) {
        System.out.print("Double Linked List: ");
        while (head != null) {
            System.out.print(head.value + "<->");
            head = head.next;
        }
        System.out.println("null");
    }
}
