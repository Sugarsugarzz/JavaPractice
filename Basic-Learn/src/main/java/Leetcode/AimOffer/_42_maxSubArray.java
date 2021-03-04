package Leetcode.AimOffer;

/**
 * 42. 连续子数组的最大和
 * Easy
 */
public class _42_maxSubArray {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > 0)
                nums[i] += nums[i-1];
            res = Math.max(res, nums[i]);
        }
        return res;
    }
}
