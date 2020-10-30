package Leetcode.ByTags.String;

import sun.security.util.Length;

/**
 * 14.最长公共前缀
 * Easy
 */

public class _0014_longestCommonPrefix {

    public static void main(String[] args) {

        String[] strs = {"flower", "flow", "flight"};
        String result = longestCommonPrefex(strs);
        System.out.println(result);
    }

    public static String longestCommonPrefex(String[] strs) {

        // 两两找公共前缀
        if (strs.length == 0)
            return "";

        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < result.length() && j < strs[i].length(); j++)
                if (strs[i].charAt(j) != result.charAt(j))
                    break;

            result = result.substring(0, j);
            if (result.equals(""))
                return "";
        }
        return result;
    }
}
