package ds.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树折纸问题
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down N=2时，打印: down down up
 * @author HuanyuLee
 * @date 2023/5/21
 */
public class PaperFolding {
    /**
     * 二叉树折纸问题
     * @param n 对折的次数
     * @return 折痕方向的集合
     */
    public List<Boolean> getAllFolds(int n) {
        List<Boolean> res = new ArrayList<>();
        process(1, n, true, res);
        return res;
    }

    /**
     * 递归求解折痕
     * 其实就是一棵二叉树的中序遍历序列
     * @param i    二叉树的层数（也就是第几次折叠）
     * @param h    二叉树的高度（也就是一共要折几次）
     * @param down true表示凹（down）；false 表示凸（up）
     * @param res  存储最终的遍历结果
     */
    private void process(int i, int h, boolean down, List<Boolean> res) {
        if (i > h) {
            return;
        }
        process(i + 1, h, true, res);
        res.add(down);
        process(i + 1, h, false, res);
    }

    public static void main(String[] args) {
        int n = 4;
        List<Boolean> allFolds = new PaperFolding().getAllFolds(n);
        for (Boolean fold : allFolds) {
            System.out.print(fold ? "down " : "up ");
        }
    }
}
