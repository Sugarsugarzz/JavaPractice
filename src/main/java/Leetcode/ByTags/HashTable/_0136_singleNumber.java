package Leetcode.ByTags.HashTable;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 136. 只出现一次的数字
 * Easy
 */

public class _0136_singleNumber {

    public static void main(String[] args) {

        int[] nums = {2, 2, 1};
        int res = singleNumber(nums);
        System.out.println(res);
    }

    public static int singleNumber(int[] nums) {

        // 一、Map，记录每个元素的出现次数
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i: nums) {
//            if (map.keySet().contains(i))
//                map.put(i, map.get(i) + 1);
//            else
//                map.put(i, 1);
//        }
//
//        for (Integer i: map.keySet()) {
//            if (map.get(i) == 1)
//                return i;
//        }
//        return -1;

        // 二、Set，题目限定每个元素最多出现两次，如果第二次出现，从set移除即可
//        Set<Integer> set = new HashSet<>();
//        for (int i : nums) {
//            if (set.contains(i))
//                set.remove(i);
//            else
//                set.add(i);
//        }
//        return set.iterator().next();

        // 三、异或运算
        // a⊕b⊕a = (a⊕a)⊕b = 0⊕b = b
        int res = 0;
        for (int i: nums) {
            res ^= i;
        }
        return res;
    }
}
