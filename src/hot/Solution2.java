package hot;

import java.util.*;

public class Solution2 {

    /**
     * 11.盛水最多的容器
     * 思路：双指针
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int curArea = (right - left) * Math.min(height[left], height[right]);
            if (curArea > maxArea)
                maxArea = curArea;
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    /**
     * 9.回文数
     * 思路一：转换为字符串
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        String s = String.valueOf(x);
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    /**
     * 9.回文数
     * 思路一：反转一半的数字
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0))
            return false;
        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x = x / 10;
        }
        return reverseNum == x || x == reverseNum / 10;
    }

    /**
     * 128. 最长连续序列
     * 思路： 利用set存储元素，若当前数字num - 1在set中，则跳过（应该从连续序列的最左侧开始遍历）
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);
        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curLongest = 1;
                while (set.contains(++num)) {
                    curLongest++;
                }
                longest = Math.max(curLongest, longest);
            }
        }
        return longest;
    }

    /**
     * 475. 供暖器
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        int ans = 0;
        Arrays.sort(heaters);
        for (int house : houses) {
            int i = binarySearch(heaters, house);
            int j = i + 1;
            int leftDistance = i < 0 ? Integer.MAX_VALUE : house - heaters[i];
            int rightDistance = j >= heaters.length ? Integer.MAX_VALUE : heaters[j] - house;
            int curDistance = Math.min(leftDistance, rightDistance);
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (nums[left] > target) {
            return -1;
        }
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    /**
     * 51.N皇后问题
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(res, queens, n, 0, columns, diagonals1, diagonals2);
        return res;
    }

    private void backtrack(List<List<String>> res, int[] queens, int n, int row,
                           Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {

        if (row == n) {
            List<String> board = generateBoard(queens, n);
            res.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(res, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    private List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
