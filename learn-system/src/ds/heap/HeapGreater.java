package ds.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆
 * 系统提供的堆无法实现的功能:
 *   1、已经入堆的元素，如果参与排序的指标方法变化，系统提供的堆无法做到时间复杂度O(logN)调整！都是O(N)的调整！
 *   2、系统提供的堆只能弹出堆顶，做不到自由删除任何一个堆中的元素，即: 无法在时间复杂度O(logN)内完成！一定会高于O(logN)！
 * 根本原因: 无反向索引表
 * 加强堆如何实现？
 *   1、建立反向索引表
 *   2、建立比较器
 *   3、核心在于各种结构相互配合
 * @author HuanyuLee
 * @date 2023/5/9
 */
public class HeapGreater<T> {
    private ArrayList<T> heap = new ArrayList<>();
    private HashMap<T, Integer> indexMap = new HashMap<>();     //反向索引表，存储了对象T在堆中的位置
    private int heapSize;
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<? super T> comp) {
        this.comp = comp;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (obj != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public List<T> getAllElements() {
        return new ArrayList<>(heap);
    }

    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        while (true) {
            int L = 2 * index + 1;
            int R = 2 * index + 2;
            int largest = index;
            if (L < heapSize && comp.compare(heap.get(L), heap.get(largest)) < 0) largest = L;
            if (R < heapSize && comp.compare(heap.get(R), heap.get(largest)) < 0) largest = R;
            if (largest == index) break;
            swap(largest, index);
            index = largest;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }
}
