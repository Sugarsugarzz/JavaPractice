package Leetcode.ByTags.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 46. 全排列
 * Medium
 */

public class _0046_permute {

    static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};
        for (List<Integer> list : permute(nums)) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> permute(int[] nums) {

        // 一、回溯法
        // 直接在原列表上进行操作（交换）
        if (nums.length == 0)
            return res;

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        backtrack(nums.length, list, 0);
        return res;
    }

    public static void backtrack(int n, List<Integer> list, int index) {
        // index 表示处理到第几位

        // 处理到最后一位，即产生结果
        if (n == index) {
            res.add(new ArrayList<>(list));  // 这里需要拷贝一份
        }

        for (int i = index; i < n; i++) {
            Collections.swap(list, index, i);
            backtrack(n, list, index + 1);
            Collections.swap(list, index, i);
        }
    }
}
