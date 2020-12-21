package Leetcode.ByTags.Array;

import java.util.*;

/**
 * 347. 前K个高频元素
 * Medium
 */

public class _0347_topKFrequent {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }

    public static int[] topKFrequent(int[] nums, int k) {

        /**
         * TopK 是面试必问的题目，
         * 其中 堆 和 二叉搜索树 的时间复杂度是 O(NlogK)
         * 计数排序（桶排序） 和 快排变形 的时间复杂度是 O(N)。
         */
        // 一、自带库做统计排序
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums)
//            map.put(num, map.getOrDefault(num, 0) + 1);
//        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
//        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
//
//        int[] ans = new int[k];
//        for (int i = 0; i < k; i++)
//            ans[i] = list.get(i).getKey();
//
//        return ans;

        // 二、堆
        // 利用优先队列实现排序，维护队列大小为k
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() == k) {
                if (queue.peek()[1] < entry.getValue()) {
                    queue.poll();
                    queue.add(new int[]{entry.getKey(), entry.getValue()});
                }
            } else {
                queue.add(new int[]{entry.getKey(), entry.getValue()});
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll()[0];
        }
        return ans;

        // 三、快速排序变形



    }
}
