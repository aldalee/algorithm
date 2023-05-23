package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.cn/problems/largest-number/
 * 最大数
 * @author HuanyuLee
 * @date 2023/5/22
 */
public class Code0179_LargestNumber {
    /**
     * 贪心策略 拼接排序
     * @param nums 一组非负整数
     * @return 重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数
     */
    public String largestNumber(int[] nums) {
        List<String> strs = new ArrayList<>();
        for (int num : nums) {
            strs.add(num + "");
        }
        strs.sort((o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str);
        }
        return res.charAt(0) == '0' ? "0" : res.toString();
    }

    // 简化版本
    public String largestNumber2(int[] nums) {
        List<String> strs = new ArrayList<>();
        for (int num : nums) {
            strs.add(Integer.toString(num));
        }
        strs.sort((o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        return strs.get(0).equals("0") ? "0" : String.join("", strs);
    }

    // LeetCode 稍作修改
    public String largestNumber3(int[] nums) {
        Integer[] numsArr = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsArr[i] = nums[i];
        }
        Arrays.sort(numsArr, (x, y) -> (int) (-m(y) * x - y + m(x) * y + x));

        StringBuilder res = new StringBuilder();
        for (int num : numsArr) {
            res.append(num);
        }
        return res.charAt(0) == '0' ? "0" : res.toString();
    }

    private long m(int num) {
        long ans = 10;
        while (ans <= num) ans *= 10;
        return ans;
    }

    /**
     * 另一种贪心策略 按照数字的权重排序
     * 比如: nums = [2, 21]，2的权重应该是a = 2/9，21的权重应该是b= 21/99
     * 因为 a > b，因此2应该排前面，21排后面，也就是"221"要大于"212"
     * Python代码更加简洁
     * def largestNumber(self, nums: List[int]) -> str:
     *      nums.sort(key = lambda x: -x / ((10 ** len(str(x))) - 1))
     *      outPut = ''.join(str(x) for x in nums)
     *      return str(int(outPut))    # Remove '0' s at the beginning of the string
     *
     * @param nums 一组非负整数
     * @return 重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数
     */
    public String largestNumber4(int[] nums) {
        Integer[] numsArr = IntStream.of(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(numsArr, (o1, o2) -> Double.compare(o2 / base(o2), o1 / base(o1)));

        String ans = Arrays.stream(numsArr).map(String::valueOf).collect(Collectors.joining());
        return ans.startsWith("0") ? "0" : ans;
    }

    private double base(Integer n) {
        return Math.pow(10, String.valueOf(n).length()) - 1;
    }
}
