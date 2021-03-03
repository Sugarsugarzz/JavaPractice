package Leetcode.AimOffer;

import java.util.Arrays;

/**
 * 21. 调整数组顺序使奇数位于偶数前面
 * Easy
 */
public class _21_exchange {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(exchange(nums)));
    }

    public static int[] exchange(int[] nums) {

        // 一、额外空间操作，双指针
//        int[] res = new int[nums.length];
//        int left = 0, right = nums.length - 1;
//        for (int num : nums) {
//            if (num % 2 == 0) {
//                res[right--] = num;
//            } else {
//                res[left++] = num;
//            }
//        }
//        return res;

        // 二、原数组操作，双指针
//        int left = 0, right = nums.length - 1;
//        while (left < right) {
//            if ((nums[left] & 1) == 1) {
//                left++;
//                continue;
//            }
//            if ((nums[right] & 1) == 0) {
//                right--;
//                continue;
//            }
//            int temp = nums[left];
//            nums[left] = nums[right];
//            nums[right] = temp;
//        }
//        return nums;

        // 三、原数组操作，快慢指针
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if ((nums[fast] & 1) == 1) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
            fast++;
        }
        return nums;
    }
}
