package Leetcode.ByTags.String;

/**
 * 125. 验证回文串
 * Easy
 */

public class _0125_isPalindrome {

    public static void main(String[] args) {

        String s = "A man, a plan, a canal: Panama";
        boolean result = isPalindrome(s);
        System.out.println(result);
    }

    public static boolean isPalindrome(String s) {

        // 双指针，左右向中间遍历
        if (s == null || s.trim().equals(""))
            return true;

        int left = 0, right = s.length() - 1;
        while (left <= right) {
            while (left <= right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left <= right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (left <= right && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)) ) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
