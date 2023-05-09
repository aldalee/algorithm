package tool;

import ds.list.DoubleListNode;

import java.util.Random;

public class GenerateRandomDoubleList {
    public static DoubleListNode<Integer> generateRandomDoubleList(final int LENGTH, final int VALUE) {
        Random rand = new Random();
        int size = rand.nextInt(LENGTH + 1);
        if (size == 0) return null;
        DoubleListNode<Integer> head = new DoubleListNode<Integer>(rand.nextInt(VALUE + 1));
        DoubleListNode<Integer> pre = head;
        DoubleListNode<Integer> cur;
        while (--size != 0) {
            cur = new DoubleListNode<Integer>(rand.nextInt(VALUE + 1));
            pre.next = cur;
            cur.prev = pre;
            pre = cur;
        }
        return head;
    }
}
