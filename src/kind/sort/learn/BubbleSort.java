package kind.sort.learn;

public class BubbleSort {

    /**
     * 冒泡排序： t: O(n^2) s: O(1) 稳定
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;
        // 每遍历一趟，会将最大的元素交换至最后
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 1, 6, 4, 3, 5};
        int[] res = BubbleSort.bubbleSort(arr);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
