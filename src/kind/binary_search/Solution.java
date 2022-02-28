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

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.mySqrt(4);
    }
}
