package hot;

import struct.ListNode;
import struct.TreeNode;

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

    /**
     * 16.最接近的三数之和
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int best = 10000;

        // 枚举a
        for (int a = 0; a < len; a++) {
            // 保证和上次枚举不同
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            // 使用双指针枚举b、c
            int b = a + 1, c = len - 1;
            while (b < c) {
                int sum = nums[a] + nums[b] + nums[c];
                if (sum == target) {
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针到下一个不同的元素
                    int c0 = c - 1;
                    while (b < c0 && nums[c] == nums[c0]) {
                        c0--;
                    }
                    c = c0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int b0 = b + 1;
                    // 移动到下一个不相等的元素
                    while (b0 < c && nums[b0] == nums[b]) {
                        b0++;
                    }
                    b = b0;
                }
            }
        }
        return best;
    }

    /**
     * 26.删除有序数组中的重复项
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int slow = 1, fast = 1;
        while (fast < len) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * 43.字符串相乘
     * 思路一：做加法，设计多次字符串操作和转换
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2))
            return "0";
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addString(ans, curr.reverse().toString());
        }
        return ans;
    }

    private String addString(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int res = x + y + add;
            ans.append(res % 10);
            add = res / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    /**
     * 43.字符串相乘
     * 思路一：做乘法，利用数组存储每位结果
     * @param num1
     * @param num2
     * @return
     */
    public String multiply2(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] = ansArr[i] % 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    /**
     * 124.二叉树中最大路径和
     * @param root
     * @return
     */
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null)
            return 0;
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        int newPathPrice = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, newPathPrice);
        // 返回节点的最大贡献
        return node.val + Math.max(leftGain, rightGain);
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 找出数组中的重复数字
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }

    public int findRepeatNumber2(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int k = nums[i];
            if (k < 0) {
                k += len;
            }
            if (nums[k] < 0) return k;
            nums[k] = nums[k] - len;
        }
        return -1;
    }

    /**
     * 912.数组排序
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left > right)
            return;
        int base = arr[left];
        int i = left, j = right;
        while (i < j) {
            while (arr[j] >= base && i < j)
                j--;
            while (arr[i] <= base && i < j)
                i++;
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;
        System.out.println(Arrays.toString(arr));
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    /**
     * 字符串的全排列
     * 方法一：回溯，注意去重
     * @param s
     * @return
     */
    List<String> res;
    boolean[] visited;
    public String[] permutation(String s) {
        int n = s.length();
        res = new ArrayList<>();
        visited = new boolean[n];
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuffer perm = new StringBuffer();
        backTrack(arr, 0, n, perm);
        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
           ans[i] = res.get(i);
        }
        return ans;
    }

    private void backTrack(char[] arr, int i, int n, StringBuffer perm) {
        if (i == n) {
            res.add(perm.toString());
            return;
        }
        for (int j = 0; j < n; j++) {
            if (visited[j] || (j > 0 && !visited[j - 1] && arr[j - 1] == arr[j])) {
                continue;
            }
            visited[j] = true;
            perm.append(arr[j]);
            backTrack(arr, i + 1, n, perm);
            perm.deleteCharAt(perm.length() - 1);
            visited[j] = false;
        }
    }

    /**
     * 字符串的全排列
     * 方法一：找下一个序列
     * @param s
     * @return
     */
    public String[] permutation2(String s) {
        List<String> ret = new ArrayList<>();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        do {
            ret.add(new String(arr));
        } while (nextPermutation(arr));
        int size = ret.size();
        String[] retArr = new String[size];
        for (int i = 0; i < size; i++) {
            retArr[i] = ret.get(i);
        }
        return retArr;
    }

    private boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1])
            i--;
        if (i < 0)
            return false;
        int j = arr.length - 1;
        while (j >= 0 && arr[i] >= arr[j])
            j--;
        swap(arr, i, j);
        reverse(arr, i + 1);
        return true;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void reverse(char[] arr, int start) {
        int left = start, right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    /**
     * 31. 下一个排列
     * @param nums
     */
    public void nextPermutation3(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void reverse(int[] arr, int start) {
        int left = start, right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }



    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.permutation2("bac");
    }
}
