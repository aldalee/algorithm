package list;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/linked-list-cycle/
 * 环形链表
 * 给你一个链表的头节点head，判断链表中是否有环。
 * @author HuanyuLee
 * @date 2023/5/15
 */
public class Code0141_LinkedListCycle {
    /**
     * 哈希表 空间复杂度O(n)
     * @param head 链表的头节点
     * @return 链表中是否有环
     */
    public boolean HasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            if (!seen.add(head)) {   // 如果该节点已经存在于哈希表中，则添加失败，说明有环
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针 空间复杂度O(1)
     * @param head 链表的头节点
     * @return 链表中是否有环
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        do {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == null || fast.next == null) {
                return false;
            }
        } while (fast != slow);
        return true;
    }
}
