package kind.tanxin;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import kind.sort.learn.QuickSort;

import java.util.*;

public class Solution {

    /**
     * 455. 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     *
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        //QuickSort.quickSort(g, 0, g.length - 1);
        //QuickSort.quickSort(s, 0, s.length - 1);
        Arrays.sort(g);
        Arrays.sort(s);
        int indexG = 0, indexS = 0;
        while (indexG < g.length && indexS < s.length) {
            if (g[indexG] <= s[indexS]) {
                indexG++;
            }
            indexS++;
        }
        return indexG;
    }

    /**
     * 435.无重叠区间：给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     * 思路一：贪心算法
     * 找到包含最多区间的一个子集，使得子集中的各集合互不重叠，用集合长度减去子集长度即可
     * 具体地，按照集合中区间的右端点进行排序，选定排序后第一个集合为子集中首个元素，循环寻找剩余与首元素不重叠的区间
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int maxLength = 1;
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= right) {
                right = intervals[i][1];
                maxLength++;
            }
        }
        return intervals.length - maxLength;
    }

    /**
     * 452. 用最少的箭引爆气球
     * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
     * 输出：2
     * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
     *
     * 思路：贪心算法，将集合中的气球按照按照右端点排序，每次射出一支箭，使得改箭正好位于一个气球的右端点，这样可以保证原本会
     * 爆的气球正好还能爆，而且尽可能的多爆其他气球
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0)
            return 0;
        // 按照右端点对集合进行排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 此处不可和上一题一样，为了防止int越界，直接用> < 判断，不能直接返回o1 - o2
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int res = 1;
        int right = points[0][1];
        for (int[] ballon : points) {
            if (ballon[0] > right) {
                res++;
                right = ballon[1];
            }
        }
        return res;
    }

    /**
     * 406. 根据身高重建队列
     * 解题思路：先排序再插入
     * 1.排序规则：按照先H高度降序，K个数升序排序
     * 2.遍历排序后的数组，根据K插入到K的位置上
     *
     * 核心思想：高个子先站好位，矮个子插入到K位置上，前面肯定有K个高个子，矮个子再插到前面也满足K的要求
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        // 再一个一个插入。
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        LinkedList<int[]> list = new LinkedList<>();
        for (int[] i : people) {
            list.add(i[1], i);
        }

        return list.toArray(new int[list.size()][2]);
    }

    /**
     * 121. 买卖股票的最佳时机
     * 思路：记录最低位的价格，一次遍历不断更新最低点
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice)
                minPrice = prices[i];
            if (prices[i] - minPrice > maxProfit)
                maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }

    /**
     * 122. 买卖股票的最佳时机2 (可以多次买卖)
     * 思路：对于 [a, b, c, d]，如果有 a <= b <= c <= d ，那么最大收益为 d - a。
     * 而 d - a = (d - c) + (c - b) + (b - a)
     * 因此当访问到一个 prices[i] 且 prices[i] - prices[i-1] > 0，那么就把 prices[i] - prices[i-1] 添加到收益中。
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += (prices[i] - prices[i - 1]);
            }
        }
        return profit;
    }

    /**
     * 605. 种花问题：0为没有花，1为有花，不能有连续两朵及以上的化种在一起，是否可以插入n朵花？
     * 思路：
     * 【1】当遍历到index遇到1时，说明这个位置有花，那必然从index+2的位置才有可能种花，因此当碰到1时直接跳过下一格。
     * 【2】当遍历到index遇到0时，由于每次碰到1都是跳两格，因此前一格必定是0，此时只需要判断下一格是不是1即可得出index这一格能不能种花，如果能种则令n减一，然后这个位置就按照遇到1时处理，即跳两格；如果index的后一格是1，说明这个位置不能种花且之后两格也不可能种花（参照【1】），直接跳过3格。
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 如果花种完了，或者花床检查完了，都停止遍历
        for (int i = 0, len = flowerbed.length; i < len && n > 0;) {
            if (flowerbed[i] == 1) {
                //即如果，当前i位置已经种植了话，那么下一个可以种花的位置是 i+2
                i += 2;
            } else if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                //这里很关键
                //如果是最后一个位置了，那么肯定能够种植（i==flowerbed.length-1)
                //如果不是，则还需要确保 可种花的位置(i+2)紧邻其后的(i+2+1)的位置没有种植（flowerbed[i+1]==0)
                //只有这样才可以种植
                n--;
                //同时找出下一个可以种植的位置
                i += 2;
            } else {
                //这种情况是flowerbed[i+2+1]=1，所以下次循环就从这里重新开始判断其后可种植的位置
                i += 3;
            }
        }
        return n <= 0;
    }

    /**
     * 605. 种花问题：0为没有花，1为有花，不能有连续两朵及以上的化种在一起，是否可以插入n朵花？
     * 思路：数学归纳法 分区统计连续0区间即可，连续n个0可以种（n - 1） / 2
     * 在开头和结尾分别补0，可以避免数组长度为0，1，2的特殊情况
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0)
            return n == 0;
        int continueZero = 1; // 数组首位补零
        int canPlace = 0;
        for (int bed : flowerbed) {
            if (bed == 0) {
                continueZero++;
            } else {
                canPlace += (continueZero - 1) / 2;
                continueZero = 0;
                if (canPlace >= n)
                    return true;
            }
        }
        // 尾部补0，同时防止了continueZero未清零的情况和尾部连续零导致的归纳公式不一致情况
        continueZero++;
        canPlace += (continueZero - 1) / 2;
        return canPlace >= n;
    }

    /**
     * 392. 判断字符串s是否为字符串t的子序列
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        int i = 0, j = 0;
        while (i < sLen && j < tLen) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == sLen;
    }

    /**
     * 665. 非递减数列
     * 思路：首先只能存在一组非递减的组合
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n -1; i++) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                count++;
                if (count > 1) {
                    return false;
                }
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }

    /**
     * 53. 最大子数组之和（连续子数组）
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preSum = nums[0];
        int maxSum = preSum;
        for (int i = 1; i < nums.length; i++) {
            preSum = preSum > 0 ? preSum + nums[i] : nums[i];
            maxSum = Math.max(preSum, maxSum);
        }
        return maxSum;
    }

    /**
     * 763. 划分字母区间：字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }
}
