package algorithm.class03_bitwise_operation;

/**
 * 打印一个数的二进制形式
 * @author HuanyuLee
 * @date 2022/9/30
 */
public class Code02_PrintBinary {
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? '0' : '1');
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 在线验证 https://c.runoob.com/front-end/58/
        int num = 65535;
        print(num);
    }
}
