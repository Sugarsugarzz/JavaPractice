package Leetcode.ByTags.Array;

/**
 * 26.删除排序数组中的重复项
 */

public class _0026_removeDuplicates {

    public static void main(String[] args) {

        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int result = removeDuplicates(nums);
        System.out.println(result);
    }

    public static int removeDuplicates(int[] nums) {

        if (nums.length < 2)
            return nums.length;

        // 有序的，用双指针。
        // 相同，则前面指针不变；不同，则前面指针后移一位，修改为后面指针所指的数。
        int p = 0;
        for (int i=1; i<nums.length; i++)
            if (nums[i] != nums[p])
                nums[++p] = nums[i];
        return p+1;
    }
}
