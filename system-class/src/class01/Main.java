package class01;

import java.util.Arrays;

import static class01.Code01_SelectionSort.selectionSort;
import static class01.Code02_BubbleSort.bubbleSort;
import static class01.Code03_InsertionSort.insertionSort;
import static class01.util.Utils.*;

/**
 * 利用对数器测试各种排序算法
 * @author HuanyuLee
 * @date 2022/9/28
 */
public class Main {
    public static void main(String[] args) {
        int testTime = 10000;
        int maxSize = 10000;
        int maxValue = 99999;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            // selectionSort(arr1);
            // bubbleSort(arr1);
            insertionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                // print error case
                System.out.println(Arrays.toString(arr));
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
