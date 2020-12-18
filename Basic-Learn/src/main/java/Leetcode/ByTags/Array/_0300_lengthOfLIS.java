package Leetcode.ByTags.Array;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * Medium
 */

public class _0300_lengthOfLIS {

    public static void main(String[] args) {

        int[] nums = {10, 9, 2, 5, 3, 7, 21, 18};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {

        // 一、动态规划
        if (nums.length == 0)  return 0;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ans = 1;
        // dp[i] = max(dp[i], dp[j] + 1)
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
