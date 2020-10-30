package Leetcode.ByTags.String;

/**
 * 12. 整数转罗马数字
 * Medium
 */

public class _0012_intToRoman {

    public static void main(String[] args) {

        int num = 1994;
        String result = intToRoman(num);
        System.out.println(result);
    }

    public static String intToRoman(int num) {

        // 贪心算法
        // 构造罗马数字与数的对应关系表，用数去分解这个大整数，使所用的数最少
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int index = 0;
        String result = "";
        while (index < 13) {
            while (num >= nums[index]) {
                result += romans[index];
                num -= nums[index];
            }
            index++;
        }
        return result;
    }
}
