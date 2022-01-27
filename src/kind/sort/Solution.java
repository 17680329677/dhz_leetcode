package kind.sort;

/*
    排序相关算法
 */

import java.util.*;

public class Solution {

    Random rand=new Random();

    /*
        215. topK
        返回数组中最大的第k个元素
     */

    /**
     * 解法一：快速排序法
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums,k,0,nums.length-1);
    }
    private int quickSort(int[] nums,int k,int left,int right){
        int index=rand.nextInt(right-left+1)+left;
        int flag=nums[index];
        nums[index]=nums[left];
        int i=left,j=right;
        while (i<j){
            while (i<j&&nums[j]<=flag) j--;
            nums[i]=nums[j];
            while (i<j&&nums[i]>=flag) i++;
            nums[j]=nums[i];
        }
        nums[i]=flag;
        if (i==k-1) return nums[i];
        else if (i<k-1) return quickSort(nums,k,i+1,right);
        else return quickSort(nums,k,left,i-1);
    }
    // todo: 最小堆

    /*
        347. 前k个高频元素
        给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     */

    /**
     * 解法一：桶排序
     *
     * 设置若干个桶，每个桶存储出现频率相同的数。桶的下标表示数出现的频率，即第 i 个桶中存储的数出现的频率为 i。
     * 把数都放到桶之后，从后向前遍历桶，最先得到的 k 个数就是出现频率最多的的 k 个数。
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyForNum = new HashMap<>();
        for (int num : nums) {
            frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int key : frequencyForNum.keySet()) {
            int frequency = frequencyForNum.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }
        List<Integer> topK = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
            if (buckets[i] == null) {
                continue;
            }
            if (buckets[i].size() <= (k - topK.size())) {
                topK.addAll(buckets[i]);
            } else {
                topK.addAll(buckets[i].subList(0, k - topK.size()));
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = topK.get(i);
        }
        return res;
    }
    // todo: 基于快排和堆的两种解法

    /*
        451. 根据字符出现的次数排序
     */
    public String frequencySort(String s) {

        Map<Character, Integer> frequencyFromString = new HashMap<>();
        for (char ch : s.toCharArray()) {
            frequencyFromString.put(ch, frequencyFromString.getOrDefault(ch, 0) + 1);
        }
        List<Character>[] bucket = new ArrayList[s.length() + 1];
        for (char ch : frequencyFromString.keySet()) {
            int frequency = frequencyFromString.get(ch);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(ch);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == null)
                continue;
            for (char c : bucket[i]) {
                for (int j = 0; j < i; j++)
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    /*
        75. 颜色分类
        给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }

    public void sortColors2(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; i++) {
            while (i <= p2 && nums[i] == 2) {
                int tmp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = tmp;
                p2--;
            }
            if (nums[i] == 0) {
                int tmp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = tmp;
                p0++;
            }
        }
    }
}
