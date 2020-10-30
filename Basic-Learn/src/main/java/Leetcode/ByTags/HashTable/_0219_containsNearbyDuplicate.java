package Leetcode.ByTags.HashTable;

import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * 202. 存在重复元素 II
 * Easy
 */

public class _0219_containsNearbyDuplicate {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 1, 2, 3};
        System.out.println(containsNearbyDuplicate(nums, 2));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {

        // 一、利用外部空间 Map
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k)
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;

        // 二、利用外部空间 Set，维护一个长度为k的哈希（滑动窗口）
//        Set<Integer> set = new HashSet<>();
//
//        for (int i = 0; i < nums.length; i++) {
//            if (set.contains(nums[i]))
//                return true;
//            set.add(nums[i]);
//            if (set.size() > k) {
//                set.remove(nums[i - k]);
//            }
//        }
//        return false;
    }
}
