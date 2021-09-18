package leetcode.weiyunsuan;

public class Solution {

    /**
     * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo_1(int n) {
        return n > 0 && (n & -n) == n;
    }

    public boolean isPowerOfTwo_2(int n) {
        int BIG = 2 << 30;
        return n > 0 && BIG % n == 0;
    }

    /**
     * 返回整数二进制中为1的个数
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                res++;
            }
        }
        return res;
    }

    public int hammingWeight_1(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
