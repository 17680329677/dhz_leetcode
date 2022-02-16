package kind.sort.learn;

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
        int[] arr = new int[] {2, 1, 6, 4, 3, 5};
        int[] res = InsertSort.insertionSort(arr);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
