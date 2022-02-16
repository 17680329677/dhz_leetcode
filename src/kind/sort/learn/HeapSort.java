package kind.sort.learn;

import kind.sort.learn.heap.MaxHeap;
import kind.sort.learn.heap.MinHeap;

public class HeapSort {

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {18, 16, 41, 62, 28, 19, 17, 30, 13, 22};
        MaxHeap<Integer> maxHeap = new MaxHeap<>(arr);
        System.out.println(maxHeap);
        MinHeap<Integer> minHeap = new MinHeap<>(arr);
        System.out.println(minHeap);
    }
}
