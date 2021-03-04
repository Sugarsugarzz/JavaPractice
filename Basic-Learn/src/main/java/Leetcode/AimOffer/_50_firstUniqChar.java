package Leetcode.AimOffer;

/**
 * 50. 第一个只出现一次的字符
 * Easy
 */
public class _50_firstUniqChar {
    public static void main(String[] args) {
        System.out.println(firstUniqChar("abaccdeff"));
    }

    public static char firstUniqChar(String s) {
        int n = s.length();
        if (n < 1)  return ' ';
        int res = n;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int i = s.indexOf(ch);
            // 判定最前index和最后index是否相同
            if (i >= 0 && i == s.lastIndexOf(ch) && res > i)
                res = i;
        }
        return res == n ? ' ' : s.charAt(res);
    }
}
