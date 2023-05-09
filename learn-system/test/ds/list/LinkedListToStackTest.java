package ds.list;


import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class LinkedListToStackTest {
    private static final int VALUE = 99999;
    private static final int TEST_CASES = 100000;
    private LinkedListToStack<Integer> myStack;
    private Stack<Integer> stack;

    @Before
    public void setUp(){
        myStack = new LinkedListToStack<>();
        stack = new Stack<>();
    }

    @Test
    public void testStack(){
        // 先测试栈为空的情况
        assertTrue(myStack.isEmpty());
        assertTrue(stack.isEmpty());
        // 然后进行TEST_CASES次随机测试，随机进行入栈，出栈和查看栈首操作
        for (int i = 0; i < TEST_CASES; i++) {
            if (myStack.isEmpty() != stack.isEmpty()) {
                fail();
            }
            if (myStack.size() != stack.size()) {
                fail();
            }
            double decide = Math.random();
            switch ((int) (decide / 0.33)) {
                case 0 -> {
                    int num = (int) (Math.random() * VALUE);
                    myStack.push(num);
                    stack.push(num);
                }
                case 1 -> {
                    if (!myStack.isEmpty()) {
                        assertEquals(myStack.pop(), stack.pop());
                    }
                }
                default -> {
                    if (!myStack.isEmpty()) {
                        assertEquals(myStack.peek(), stack.peek());
                    }
                }
            }
        }
        // 测试并弹出所有的元素
        while (!myStack.isEmpty()) {
            assertEquals(myStack.pop(), stack.pop());
        }
        // 最后测试完所有的随机操作后，再次测试栈是否为空以及栈中的元素数量是否相等
        assertEquals(myStack.size(), stack.size());
        assertTrue(myStack.isEmpty());
        assertTrue(stack.isEmpty());
    }
}
