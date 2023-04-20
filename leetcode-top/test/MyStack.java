import java.util.LinkedList;
import java.util.Stack;

/**
 * Java自带的Stack效率较低，使用LinkedList、数组 替代
 * @author HuanyuLee
 * @date 2023/4/19
 */
public class MyStack {
    public static void main(String[] args) {
        //Java自带的Stack
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

        //使用LinkedList替代
        LinkedList<Integer> stack2 = new LinkedList<>();
        stack2.addFirst(1);
        stack2.addFirst(2);
        stack2.addFirst(3);
        while (!stack2.isEmpty()){
            System.out.println(stack2.pollFirst());
        }

        //使用Array替代
        int[] stack3 = new int[100];
        int size = 0;
        stack3[size++] = 1;
        stack3[size++] = 2;
        stack3[size++] = 3;
        while (size > 0) {
            System.out.println(stack3[--size]);
        }
    }
}
