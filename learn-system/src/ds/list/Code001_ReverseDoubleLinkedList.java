package ds.list;

/**
 * 双链表的反转
 * 类似题目见 leetcode-top.src.Code0206_ReverseLinkedList.java
 * @author HuanyuLee
 * @date 2023/4/21
 */
public class Code001_ReverseDoubleLinkedList {
    public static DoubleListNode reverseDoubleList(DoubleListNode head){
        DoubleListNode pre = null;
        DoubleListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.prev = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
