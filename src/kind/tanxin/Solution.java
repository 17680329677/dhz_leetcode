package kind.tanxin;

import kind.sort.learn.QuickSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

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
}
