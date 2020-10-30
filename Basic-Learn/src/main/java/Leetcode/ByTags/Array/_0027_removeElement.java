package Leetcode.ByTags.Array;

/**
 * 27. 移除元素
 * Easy
 */

public class _0027_removeElement {

    public static void main(String[] args) {

        int[] nums = {3};
        int val = 3;
        int result = removeElement(nums, val);
        System.out.println(result);
    }

    public static int removeElement(int[] nums, int val) {

        int p = 0;
        // 遇到val就跳过，非val就将前指针后移一位，填上后面指针所指的值。
        // i得从0开始，不能漏掉每一个值。
        for (int i=0; i<nums.length; i++)
            if (nums[i] != val)
                nums[p++] = nums[i];

        return p;
    }
}
