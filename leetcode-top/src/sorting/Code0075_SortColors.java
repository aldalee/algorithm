package sorting;

/**
 * https://leetcode.cn/problems/sort-colors/
 * 颜色分类（荷兰国旗问题）
 * @author HuanyuLee
 * @date 2023/5/28
 */
public class Code0075_SortColors {
    public static void sortColors(int[] nums) {
        int less = -1;                  // 小于划分值区域右边界
        int more = nums.length;         // 大于划分值区域左边界
        int idx = 0;                    // 当前位置
        while (idx < more) {
            if (nums[idx] == 1) {
                idx++;
            } else if (nums[idx] < 1) {
                swap(nums, idx++, ++less);
            } else {
                swap(nums, idx, --more);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
