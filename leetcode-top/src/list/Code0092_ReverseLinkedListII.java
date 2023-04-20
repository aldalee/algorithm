package list;

/**
 * https://leetcode.cn/problems/reverse-linked-list-ii/
 * 反转链表Ⅱ
 * @author HuanyuLee
 * @date 2023/4/20
 */
public class Code0092_ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode p;
        for (int i = 0; i < right - left; i++) {
            p = cur.next;
            cur.next = p.next;
            p.next = pre.next;
            pre.next = p;
        }
        return dummy.next;
    }
}
