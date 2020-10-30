package Leetcode.ByTags.String;

/**
 * 43. 字符串相乘
 * Medium
 */

public class _0043_multiply {

    public static void main(String[] args) {

        String num1 = "123";
        String num2 = "456";
        String res = multiply(num1, num2);
        System.out.println(res);
    }

    public static String multiply(String num1, String num2) {

        // 优化竖式计算
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        // 最多为m+n位
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = res[i + j + 1] + n1 * n2;
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0)
                continue;
            sb.append(res[i]);
        }

        return sb.toString();
    }
}
