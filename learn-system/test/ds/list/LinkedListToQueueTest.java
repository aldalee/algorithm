package ds.list;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

public class LinkedListToQueueTest {
    private static final int VALUE = 100;
    private static final int TEST_CASES = 100000;
    private LinkedListToQueue<Integer> myQueue;
    private Queue<Integer> queue;

    @Before
    public void setUp() {
        myQueue = new LinkedListToQueue<>();
        queue = new LinkedList<>();
    }

    @Test
    public void testQueue() {
        // 先测试队列为空的情况
        assertTrue(myQueue.isEmpty());
        assertTrue(queue.isEmpty());
        // 然后进行TEST_CASES次随机测试，随机进行入队，出队和查看队首操作
        for (int i = 0; i < TEST_CASES; i++) {
            if (myQueue.isEmpty() != queue.isEmpty()) {
                fail();
            }
            if (myQueue.size() != queue.size()) {
                fail();
            }
            double decide = Math.random();
            switch ((int) (decide / 0.33)) {
                case 0 -> {
                    int num = (int) (Math.random() * VALUE);
                    myQueue.offer(num);
                    queue.offer(num);
                }
                case 1 -> {
                    if (!myQueue.isEmpty()) {
                        assertEquals(myQueue.poll(), queue.poll());
                    }
                }
                default -> {
                    if (!myQueue.isEmpty()) {
                        assertEquals(myQueue.peek(), queue.peek());
                    }
                }
            }
        }
        // 测试并弹出所有的元素
        while (!myQueue.isEmpty()) {
            assertEquals(myQueue.poll(), queue.poll());
        }
        // 最后测试完所有的随机操作后，再次测试队列是否为空以及队列中的元素数量是否相等
        assertEquals(myQueue.size(), queue.size());
        assertTrue(myQueue.isEmpty());
        assertTrue(queue.isEmpty());
    }
}
