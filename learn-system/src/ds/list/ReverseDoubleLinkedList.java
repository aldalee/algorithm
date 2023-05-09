package ds.list;

/**
 * 双链表的反转
 * 类似题目见 leetcode-top.src.Code0206_ReverseLinkedList.java
 * @author HuanyuLee
 * @date 2023/4/21
 */
public class ReverseDoubleLinkedList<T> {
    public DoubleListNode<T> reverseDoubleList(DoubleListNode<T> head){
        DoubleListNode<T> pre = null;
        DoubleListNode<T> next;
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
