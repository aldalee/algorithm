package ds.list;

/**
 * Definition for double-linked list.
 * @author HuanyuLee
 * @date 2023/4/21
 */
public class DoubleListNode {
    public int value;
    public DoubleListNode prev;
    public DoubleListNode next;

    public DoubleListNode() {
    }

    public DoubleListNode(int value) {
        this.value = value;
    }

    public DoubleListNode(int value, DoubleListNode prev, DoubleListNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
