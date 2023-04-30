package list;

/**
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/
 * K个一组翻转链表
 * @author HuanyuLee
 * @date 2023/4/29
 */
public class Code0025_ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode start, end, endNext;
        while (pre.next != null) {
            start = pre.next;
            end = getKGroupEnd(pre, k);
            if (end == null) break;
            endNext = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = endNext;
            pre = start;
        }
        return dummy.next;
    }

    ListNode getKGroupEnd(ListNode start, int k) {
        for (int i = 0; i < k && start != null; i++) {
            start = start.next;
        }
        return start;
    }

    ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
