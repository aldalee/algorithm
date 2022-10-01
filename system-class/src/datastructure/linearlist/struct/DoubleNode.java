package datastructure.linearlist.struct;

/**
 * @author HuanyuLee
 * @date 2022/10/1
 */
public class DoubleNode<T> {
    public T data;
    public DoubleNode<T> prev;
    public DoubleNode<T> next;

    public DoubleNode(T data) {
        this.data = data;
    }
}
