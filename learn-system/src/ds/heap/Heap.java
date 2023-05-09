package ds.heap;

/**
 * 堆（Heap），优先级队列（PriorityQueue）
 * 数组arr[]和完全二叉树的对应关系: 左孩子2*i+1;右孩子2*i+2;父节点(i-1)/2。
 * 堆通常是一个可以被看做一棵完全二叉树的数组对象
 * 大根堆
 * @author HuanyuLee
 * @date 2023/5/7
 */
public class Heap {
    // 递归版本，适用于理解算法，实际中不常用
    public static void heapify(int[] arr, int index, int heapSize) {
        int L = 2 * index + 1;
        int R = 2 * index + 2;
        int largest = index;      //Initialize largest as root.
        if (L < heapSize && arr[L] > arr[largest]) largest = L;
        if (R < heapSize && arr[R] > arr[largest]) largest = R;
        if (largest != index) {
            swap(arr, index, largest);
            heapify(arr, largest, heapSize);
        }
    }

    // 迭代版本，适用实际工作场景，要防止递归爆栈
    public static void heapify2(int[] arr, int index, int heapSize) {
        while (true) {
            int L = 2 * index + 1;
            int R = 2 * index + 2;
            int largest = index;
            if (L < heapSize && arr[L] > arr[largest]) largest = L;
            if (R < heapSize && arr[R] > arr[largest]) largest = R;
            if (largest == index) break;
            swap(arr, index, largest);
            index = largest;
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) { //此处是边界条件
            swap(arr, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
