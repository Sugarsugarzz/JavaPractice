package Leetcode.ByTags.String;

/**
 * 8. 字符串转换整数（atoi）
 * Medium
 */

public class _0008_myAtoi {

    public static void main(String[] args) {

        String s = "";
        int result = myAtoi(s);
        System.out.println(result);
    }

    public static int myAtoi(String str) {

        if (str == null || str.trim().equals(""))
            return 0;
        // 位置；组合的数；正负flag
        int loc = 0, res = 0, flag = 1;
        // 处理空格
        while (str.charAt(loc) == ' ')  loc++;
        if (str.charAt(loc) == '-')  flag = -1;
        if (str.charAt(loc) == '-' || str.charAt(loc) == '+')
            loc++;
        while (loc < str.length() && Character.isDigit(str.charAt(loc))) {
            // 获取该位的数
            int r = str.charAt(loc) - '0';
            // 处理溢出问题
            if ((res > Integer.MAX_VALUE / 10) || (res == Integer.MAX_VALUE / 10 && r > 7))
                return flag > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            // 推入数字
            res = res * 10 + r;
            // 位置递增
            loc++;
        }

        return flag > 0 ? res : -res;
    }
}
