package ds.list;

/**
 * 用双链表结构实现双端队列
 * @author HuanyuLee
 * @date 2023/4/28
 */
public class DoubleLinkedListToDeque<T> {
    private DoubleListNode<T> head;
    private DoubleListNode<T> tail;
    private int size;

    public DoubleLinkedListToDeque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void pushHead(T value) {
        DoubleListNode<T> cur = new DoubleListNode<>(value);
        if (head == null) {
            head = cur;
            tail = cur;
        } else {
            cur.next = head;
            head.prev = cur;
            head = cur;
        }
        size++;
    }

    public void pushTail(T value) {
        DoubleListNode<T> cur = new DoubleListNode<>(value);
        if (head == null) {
            head = cur;
        } else {
            tail.next = cur;
            cur.prev = tail;
        }
        tail = cur;
        size++;
    }

    public T pollHead() {
        if (head == null) {
            return null;
        }
        T ans = head.value;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return ans;
    }

    public T pollTail() {
        if (head == null) {
            return null;
        }
        T ans = tail.value;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return ans;
    }

    public T peekHead() {
        T ans = null;
        if (head != null) {
            ans = head.value;
        }
        return ans;
    }

    public T peekTail() {
        T ans = null;
        if (tail != null) {
            ans = tail.value;
        }
        return ans;
    }
}
