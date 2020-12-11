package Leetcode.ByTags.Array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 239. 滑动窗口最大值
 * Hard
 */

public class _0239_maxSlidingWindow {

    public static void main(String[] args) {

        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        // 一、暴力法（超时）
//        int[] ans = new int[nums.length - k + 1];
//        for (int i = 0; i < nums.length; i++) {
//            if (i + k - 1 < nums.length) {
//                int maxNum = Integer.MIN_VALUE;
//                for (int j = i; j <= i + k - 1; j++) {
//                    maxNum = Math.max(maxNum, nums[j]);
//                }
//                ans[i] = maxNum;
//            }
//        }
//        return ans;

        // 二、双向队列
        // 最大元素，首先想到的是大根堆！ O(Nlogk)
        // 借助双向队列，可以从两端以常数时间压入/弹出元素
        // 队列中第一个总是最大的数，如果有更大的数入队，则清空队列将其入队
        // 队列中存储数组下标
        if (nums.length * k == 0)  return new int[0];

        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // 将小于nums[i]的清空
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            if (i + 1 >= k) {
                ans[i - k + 1] = nums[queue.peek()];
            }
        }
        return ans;
    }
}
