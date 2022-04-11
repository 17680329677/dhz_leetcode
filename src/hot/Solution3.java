package hot;

import sun.jvm.hotspot.tools.JStack;

import java.util.*;

public class Solution3 {

    /**
     * 154.旋转数组中的最小元素
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (numbers[mid] < numbers[high]) {
                high = mid;
            } else if (numbers[mid] > numbers[high]) {
                low = mid + 1;
            } else {
                high--;
            }
        }
        return numbers[low];
    }

    /**
     * 45. 跳跃游戏2
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int step = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                step++;
            }
        }
        return step;
    }

    /**
     * 46.全排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        backTrack(n, res, output, 0);
        return res;
    }

    private void backTrack(int n, List<List<Integer>> res, List<Integer> output, int first) {
        if (first == n) {
            res.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            backTrack(n, res, output, first + 1);
            Collections.swap(output, first, i);
        }
    }

    /**
     * 32.最长有效括号
     * 思路一：栈解法
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Deque<Integer> deque = new LinkedList<>();
        deque.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                deque.push(i);
            } else {
                deque.pop();
                if (deque.isEmpty()) {
                    deque.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - deque.peek());
                }
            }
        }
        return maxLen;
    }

    /**
     * 32.最长有效括号
     * 思路二：动态规划
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /**
     * 6.Z字型排列
     * 思路一：借助矩阵
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = 2 * r - 2; // 周期
        int c = (n + t - 1) / t * r - 1;  // r-1 为一个周期需要的列数，此处计算矩阵的列宽
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; i++) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                x++; // 向下移动
            } else {
                x--;
                y++;
            }
        }

        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }

    /**
     * 6.Z字型排列
     * 思路一：压缩矩阵
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        StringBuffer[] mat = new StringBuffer[r];
        for (int i = 0; i < r; ++i) {
            mat[i] = new StringBuffer();
        }
        for (int i = 0, x = 0, t = r * 2 - 2; i < n; ++i) {
            mat[x].append(s.charAt(i));
            if (i % t < r - 1) {
                ++x;
            } else {
                --x;
            }
        }
        StringBuffer ans = new StringBuffer();
        for (StringBuffer row : mat) {
            ans.append(row);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        int[] nums = new int[]{1, 2, 3};
        solution3.permute(nums);
    }
}
