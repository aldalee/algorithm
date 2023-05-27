package algorithm.recursive;

/**
 * Tower of Hanoi
 * @author HuanyuLee
 * @date 2023/5/27
 */
public class Hanoi {
    public static void hanoi(int n) {
        if (n <= 0) return;
        move(n, "a", "b", "c");
    }

    static int step = 0;        // 移动n层汉诺塔需要移动 (1 << n)-1 步

    /**
     * 将n个盘子，借助help从src移动到dest
     * @param n    盘子的个数
     * @param src  起始柱子
     * @param dest 目标柱子
     * @param help 辅助柱子
     */
    private static void move(int n, String src, String dest, String help) {
        if (n == 1) {
            System.out.println("Move " + n + " from " + src + " to " + dest);
        } else {
            move(n - 1, src, help, dest);
            System.out.println("Move " + n + " from " + src + " to " + dest);
            move(n - 1, help, dest, src);
        }
        step++;
    }

    public static void main(String[] args) {
        int n = 4;
        hanoi(n);
        System.out.println("step = " + step);
    }
}
