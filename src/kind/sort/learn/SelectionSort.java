package kind.sort.learn;


public class SelectionSort {
    /**
     * 选择排序： t: O(n^2) s: O(1) 不稳定
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 1, 6, 4, 3, 5};
        int[] res = SelectionSort.selectionSort(arr);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
