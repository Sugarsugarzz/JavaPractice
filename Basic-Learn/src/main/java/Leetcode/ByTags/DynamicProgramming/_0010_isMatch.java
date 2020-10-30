package Leetcode.ByTags.DynamicProgramming;

import java.util.Arrays;

/**
 * 10. 正则表达式匹配
 * Difficult
 */

public class _0010_isMatch {

    public static void main(String[] args) {

        String s = "bb";
        String p = ".bab";
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {

        // 一、动态规划
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];

        dp[0][0] = true;

        for (int i = 1; i <= p.length(); i++) {
            char ch = p.charAt(i-1);
            if (i != 1 && ch == '*')
                dp[0][i] = dp[0][i-2];
            else
                dp[0][i] = false;
        }

        for (int i = 1; i <= s.length(); i++) {
            char ch1 = s.charAt(i-1);
            for (int j = 1; j <= p.length(); j++) {
                char ch2 = p.charAt(j-1);
                if (ch1 == ch2 || ch2 == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (ch2 == '*') {
                    if (j > 1) {
                        if (dp[i][j-2]) {
                            dp[i][j] = true;
                        } else {
                            char prev = p.charAt(j-2);
                            if (ch1 == prev || prev == '.') {
                                dp[i][j] = dp[i-1][j];
                            }
                        }
                    }
                }
            }
        }

        for (boolean[] booleans : dp) {
            System.out.println(Arrays.toString(booleans));
        }

        return dp[s.length()][p.length()];

    }
}
