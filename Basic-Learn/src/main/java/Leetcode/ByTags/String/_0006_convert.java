package Leetcode.ByTags.String;

/**
 * 6. Z字形变换
 * Medium
 */

public class _0006_convert {

    public static void main(String[] args) {

        String s = "LEETCODEISHIRING";
        String result = convert(s, 3);
        System.out.println(result);
    }

    public static String convert(String s, int numRows) {

        // 一行的直接返回
        if (numRows == 1)
            return s;

        // 确定数组的数量
        int len = Math.min(s.length(), numRows);
        StringBuilder[] rows = new StringBuilder[len];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }
        boolean down = false;
        int loc = 0;

        // down表示状态；每次循环把字符加到对应的行数组中。
        for (int i = 0; i < s.length(); i++) {
            rows[loc].append(s.charAt(i));
            if (loc == 0 || loc == numRows - 1)
                down = !down;
            loc += down ? 1: -1;
        }

        // 将所有数组拼接
        String ans = "";
        for (StringBuilder row: rows) {
            ans += row.toString();
        }
        return ans;
    }
}
