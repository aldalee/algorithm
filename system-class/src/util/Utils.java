package util;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 工具类
 * @author HuanyuLee
 * @date 2022/9/27
 */
public class Utils {
    /**
     * 生成长度随机值随机的数组
     * @param maxSize  数组的最大长度
     * @param maxValue 数组的最大值
     * @return array
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() -> [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomNumber(maxValue);
        }
        return arr;
    }

    /**
     * 生成长度随机、值随机、相邻不相等的数组
     * @param maxSize  数组的最大长度
     * @param maxValue 数组的最大值
     * @return array
     */
    public static int[] generateAdjacentNotEqualRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        arr[0] = randomNumber(maxValue);
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = randomNumber(maxValue);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    /**
     * 生成一个随机数组，保证其中一种数出现K次，其余数都出现 M 次
     * 并且 K < M, M > 1
     * @param maxKinds 最大的种类
     * @param range    数组的最大值
     * @param k        一种数出现 K 次
     * @param p        这种数出现的概率
     * @param m        其余数都出现 M 次
     * @return array
     */
    public static int[] generateKMRandomArray(int maxKinds, int range, int k, double p, int m) {
        int kTimeNum = randomNumber(range);
        // 出现K次的数以 p 的概率出现，剩下的随便，只要比M小就可以
        int times = Math.random() < p ? k : ((int) (Math.random() * (m - 1)) + 1);
        int numKinds = (int) (Math.random() * maxKinds) + 2; // 至少有2种数
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        while (index < times) {  // 先填进出现 K 次的数
            arr[index++] = kTimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(kTimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            // 保证每一次生成的数都是不重复的
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // 打乱数据的规律
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数随机的 j 位置的数交换
            int j = (int) (Math.random() * arr.length);
            swap(arr, i, j);
        }
        return arr;
    }

    /**
     * 生成 [-range, +range] 之间的随机数
     * @param range 范围
     * @return number
     */
    private static int randomNumber(int range) {
        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 拷贝数组
     * @param arr 待拷贝数组
     * @return copyArray
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    /**
     * 判断两个数组是否相等
     * @param arr1 数组1
     * @param arr2 数组2
     * @return boolean
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null) {
            return true;
        }
        // array1 and array2 not null
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 系统的数组排序
     * @param arr 待排序数组
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }
}
