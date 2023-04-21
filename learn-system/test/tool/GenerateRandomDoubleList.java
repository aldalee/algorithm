package tool;

import ds.list.DoubleListNode;

import java.util.Random;

/**
 * @author HuanyuLee
 * @date 2023/4/21
 */
public class GenerateRandomDoubleList {
    public static DoubleListNode generateRandomDoubleList(final int LENGTH, final int VALUE) {
        Random rand = new Random();
        int size = rand.nextInt(LENGTH + 1);
        if (size == 0) return null;
        DoubleListNode head = new DoubleListNode(rand.nextInt(VALUE + 1));
        DoubleListNode pre = head;
        DoubleListNode cur;
        while (--size != 0) {
            cur = new DoubleListNode(rand.nextInt(VALUE + 1));
            pre.next = cur;
            cur.prev = pre;
            pre = cur;
        }
        return head;
    }
}
