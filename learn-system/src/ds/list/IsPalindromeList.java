package ds.list;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/palindrome-linked-list/
 * 给定一个单链表的头节点head，判断该链表是否为回文结构
 * @author HuanyuLee
 * @date 2023/5/11
 */
public class IsPalindromeList {
    /**
     * 借助栈完成功能，实现简单，空间复杂度O(n)
     * @param head 单链表的头节点
     * @return 是否为回文结构
     */
    public boolean bruteForce(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 快慢指针法，空间复杂度O(1)
     * 注意: 在并发环境下，函数运行时需要锁定其他线程或进程对链表的访问，
     * 因为在函数执行过程中链表会被修改
     * @param head 单链表的头节点
     * @return 是否为回文结构
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);
        // 判断是否是回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean res = true;
        while (res && p2 != null) {
            if (p1.val != p2.val) res = false;
            p1 = p1.next;
            p2 = p2.next;
        }
        // 恢复链表
        firstHalfEnd.next = reverseList(secondHalfStart);
        return res;
    }

    /**
     * 快慢指针法，找到前半部分链表的尾节点
     */
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;  // 快指针一次走两步
            slow = slow.next;       // 慢指针一次走一步
        }
        return slow;
    }

    /**
     * 反转链表
     */
    private ListNode reverseList(ListNode head) {
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
