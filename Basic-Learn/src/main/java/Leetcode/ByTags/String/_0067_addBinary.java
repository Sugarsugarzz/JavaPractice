package Leetcode.ByTags.String;

/**
 * 67. 二进制求和
 * Easy
 */

public class _0067_addBinary {

    public static void main(String[] args) {

        String a = "1";
        String b = "11";
        String result = addBinary(a, b);
        System.out.println(result);
    }

    public static String addBinary(String a, String b) {

        // 将结果反转输出
        StringBuilder sb = new StringBuilder();
        int ca = 0;  // 是否进位
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            sb.append(sum % 2);
            ca = sum / 2;
        }
        sb.append(ca == 1? ca : "");
        return sb.reverse().toString();
    }
}
