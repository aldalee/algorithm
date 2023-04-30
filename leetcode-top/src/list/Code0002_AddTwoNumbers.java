package list;

/**
 * https://leetcode.cn/problems/add-two-numbers/
 * 两数相加
 * @author HuanyuLee
 * @date 2023/4/30
 */
public class Code0002_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode L1, ListNode L2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        while (L1 != null || L2 != null) {
            int x = L1 != null ? L1.val : 0;
            int y = L2 != null ? L2.val : 0;
            int sum = x + y + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (L1 != null) L1 = L1.next;
            if (L2 != null) L2 = L2.next;
        }
        if (carry > 0) tail.next = new ListNode(carry);
        return head;
    }
}