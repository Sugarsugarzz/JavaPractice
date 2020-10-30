package Leetcode.ByTags.String;

/**
 * 38. 外观数列
 * Easy
 */

public class _0038_countAndSay {

    public static void main(String[] args) {

        int n = 4;
        String result = countAndSay(n);
        System.out.println(result);

    }

    public static String countAndSay(int n) {

        if (n == 1)
            return "1";
        int pre = 0;
        int cur = 1;
        String str = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();

        // 遍历字符串，遇到不同则将数的长度和数存入sb
        for (; cur < str.length(); cur++) {
            if (str.charAt(cur) != str.charAt(pre)) {
                int count = cur - pre;
                sb.append(count).append(str.charAt(pre));
                pre = cur;
            }
        }
        // 未遍历完，还有一段重复数
        if (pre < cur)
            sb.append(cur - pre).append(str.charAt(pre));

        return sb.toString();
    }
}
