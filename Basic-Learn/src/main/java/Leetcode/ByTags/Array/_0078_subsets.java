package Leetcode.ByTags.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * Medium
 */
public class _0078_subsets {

    static List<List<Integer>> ans = new ArrayList<>();

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};
        for (List<Integer> subset : subsets(nums)) {
            System.out.println(subset);
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        // 一、回溯法
        backtrack(0, nums, new ArrayList<Integer>());
        return ans;
    }

    public static void backtrack(int i, int[] nums, ArrayList<Integer> tmp) {

        ans.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
