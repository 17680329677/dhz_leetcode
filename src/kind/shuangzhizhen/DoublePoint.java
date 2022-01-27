package kind.shuangzhizhen;

import struct.ListNode;

import java.util.List;

/**
 * 双指针相关题目
 */

public class DoublePoint {

    /*
        167. 两数之和-有序数组版
        在有序数组中找出两个数，使它们的和为 target
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            if (numbers[low] + numbers[high] < target) {
                low++;
            } else if (numbers[low] + numbers[high] > target) {
                high--;
            } else {
                res[0] = low + 1;
                res[1] = high + 1;
                return res;
            }
        }
        return res;
    }

    /*
        633. 两数平方和
        给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
     */
    public static boolean judgeSquareSum(int c) {
        if (c == 0 || c == 1)
            return true;
        long i = 0;
        long j = (long) Math.sqrt(c);
        while (i <= j) {
            long powSum = i * i + j * j;
            if (powSum == c) {
                return true;
            } else if (powSum > c) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    /*
        345. 反转字符串中的元音字母
     */
    public String reverseVowels(String s) {
        int length = s.length();
        char[] arr = s.toCharArray();
        int i = 0, j = length - 1;
        while (i < j) {
            while (i < length && !isVowel(arr[i]))
                i++;
            while (j > 0 && !isVowel(arr[j]))
                j--;
            if (i < j) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        return new String(arr);
    }

    public boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    /*
        680. 判断一个字符串最多删除一个字符的前提下是否可以构成回文
     */
    public boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j) && isPalindrome(s, i, j - 1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    /*
        88. 合并两个有序数组（需要从尾开始遍历，否则在 nums1 上归并得到的值会覆盖还未进行归并比较的值。）
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1;
        int indexMerge = m + n - 1;
        while (index2 >= 0) {
            if (index1 < 0) {
                nums1[indexMerge--] = nums2[index2--];
            } else if (index2 < 0) {
                nums1[indexMerge--] = nums1[index1--];
            } else if (nums1[index1] > nums2[index2]) {
                nums1[indexMerge--] = nums1[index1--];
            } else {
                nums1[indexMerge--] = nums2[index2--];
            }
        }
    }

    /*
        88. 环形链表判断(两个指针，一个一次走一步，另一个走两步，若有环，则必定相遇)
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode l1 = head, l2 = head.next;
        while (l1 != null && l2 != null && l2.next != null) {
            if (l1 == l2) {
                return true;
            }
            l1 = l1.next;
            l2 = l2.next.next;
        }
        return false;
    }

    /*
        524. 通过删除字母匹配到字典里最长单词
        给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
        如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
     */
    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for (String t : dictionary) {
            int i = 0, j = 0;
            while (i < t.length() && j < s.length()) {
                if (t.charAt(i) == s.charAt(j)) {
                    ++i;
                }
                ++j;
            }
            if (i == t.length()) {
                if (t.length() > res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }
        return res;
    }
}
