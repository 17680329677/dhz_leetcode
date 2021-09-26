package leetcode.sum;

import java.util.HashMap;
import java.util.Map;

public class TypeSum {

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int[] res = new int[2];
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                res[0] = i + 1;
                res[1] = j + 1;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }


    /**
     * 二分解法
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSumMy(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i + 1);
        }
        for (int index = 0; index < numbers.length; index++) {
            if (map.containsKey(target - numbers[index])) {
                res[0] = index + 1;
                res[1] = map.get(target - numbers[index]);
            }
        }
        return res;
    }

    /**
     * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。
     * 思路：对于整数 aa 和 bb：
     *
     * 在不考虑进位的情况下，其无进位加法结果为 \texttt{a} \oplus \texttt{b}a⊕b。
     *
     * 而所有需要进位的位为 \texttt{a \& b}a & b，进位后的进位结果为 \texttt{(a \& b) << 1}(a & b) << 1
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        TypeSum typeSum = new TypeSum();
        int[] nums = {2,7,11,15};
        typeSum.twoSum(nums, 9);
    }
}
