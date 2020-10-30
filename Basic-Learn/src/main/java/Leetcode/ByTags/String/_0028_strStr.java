package Leetcode.ByTags.String;

import java.util.HashMap;

/**
 * 28. 实现strStr()
 * Easy
 */

public class _0028_strStr {

    public static void main(String[] args) {

        String haystack = "";
        String needle = "";
        int result = strStr(haystack, needle);
        System.out.println(result);
    }

    public static int strStr(String haystack, String needle) {

        // 逐个匹配，自己的方法，效率不高
        if (needle.equals(""))
            return 0;
        for (int i = 0; i < haystack.length(); i++) {
            int cur = i;
            for (int j = 0; j < needle.length(); j++) {
                if (cur >= haystack.length())
                    return -1;
                else if (haystack.charAt(cur) != needle.charAt(j))
                    break;
                else if (j == needle.length() - 1 && haystack.charAt(cur) == needle.charAt(j))
                    return i;
                cur++;
            }
        }

        return -1;

        // 使用KMP算法
    }
}
