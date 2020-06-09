package Leetcode.ByTags.Array;

/**
 * 33. 搜索旋转排序数组
 * Medium
 */

public class _0033_search {

    public static void main(String[] args) {

        int[] nums = {5, 1, 3};
        int target = 3;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {

        // 部分有序，二分查找
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            // 左边有序的情况
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (target <= nums[right] && target > nums[mid])
                    left = mid + 1;
                else
                    right = mid - 1;
            }

        }
        return -1;
    }
}
