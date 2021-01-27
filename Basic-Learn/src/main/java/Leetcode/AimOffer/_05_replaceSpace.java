package Leetcode.AimOffer;

/**
 *  05. 替换空格
 *  Easy
 */

public class _05_replaceSpace {

    public static void main(String[] args) {
        String s = "We are happy";
        System.out.println(replaceSpace(s));
    }

    public static String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
