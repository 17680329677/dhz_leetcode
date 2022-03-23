package kind.sort.learn;

import java.util.Deque;
import java.util.LinkedList;

public class InsertSort {

    /**
     * 插入排序：t: O(n^2) s: O(1) 稳定
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        if(array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    public static void main(String[] args) {
        String s = "123".substring(0, 0);
        System.out.println(s);
    }
}
