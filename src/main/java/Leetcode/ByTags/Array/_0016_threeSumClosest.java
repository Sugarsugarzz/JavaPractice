package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * Medium
 */

public class _0016_threeSumClosest {

    public static void main(String[] args) {

        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int result = threeSumClosest(nums, target);
        System.out.println(result);
    }

    public static int threeSumClosest(int[] nums, int target) {

        // 双指针（自己的方法）
//        int min = Integer.MAX_VALUE;
//        int ans = 0;
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length; i++) {
//            if (i > 0 && nums[i] == nums[i-1])
//                continue;
//
//            int left = i + 1, right = nums.length - 1;
//            while (left < right) {
//                int sum = nums[i] + nums[left] + nums[right];
//                int diff = Math.abs(target-sum);
//
//                if (sum == target)
//                    return sum;
//                else if (sum < target) {
//                    if (diff < min) {
//                        min = diff;
//                        ans = sum;
//                    }
//                    left++;
//                } else if (sum > target) {
//                    if (diff < min) {
//                        min = diff;
//                        ans = sum;
//                    }
//                    right--;
//                }
//            }
//        }

        // 双指针
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if (sum < target)
                    left++;
                else if (sum > target)
                    right--;
                else
                    return ans;
            }
        }

        return ans;
    }
}
