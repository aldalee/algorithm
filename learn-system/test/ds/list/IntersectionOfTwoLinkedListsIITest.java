package ds.list;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IntersectionOfTwoLinkedListsIITest {
    IntersectionOfTwoLinkedListsII s = new IntersectionOfTwoLinkedListsII();
    ListNode head1;
    ListNode head2;

    public void initNoLoopList() {
        // 1->2->3->4->5->6->7->NULL
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);

        // 0->9->8->6->7->NULL
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
    }

    public void initBothListOfSameEntryLoopNode() {
        // 1->2->3->4->5->6->7->4...
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next; // 8->2
    }

    public void initTwoIndependentLoopLists() {
        // 1->2->3->4->3->...
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = head1.next.next;

        // 1->2->3->4->3->...
        head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = head2.next.next;
    }

    public void initBothListOfDifferentEntryLoopNode() {
        // 1->2->3->4->5->6->7->4...
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->6->4->5->6..
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
    }

    @Test
    public void testGetIntersectionNodeForNoLoopList() {
        initNoLoopList();
        ListNode f = s.getIntersectionNode(head1, head2);
        assertEquals(f.val, 6);
    }

    @Test
    public void testGetIntersectionNodeForBothListOfSameEntryLoopNode() {
        initBothListOfSameEntryLoopNode();
        ListNode f = s.getIntersectionNode(head1, head2);
        assertEquals(f.val, 2);
    }

    @Test
    public void testGetIntersectionNodeForTwoIndependentLoopLists() {
        initTwoIndependentLoopLists();
        ListNode f = s.getIntersectionNode(head1, head2);
        assertNull(f);
    }

    @Test
    public void testGetIntersectionNodeForBothListOfDifferentEntryLoopNode() {
        initBothListOfDifferentEntryLoopNode();
        ListNode f = s.getIntersectionNode(head1, head2);
        assertEquals(f.val, 4);
    }
}
