package Leetcode.AimOffer;

/**
 * 11. 旋转数组的最小数字
 * Easy
 */
public class _11_minArray {
    public static void main(String[] args) {
        int[] numbers = {3, 4, 5, 1, 2};
        System.out.println(minArray(numbers));
    }

    public static int minArray(int[] numbers) {

        // 一、双指针
//        if (numbers == null)
//            return -1;
//        if (numbers.length < 2)
//            return numbers[0];
//
//        int left = 0, right = 1;
//        while (right < numbers.length) {
//            if (numbers[right] < numbers[left])
//                return numbers[right];
//            right++;
//            left++;
//        }
//        return numbers[0];

        // 二、二分法
        // 有序数组
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                // 存在重复元素，无法确定在最小值左侧还是右侧。需要忽略最右端的点。
                right--;
            }
        }
        return numbers[left];
    }
}
