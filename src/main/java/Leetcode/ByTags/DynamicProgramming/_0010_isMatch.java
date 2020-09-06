package Leetcode.ByTags.DynamicProgramming;

/**
 * 10. 正则表达式匹配
 * Difficult
 */

public class _0010_isMatch {

    public static void main(String[] args) {

        String s = "mississippi";
        String p = "mis*is*p*";
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {

        int index = 0;

        boolean[] dp = new boolean[s.length() + 1];

        for (int i = 0; i < p.length(); i++) {
            switch (p.charAt(i)) {
                case '.':

            }
        }

        return false;
    }
}
