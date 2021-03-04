package Leetcode.AimOffer;

/**
 * 53-I. 在排序数组中查找数字 I
 * Easy
 */
public class _53_01_search {
    public static void main(String[] args) {
        System.out.println(search(new int []{1, 2, 3, 4, 8, 8, 8}, 8));
    }

    public static int search(int[] nums, int target) {
        int count = 0;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                count++;
                int index = mid;
                while (++index < nums.length && nums[index] == target) count++;
                index = mid;
                while (--index >= 0 && nums[index] == target) count++;
                return count;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return count;
    }
}
