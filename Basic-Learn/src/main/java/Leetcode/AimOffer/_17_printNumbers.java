package Leetcode.AimOffer;

import java.util.Arrays;

/**
 * 17. 打印从1到最大的n位数
 * Easy
 */
public class _17_printNumbers {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(printNumbers(5)));
    }

    static int[] res;
    static int count = 0, nine = 0, start, n;
    static char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static int[] printNumbers(int nn) {

        // 一、10^n - 1
//        int target = (int) Math.pow(10, n) - 1;
//        int[] res = new int[target];
//        for (int i = 1; i <= target; i++) {
//            res[i-1] = i;
//        }
//        return res;

        // 二、考虑大数问题
        // 只能用字符串来模拟处理
        // 递归全排列
        n = nn;
        res = new int[(int)Math.pow(10, n) - 1];
        num = new char[n];
        start = n - 1;
        dfs(0);
        return res;
    }

    public static void dfs(int x) {
        if (n == x) {
            // 用 start 限制左边界，只有 n - start == nine 才扩充左边界，防止多余 0 生成
            String s = String.valueOf(num).substring(start);
            if (!s.equals("0")) {
                res[count++] = Integer.parseInt(s);
            }
            if (n - start == nine) {
                start--;
            }
            return;
        }

        for (char ch: loop) {
            if (ch == '9')  nine++;
            num[x] = ch;
            dfs(x+1);
        }
        nine--;
    }
}
