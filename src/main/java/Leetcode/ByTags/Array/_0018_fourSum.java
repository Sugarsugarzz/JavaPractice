package Leetcode.ByTags.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * Medium
 */

public class _0018_fourSum {

    public static void main(String[] args) {

        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> result = fourSum(nums, target);
        for (List<Integer> list: result)
            System.out.println(Arrays.toString(list.toArray()));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        // 双指针
        // 固定两个点做双指针
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0; i < len - 3; i++) {
            // 剪枝
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            if (nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target)
                break;
            if (nums[i] + nums[len-1] + nums[len-2] + nums[len-3] < target)
                continue;

            for (int j = i + 1; j < len - 2; j++) {
                // 剪枝
                if (j - i > 1 && nums[j] == nums[j-1])
                    continue;
                if (nums[i] + nums[j] + nums[len-1] + nums[len-2] < target)
                    continue;
                if (nums[i] + nums[j] + nums[j+1] + nums[j+2] > target)
                    break;

                int left = j + 1, right = len - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left+1])
                            left++;
                        while (left < right && nums[right] == nums[right-1])
                            right--;
                        left++;
                        right--;
                    } else if (sum < target)
                        left++;
                    else
                        right--;
                }

            }
        }

        return result;
    }
}
