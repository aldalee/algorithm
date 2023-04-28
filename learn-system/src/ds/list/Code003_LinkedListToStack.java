package ds.list;

/**
 * 使用链表实现栈
 * @author HuanyuLee
 * @date 2023/4/21
 */
public class Code003_LinkedListToStack<T> {
    private Node<T> head;
    private int size;

    public Code003_LinkedListToStack() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(T value) {
        Node<T> cur = new Node<>(value);
        if (head != null) {
            cur.next = head;
        }
        head = cur;
        size++;
    }

    public T pop() {
        T ans = null;
        if (head != null) {
            ans = head.value;
            head = head.next;
            size--;
        }
        return ans;
    }

    public T peek() {
        return head != null ? head.value : null;
    }
}
