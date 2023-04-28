package ds.list;

/**
 * 使用链表实现队列
 * @author HuanyuLee
 * @date 2023/4/21
 */
public class Code002_LinkedListToQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Code002_LinkedListToQueue() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void offer(T value) {
        Node<T> cur = new Node<>(value);
        if (tail == null) {
            head = cur;
        } else {
            tail.next = cur;
        }
        tail = cur;
        size++;
    }

    public T poll() {
        T ans = null;
        if (head != null) {
            ans = head.value;
            head = head.next;
            size--;
        }
        if (head == null){
            tail = null;
        }
        return ans;
    }

    public T peek() {
        T ans = null;
        if (head != null) {
            ans = head.value;
        }
        return ans;
    }

}
