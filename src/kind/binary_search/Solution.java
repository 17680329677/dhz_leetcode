package kind.binary_search;

/**
 * 二分查找类题目
 * 1. mid = (low + high) / 2  可能溢出
 * 2. mid = low + (high - low) / 2  推荐  无溢出风险
 */
public class Solution {

    /**
     * 69. x的平方根
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x <= 0)
            return 0;
        int high = x / 2;
        int low = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int sqrt = x / mid;
            if (sqrt == mid) {
                return mid;
            } else if (sqrt < mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    /**
     * 744. 寻找比目标字母大的最小字母
     * 方法一：用set或长度为26的数组记录每个字母是否存在，然后一次判断target++是否存在
     * 方法二：循环遍历找第一个大于target的字母即可
     * 方法三：二分法，找到target的插入位置即可，如果最后low位置在最后 证明没有比target大的 返回0（取模）
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0, high = letters.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (letters[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return letters[low % letters.length];
    }

    /**
     * 540. 有序数组中的唯一元素
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.mySqrt(4);
    }

    /**
     * 278.找出第一个错误版本
     * 思路：简单二分法即可
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        if (n == 1)
            return 1;
        int low = 1, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean isBadVersion(int n) {
        return true;
    }


    /**
     * 153.寻找旋转（旋转n次就是循环n次）数组中的最小值（数组原本按照升续排列）
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low];
    }

    /**
     * 34.
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int leftIndex = binarySearch(nums, target, true);
        int rightIndex = binarySearch(nums, target, false) - 1;
        if (leftIndex <= rightIndex && rightIndex < nums.length &&
                nums[leftIndex] == target && nums[rightIndex] == target) {
            return new int[] {leftIndex, rightIndex};
        }
        return new int[] {-1, -1};
    }

    private int binarySearch(int[] nums, int target, boolean lower) {
        int low = 0, high = nums.length - 1;
        int ans = nums.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                high = mid - 1;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
