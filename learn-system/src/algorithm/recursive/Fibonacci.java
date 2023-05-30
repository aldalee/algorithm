package algorithm.recursive;

/**
 * 斐波那契数列
 * @author HuanyuLee
 * @date 2023/5/30
 */
public class Fibonacci {
    public static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(fibonacci(5));
    }
}
