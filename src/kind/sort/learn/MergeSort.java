package kind.sort.learn;

import java.util.Arrays;

public class MergeSort {

    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2)
            return arr;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] resArr = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < resArr.length; index++) {
            if (i >= left.length) {
                resArr[index] = right[j++];
            } else if (j >= right.length) {
                resArr[index] = left[i++];
            } else if (left[i] > right[j]) {
                resArr[index] = right[j++];
            } else {
                resArr[index] = left[i++];
            }
        }
        return resArr;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 1, 6, 4, 3, 5};
        int[] res = MergeSort.mergeSort(arr);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
