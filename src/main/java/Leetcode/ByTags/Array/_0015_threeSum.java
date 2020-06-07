package Leetcode.ByTags.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 15. 三数之和
 * Medium
 */

public class _0015_threeSum {

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(Arrays.toString(result.toArray()));
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        // 暴力法
        // 超时
//        List<List<Integer>> ans = new ArrayList<>();
//        for (int i=0; i<nums.length; i++) {
//            for (int j=0; j<nums.length; j++) {
//                for (int k=0; k<nums.length; k++) {
//                    if (i!=j && j!=k && k!=i && 0 == nums[i] + nums[j] + nums[k]) {
//                        List<Integer> tmp_list = new ArrayList<>();
//                        tmp_list.add(nums[i]);
//                        tmp_list.add(nums[j]);
//                        tmp_list.add(nums[k]);
//                        // 排序，用于去重
//                        Collections.sort(tmp_list);
//                        if (!ans.contains(tmp_list))
//                            ans.add(tmp_list);
//                    }
//                }
//            }
//        }

        // 双指针
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        // 边界条件
        if (nums == null || len < 3)
            return ans;

        Arrays.sort(nums);
        for (int i=0; i<len; i++) {
            if (nums[i] > 0)
                break;
            if (i>=1 && nums[i] == nums[i-1])
                continue;

            int left = i+1, right = len-1;
            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left<right && nums[left]==nums[left+1])
                        left++;
                    while (left<right && nums[right]==nums[right-1])
                        right--;
                    left++;
                    right--;
                } else if (sum < 0)
                    left++;
                else if (sum > 0)
                    right--;
            }
        }
        return ans;
    }
}
