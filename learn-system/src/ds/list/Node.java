package ds.list;

/**
 * Definition for singly-linked list.
 * @author HuanyuLee
 * @date 2023/4/21
 */
public class Node<T> {
    public T value;
    public Node<T> next;

    public Node() {
    }

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
}
