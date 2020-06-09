package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * Medium
 */

public class _0034_searchRange {

    public static void main(String[] args) {

        int[] nums = {2, 2};
        int target = 2;
        int[] result = searchRange(nums, target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] searchRange(int[] nums, int target) {

        // 二分法 （自己的方法，最坏情况复杂度还是为O(n)）
        // 和二分一样，找到目标值的位置，然后向左右遍历找最前后的位置
        int left = 0, right = nums.length - 1;
        while (left <= right) {

            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                int start = mid, end = mid;
                while (start > 0 && nums[start-1] == target) start--;
                while (end < nums.length - 1 && nums[end+1] == target) end++;
                return new int[] {start, end};
            } else if (target < nums[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }
        return new int[] {-1, -1};


    }
}
