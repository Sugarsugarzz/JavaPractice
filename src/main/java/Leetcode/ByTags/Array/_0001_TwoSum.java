package Leetcode.ByTags.Array;

import java.util.HashMap;

/**
 * 1.两数之和
 */
public class _0001_TwoSum {

    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i=0; i<nums.length; i++) {
            int a = target - nums[i];
            if (hashMap.containsKey(a) && hashMap.get(a)!= i)
                return new int[] {i, hashMap.get(a)};
            hashMap.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};
        int target = 5;
        int[] result = twoSum(nums, target);
        if (result == null)
            System.out.println("无匹配项");
        else
            for (int i: result)
                System.out.println(i);
    }
}
