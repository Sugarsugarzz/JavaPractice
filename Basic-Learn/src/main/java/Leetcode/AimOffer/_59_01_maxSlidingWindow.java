package Leetcode.AimOffer;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 59-I. 滑动窗口的最大值
 * Easy
 */
public class _59_01_maxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length * k == 0)  return new int[0];

        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.size() > k) {
                queue.poll();
            }
            if (i - k + 1 >= 0) {
                ans[i - k + 1] = nums[queue.peek()];
            }
        }

        return ans;
    }
}
