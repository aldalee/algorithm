package list;

/**
 * Definition for singly-linked list.
 * @author HuanyuLee
 * @date 2023/4/20
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
