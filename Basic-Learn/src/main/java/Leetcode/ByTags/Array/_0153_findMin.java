package Leetcode.ByTags.Array;

/**
 * 153. 寻找旋转排序数组中的最小值
 * Medium
 */
public class _0153_findMin {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(findMin(nums));
    }

    public static int findMin(int[] nums) {
        if (nums == null)
            return -1;
        if (nums.length < 2)
            return nums[0];

        int left = 0, right = 1;
        while (right < nums.length) {
            if (nums[right] < nums[left])
                return nums[right];
            right++;
            left++;
        }
        return nums[0];
    }
}
