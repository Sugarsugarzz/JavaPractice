package Leetcode.AimOffer;

/**
 *  03. 数组中重复的数字
 *  Easy
 */

public class _03_findRepeatNumber {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(nums));
    }

    public static int findRepeatNumber(int[] nums) {

        // 一、额外空间
//        int[] tmp = new int[nums.length];
//        for (int num : nums) {
//            if (tmp[num] == 0) {
//                tmp[num] = 1;
//            } else {
//                return num;
//            }
//        }
//        return 0;

        // 二、原数组处理，将数字对应下标位的数转为负数
//        int num;
//        for (int i = 0; i < nums.length; i++) {
//            num = Math.abs(nums[i]);
//            if (nums[num] >= 0) {
//                nums[num] = -nums[num];
//            } else {
//                return num;
//            }
//        }
//        return 0;

        // 三、原数组处理，将数字置换到下标位
        int temp;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
}
