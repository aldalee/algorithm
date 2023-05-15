package list;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/
 * 相交链表
 * @author HuanyuLee
 * @date 2023/5/15
 */
public class Code0160_IntersectionOfTwoLinkedLists {
    /**
     * 哈希表 空间复杂度O(n)
     * @param headA 单链表头节点
     * @param headB 单链表头节点
     * @return 返回相交的第一个节点，如果不相交返回null
     */
    public ListNode findFirstCommonNode(ListNode headA, ListNode headB) {
        Set<ListNode> seen = new HashSet<>();
        while (headA != null) {
            seen.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (seen.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }

    /**
     * 双指针 空间复杂度O(1)
     * https://xiaochen1024.com/20211118151045.gif
     * @param headA 单链表头节点
     * @param headB 单链表头节点
     * @return 返回相交的第一个节点，如果不相交返回null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p = headA;
        ListNode q = headB;
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}
