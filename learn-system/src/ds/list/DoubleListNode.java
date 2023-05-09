package ds.list;

/**
 * Definition for double-linked list.
 * @author HuanyuLee
 * @date 2023/4/21
 */
public class DoubleListNode<T> {
    public T value;
    public DoubleListNode<T> prev;
    public DoubleListNode<T> next;

    public DoubleListNode() {
    }

    public DoubleListNode(T value) {
        this.value = value;
    }

    public DoubleListNode(T value, DoubleListNode<T> prev, DoubleListNode<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
