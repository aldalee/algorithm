package list;

/**
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 * 合并两个有序链表
 * @author HuanyuLee
 * @date 2023/4/30
 */
public class Code0021_MergeTwoSortedLinkedList {
    public ListNode mergeTwoLists(ListNode L1, ListNode L2) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;       //prev.next指向较小的一个
        while (L1 != null && L2 != null) {
            if (L1.val <= L2.val) {
                prev.next = L1;
                L1 = L1.next;
            } else {
                prev.next = L2;
                L2 = L2.next;
            }
            prev = prev.next;
        }
        prev.next = L1 == null ? L2 : L1;   //处理未合并的节点
        return dummy.next;
    }
}
