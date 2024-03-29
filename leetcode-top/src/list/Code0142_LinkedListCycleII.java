package list;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/linked-list-cycle-ii/
 * 环形链表II
 * 给定一个链表的头节点head，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * @author HuanyuLee
 * @date 2023/5/15
 */
public class Code0142_LinkedListCycleII {
    /**
     * 哈希表 空间复杂度O(n)
     * @param head 链表的头节点
     * @return 链表开始入环的第一个节点。如果链表无环，则返回null。
     */
    public ListNode getLoopListNode(ListNode head) {
        ListNode pos = head;
        Set<ListNode> seen = new HashSet<>();
        while (pos != null) {
            if (seen.contains(pos)) return pos;
            else seen.add(pos);
            pos = pos.next;
        }
        return null;
    }

    /**
     * 快慢指针 空间复杂度O(1)
     * @param head 链表的头节点
     * @return 链表开始入环的第一个节点。如果链表无环，则返回null。
     */
    public ListNode detectCycle(ListNode head) {
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
}
