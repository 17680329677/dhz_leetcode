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
}
