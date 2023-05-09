package ds.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class DoubleLinkedListToDequeTest {
    private static final int VALUE = 99999;
    private static final int TEST_CASES = 100000;

    private DoubleLinkedListToDeque<Integer> myDeque;
    private Deque<Integer> deque;

    @Before
    public void init() {
        myDeque = new DoubleLinkedListToDeque<>();
        deque = new LinkedList<>();
    }

    @Test
    public void testDeque() {
        for (int i = 0; i < TEST_CASES; i++) {
            if (myDeque.isEmpty() != deque.isEmpty()) {
                fail();
            }
            if (myDeque.size() != deque.size()) {
                fail();
            }
            double decide = Math.random();
            switch ((int) (decide / 0.33)) {
                case 0 -> {
                    int num = (int) (Math.random() * VALUE);
                    if (Math.random() < 0.5) {
                        myDeque.pushHead(num);
                        deque.addFirst(num);
                    } else {
                        myDeque.pushTail(num);
                        deque.addLast(num);
                    }
                }
                case 1 -> {
                    if (!myDeque.isEmpty()) {
                        if (Math.random() < 0.5) {
                            assertEquals(myDeque.pollHead(), deque.pollFirst());
                        } else {
                            assertEquals(myDeque.pollTail(), deque.pollLast());
                        }
                    }
                }
                default -> {
                    if (!myDeque.isEmpty()) {
                        if (Math.random() < 0.5) {
                            assertEquals(myDeque.peekHead(), deque.peekFirst());
                        } else {
                            assertEquals(myDeque.peekTail(), deque.peekLast());
                        }
                    }
                }
            }
        }
        assertEquals(myDeque.size(), deque.size());
        while (!myDeque.isEmpty()) {
            assertEquals(myDeque.pollHead(), deque.pollFirst());
        }
        assertTrue(myDeque.isEmpty());
        assertTrue(deque.isEmpty());
    }
}
