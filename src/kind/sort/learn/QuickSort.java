package kind.sort.learn;

import java.util.Arrays;

public class QuickSort {

    /**
     * 快速排序： t: O(nLOGn) 不稳定
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left > right)
            return;
        int base = arr[left];
        int i = left, j = right;
        while (i < j) {
            while (arr[j] >= base && i < j)
                j--;
            while (arr[i] <= base && i < j)
                i++;
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;
        System.out.println(Arrays.toString(arr));
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 1, 6, 4, 3, 5};
        QuickSort.quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
