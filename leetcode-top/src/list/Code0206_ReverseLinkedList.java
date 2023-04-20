package list;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 * 反转链表
 * @author HuanyuLee
 * @date 2023/4/20
 */
public class Code0206_ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
