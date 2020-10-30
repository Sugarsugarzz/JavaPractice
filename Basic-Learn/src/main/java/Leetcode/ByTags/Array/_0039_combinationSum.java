package Leetcode.ByTags.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * Medium
 */

public class _0039_combinationSum {

    public static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {

        int[] nums = {2, 3, 6, 7};
        int target = 5;
        List<List<Integer>> result = combinationSum(nums, target);
        for (List<Integer> list: result)
            System.out.println(Arrays.toString(list.toArray()));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0 || target < 0)
            return result;

        List<Integer> list = new ArrayList<>();
        backtrack(0, candidates, target, list);
        return result;
    }

    public static void backtrack(int start, int[] candidates, int target, List<Integer> list) {
        // 递归终止条件
        if (target < 0)
            return;
        if (target == 0)
            result.add(new ArrayList<>(list));
        else
            // 可以重复使用当前数字，所以回溯依然从i开始
            for (int i = start; i < candidates.length; i++) {
                list.add(candidates[i]);
                backtrack(i, candidates, target - candidates[i], list);
                // 那些add上的无用数，都要在这remove掉
                list.remove(list.size() - 1);
            }
    }

}
