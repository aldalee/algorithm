package algorithm.sorting;

/**
 * 希尔排序 -> 改进的插入排序算法
 * 将待排序的数组分割成多个较小的子数组进行插入排序，并逐步缩小子数组的规模，最终完成整个数组的排序
 * 希尔排序的主要思想:
 *     1、过设置一个间隔序列gap sequence，根据间隔序列将数组分割成若干个子数组,然后对每个子数组进行插入排序。
 *     2、在排序过程中，通过不断缩小间隔序列的值，直到最后一次间隔为1，完成最后一轮的插入排序，从而达到整体有序的目的
 * @author HuanyuLee
 * @date 2023/5/10
 */
public class ShellSort {
    public void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                insert(arr, i, gap);
            }
        }
    }

    /**
     * 在给定的间隔下，使用插入排序将元素插入到正确的位置
     * @param arr 要排序的数组
     * @param idx 当前要插入的元素索引
     * @param gap 当前的间隔
     */
    private void insert(int[] arr, int idx, int gap) {
        int temp = arr[idx];
        int i;
        // 从当前位置向前遍历，将大于temp的元素后移
        for (i = idx; i >= gap && arr[i - gap] > temp; i -= gap) {
            arr[i] = arr[i - gap];   // 元素后移
        }
        arr[i] = temp;               // 将temp插入到正确的位置
    }
}
