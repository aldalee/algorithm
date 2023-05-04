package array;

/**
 * https://leetcode.cn/problems/remove-element/
 * 移除元素
 * @author HuanyuLee
 * @date 2023/5/4
 */
public class Code0027_RemoveElement {
    public int removeElement(int[] nums, int val) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (nums[i] == val) {
                nums[i] = nums[j--];
            } else {
                i++;
            }
        }
        return i;
    }
}
