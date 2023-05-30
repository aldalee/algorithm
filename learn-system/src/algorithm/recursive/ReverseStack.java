package algorithm.recursive;

import java.util.Arrays;
import java.util.Stack;

/**
 * 逆序栈
 * 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数
 * @author HuanyuLee
 * @date 2023/5/30
 */
public class ReverseStack {
    // 逆序栈
    public static void reverseStack(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int bottom = func(stack);
            reverseStack(stack);
            stack.push(bottom);
        }
    }

    /**
     * 移除栈底元素，剩下的元素全部落下来，返回移除的栈底元素
     * @param stack 栈
     * @return 移除的栈底元素
     */
    private static int func(Stack<Integer> stack) {
        int top = stack.pop();
        if (stack.isEmpty()) {
            return top;
        }
        int bottom = func(stack);
        stack.push(top);
        return bottom;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.addAll(Arrays.asList(1, 2, 3, 4, 5));

        reverseStack(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
