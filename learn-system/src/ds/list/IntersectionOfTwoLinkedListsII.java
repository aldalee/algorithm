package ds.list;

/**
 * 相交链表II
 * 题目描述: 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null。
 * 要求: 如果两个链表长度之和为N，时间复杂度请达到O(N)，空间复杂度请达到O(1)
 * @author HuanyuLee
 * @date 2023/5/15
 */
public class IntersectionOfTwoLinkedListsII {
    /**
     * 双指针
     * @param headA 第一个单链表头节点
     * @param headB 第二个单链表头节点
     * @return 返回相交的第一个节点，如果不相交返回null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode loopA = getFirstNodeOfLoop(headA);
        ListNode loopB = getFirstNodeOfLoop(headB);
        // 如果两个链表都无环
        if (loopA == null && loopB == null) {
            return noLoop(headA, headB);
        }
        // 如果两个链表都有环
        if (loopA != null && loopB != null) {
            return bothLoop(headA, loopA, headB, loopB);
        }
        return null;
    }

    /**
     * 找到链表入环的第一个节点。如果链表无环，则返回null
     */
    private ListNode getFirstNodeOfLoop(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);
        fast = head;    // slow和fast相遇了，fast回到head
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 如果两个链表都无环，返回第一个相交节点，如果不相交返回null
     */
    private ListNode noLoop(ListNode headA, ListNode headB) {
        return noLoop(headA, null, headB, null);
    }

    private ListNode noLoop(ListNode headA, ListNode endA, ListNode headB, ListNode endB) {
        if (headA == null || headB == null) {
            return null;
        }
        // 计算两个链表的长度
        int lenA = getListLength(headA, endA);
        int lenB = getListLength(headB, endB);
        // 让较长的链表先走差值步
        if (lenA > lenB) {
            headA = moveSteps(headA, lenA - lenB);
        } else {
            headB = moveSteps(headB, lenB - lenA);
        }
        // 同时遍历两个链表，找到相交节点
        while (headA != endA && headB != endB) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    /**
     * 如果两个链表都有环，返回第一个相交节点，如果不相交返回null
     */
    private ListNode bothLoop(ListNode headA, ListNode loopA, ListNode headB, ListNode loopB) {
        if (loopA == loopB) {                       // 入环节点是一个
            return noLoop(headA, loopA, headB, loopB);
        } else {                                    // 入环节点不是一个
            ListNode cur = loopA.next;
            while (cur != loopA) {
                if (cur == loopB) {                 // 两个链表有交集
                    return loopA;
                }
                cur = cur.next;
            }
            return null;                            // 两个链表无交集
        }
    }

    private int getListLength(ListNode head, ListNode end) {
        int size = 0;
        while (head != end) {
            size++;
            head = head.next;
        }
        return size;
    }

    private ListNode moveSteps(ListNode head, int steps) {
        while (steps > 0) {
            head = head.next;
            steps--;
        }
        return head;
    }
}
