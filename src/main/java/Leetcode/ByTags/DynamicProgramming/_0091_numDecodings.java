package Leetcode.ByTags.DynamicProgramming;


/**
 * 91. 解码方法
 * Medium
 */

public class _0091_numDecodings {

    public static void main(String[] args) {

        String s = "12";
        System.out.println(numDecodings(s));
    }

    public static int numDecodings(String s) {

        // 一、动态规划
        /*
       如果连续的两位数符合条件，则相当于上楼梯的题目，有两种解法：
            1. 一位数决定一个字母
            2. 两位数决定一个字母
            相当于 dp[i] = dp[i-1] + dp[i-2];
       如果不符合条件，有两种情况：
            1.当前数字是0
                无法解码，这阶楼梯不能单独走。
                dp[i] = dp[i-2];
            2.当前数字不是0
                这阶楼梯只能一个一个走。
                dp[i] = dp[i-1];
         */
        int len = s.length();
        if (len == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int[] dp = new int[len + 1];
        dp[0] = 1;

        for (int i = 0; i < len; i++) {
            dp[i+1] = s.charAt(i) == '0' ? 0 : dp[i];
            if (i > 0 && (s.charAt(i-1) == '1' || s.charAt(i-1) == '2' && s.charAt(i) <= '6')) {
                dp[i+1] += dp[i-1];
            }
        }

        return dp[len];
    }
}