package Leetcode.ByTags.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 128. 最长连续序列
 * Hard
 */

public class _0128_longestConsecutive {

    public static void main(String[] args) {

//        int[] nums = {100, 4, 200, 1, 3, 2};
        int[] nums = {2147483647, -2147483648};
//        int[] nums = {1, 2, 0, 1};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {

        // 一、一遍排序、一遍遍历找最长序列（排序超过O(n)的时间复杂度了）
//        if (nums.length == 0)  return 0;
//        Arrays.sort(nums);
//        int max = 1, cur = 1;
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] == nums[i-1])  continue;
//            if (nums[i] == nums[i-1] + 1) {
//                max = Math.max(max, ++cur);
//            } else {
//                cur = 1;
//            }
//        }
//        return max;

        // 二、哈希表
        // 利用空间
        Set<Integer> num_set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int max = 0;
        for (Integer num : num_set) {
            if (!num_set.contains(num - 1)) {
                int curr_num = num, curr_length = 1;
                while (num_set.contains(curr_num + 1)) {
                    if (curr_num == Integer.MAX_VALUE) break;
                    curr_num++;
                    curr_length++;
                }
                max = Math.max(max, curr_length);
            }
        }
        return max;
    }
}
