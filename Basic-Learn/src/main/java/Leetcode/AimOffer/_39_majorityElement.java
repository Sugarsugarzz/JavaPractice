package Leetcode.AimOffer;

/**
 * 39. 数组中出现次数超过一半的数字
 * Easy
 */
public class _39_majorityElement {
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2, 2, 2, 1, 3}));
    }

    public static int majorityElement(int[] nums) {
        int mid = nums.length / 2;
        sort(nums, 0, nums.length - 1, mid);
        return nums[mid];
    }

    public static void sort(int[] nums, int lo, int hi, int k) {
        if (lo >= hi) {
            return;
        }
        int j = partition(nums, lo, hi);
        if (j == k) {
            return;
        } else if (j > k) {
            sort(nums, lo, j - 1, k);
        } else {
            sort(nums, j + 1, hi, k);
        }
    }

    public static int partition(int[] nums, int lo, int hi) {
        int val = nums[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (nums[++i] < val) if (i == hi)  break;
            while (nums[--j] > val) if (j == lo)  break;
            if (i >= j)  break;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        nums[lo] = nums[j];
        nums[j] = val;
        return j;
    }
}

