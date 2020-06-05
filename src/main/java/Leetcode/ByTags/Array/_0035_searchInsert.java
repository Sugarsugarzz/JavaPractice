package Leetcode.ByTags.Array;

/**
 * 35. 搜索插入位置
 */

public class _0035_searchInsert {

    public static void main(String[] args) {

        int[] nums = {1, 3, 5, 6};
        int target = 2;
        int result = searchInsert(nums, target);
        System.out.println(result);
    }

    public static int searchInsert(int[] nums, int target) {

        // 边界情况
        if (target > nums[nums.length-1])
            return nums.length;

        // 有序数组，上二分
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid])
                right = mid - 1;
            else if (target > nums[mid])
                left = mid + 1;
            else
                return mid;
        }
        return left;
    }
}
