package Leetcode.ByTags.Array;

import java.util.Arrays;
import java.util.Stack;

/**
 * 581. 最短无序连续子数组
 * Medium
 */

public class _0581_findUnsortedSubarray {

    public static void main(String[] args) {
        int[] nums = {2, 1};
        System.out.println(findUnsortedSubarray(nums));
    }

    public static int findUnsortedSubarray(int[] nums) {

        // 一、排序对比 O(nlongn)
//        int[] compare = nums.clone();
//        Arrays.sort(compare);
//        int left = nums.length, right = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != compare[i]) {
//                left = Math.min(left, i);
//                right = Math.max(right, i);
//            }
//        }
//        return right - left >= 0 ? right - left + 1 : 0;

        // 二、使用栈  time O(n)  space O(n)
        // 顺序和逆序入栈，分别找到无序子数组的左右边界
//        Stack<Integer> stack = new Stack<>();
//        int left = nums.length, right = 0;
//        for (int i = 0; i < nums.length; i++) {
//            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
//                left = Math.min(left, stack.pop());
//            stack.push(i);
//        }
//        stack.clear();
//        for (int i = nums.length - 1; i >= 0; i--) {
//            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
//                right = Math.max(right, stack.pop());
//            stack.push(i);
//        }
//        return right - left >= 0 ? right - left + 1 : 0;

        // 三、不利用额外空间  time O(n)  space O(1)  需要4趟O(n)
        // 找到无序子数组的最大和最小元素的位置，即可确定无序子数组的范围
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                min = Math.min(min, nums[i]);
            }
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i+1]) {
                max = Math.max(max, nums[i]);
            }
        }
        int left, right;
        for (left = 0; left < nums.length; left++) {
            if (nums[left] > min)
                break;
        }
        for (right = nums.length - 1; right >= 0; right--) {
            if (nums[right] < max)
                break;
        }
        System.out.println(left + " _ " + right);
        return right - left >= 0 ? right - left + 1 : 0;
    }
}
