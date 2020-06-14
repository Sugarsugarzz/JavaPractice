package Leetcode.ByTags.String;

/**
 * 58. 最后一个单词的长度
 * Easy
 */

public class _0058_lengthOfLastWord {

    public static void main(String[] args) {

        String s = "Hello World";
        int result = lengthOfLastWord(s);
        System.out.println(result);
    }

    public static int lengthOfLastWord(String s) {

        // String库方法
//        if (s == null || s.trim().equals(""))
//            return 0;
//        String[] sss = s.split(" ");
//        return sss[sss.length - 1].length();

        // 从后向前遍历
        if (s == null)
            return 0;
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                count++;
            } else if (s.charAt(i) == ' ' && count > 0) {
                return count;
            }
        }
        return count;
    }
}
