package kind.sort.learn;

public class ShellSort {

    public static int[] shellSort(int[] arr) {
        int len = arr.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                // 先按照当前gap找到偏移量为1的元素（即1号元素，从0开始编号）
                temp = arr[i];
                // 0号元素
                int preIndex = i - gap;
                // 如果如果前序元素大，则将大元祖换到后面
                while (preIndex >= 0 && arr[preIndex] > temp) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                // 填坑
                arr[preIndex + gap] = temp;
            }
            gap = gap / 2;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {18, 16, 41, 62, 28, 19, 17, 30, 13, 22};
        int[] res = ShellSort.shellSort(arr);
        for (int i : res) {
            System.out.println(i);
        }
    }
}

