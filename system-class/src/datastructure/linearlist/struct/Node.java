package datastructure.linearlist.struct;

/**
 * @author HuanyuLee
 * @date 2022/10/1
 */
public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
    }
}
