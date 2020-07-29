package Leetcode.ByTags.Greedy;

/**
 * 55. 跳跃游戏
 * Medium
 */

public class _0055_canJump {

    public static void main(String[] args) {

        int[] nums = {2, 3, 1, 1, 4};
        boolean res = canJump(nums);
        System.out.println(res);
    }

    public static boolean canJump(int[] nums) {

        // 一、顺推
        // 维护一个当前位置和当前能够到达的最远距离变量
        // 在能到达的范围内，计算每个位置上到达的最远距离，与已有最远距离进行比较
//        int longest = 0;
//        for (int i = 0; i <= longest; i++) {
//            longest = Math.max(longest, nums[i] + i);
//            if (longest >= nums.length - 1)
//                return true;
//        }
//        return false;

        // 二、逆推
        int n = nums.length - 1;  // 最后一个位置
        int m = n - 1;  // 倒数第二个位置
        for (int i = m; i >= 0; i--) {
            if (nums[i] >= (n - i))
                n = i;
        }
        return n == 0;
    }
}
