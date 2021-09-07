package leetcode.sort;

import java.util.Arrays;

/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按非递减顺序排序
 */

public class DoublePointSort {

    public int[] sortedSquares1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    public int[] sortedSquares(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;
        int[] sortedArray = new int[nums.length];

        while (left <= right) {
            if (nums[left] * nums[left] >= nums[right] * nums[right]) {
                sortedArray[index] = nums[left] * nums[left];
                left++;
                index--;
            } else {
                sortedArray[index] = nums[right] * nums[right];
                right--;
                index--;
            }
        }
        return sortedArray;
    }

}
