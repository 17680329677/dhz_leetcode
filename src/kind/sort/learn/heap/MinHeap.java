package kind.sort.learn.heap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MinHeap<E extends Comparable<E>> {

    private ArrayList<E> data;

    public MinHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MinHeap() {
        data = new ArrayList<>();
    }

    // 返回堆中元素个数
    public int getSize() {
        return data.size();
    }

    // 判断堆是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent");
        return (index - 1) / 2;
    }

    //返回完全二叉树中索引为index的节点的左孩子的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //返回完全二叉树中索引为index的节点的左孩子的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 交换索引为i，j的值
    public void swap(int i, int j) {
        E t = data.get(i);
        data.set(i, data.get(j));
        data.set(j, t);
    }

    //向堆中添加元素
    public void add(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0) {
            swap(parent(k), k);
            k = parent(k);
        }
    }

    // 找堆中最小的元素
    public E findMin() {
        return data.get(0);
    }

    // 去除堆中最小的元素
    public E extractMin() {
        E min = findMin();
        swap(0, data.size() - 1);
        data.remove(data.size() - 1);
        siftDown(0);
        return min;
    }

    // 取出元素后调整堆
    private void siftDown(int k) {
        while (leftChild(k) < data.size()) {
            int min = leftChild(k);
            if (min + 1 < data.size() && data.get(min).compareTo(data.get(min + 1)) > 0)
                min = rightChild(k);
            if (data.get(k).compareTo(data.get(min)) <= 0)
                break;
            swap(k, min);
            k = min;
        }
    }

    //取出堆中最小的元素,替换为元素e
    public E replace(E e) {
        E min = findMin();
        data.set(0, e);
        siftDown(0);
        return min;
    }

    //heapify操作:将数组转化为堆(从最后一个非叶子节点开始，依次调整堆)
    public MinHeap(E[] arr) {
        data = new ArrayList<>(Arrays.asList(arr));
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public String toString() {
        int[] arr = new int[data.size()];
        int index = 0;
        for (E e : data) {
            arr[index++] = (Integer) e;
        }
        return Arrays.toString(arr);
    }
}
