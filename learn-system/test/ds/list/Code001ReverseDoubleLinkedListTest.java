package ds.list;

import java.util.ArrayList;
import java.util.List;

import static ds.list.Code001_ReverseDoubleLinkedList.reverseDoubleList;
import static tool.GenerateRandomDoubleList.generateRandomDoubleList;

public class Code001ReverseDoubleLinkedListTest {
    @org.junit.Test
    public void testReverseDoubleList() {
        int LENGTH = 50;
        int VALUE = 100;
        int TEST_CASES = 100000;
        System.out.println(">>> Test begin...");
        for (int i = 0; i < TEST_CASES; i++) {
            DoubleListNode doubleList = generateRandomDoubleList(LENGTH, VALUE);
            List<Integer> origin = getDoubleListOriginOrder(doubleList);
            DoubleListNode reverse = reverseDoubleList(doubleList);
            if (!checkDoubleListReverse(origin, reverse)) {
                System.out.println("Oops!");
            }
        }
        System.out.println(">>> Test finish!");
    }

    public static List<Integer> getDoubleListOriginOrder(DoubleListNode head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    public static boolean checkDoubleListReverse(List<Integer> origin, DoubleListNode head) {
        DoubleListNode end = null;
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

    public static void printDoubleList(DoubleListNode head) {
        System.out.print("Double Linked List: ");
        while (head != null) {
            System.out.print(head.value + "<->");
            head = head.next;
        }
        System.out.println("null");
    }
}
