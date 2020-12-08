package Leetcode.ByTags.Array;

import java.util.Arrays;
import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 * Medium
 */

public class _0215_findKthLargest {

    static Random random = new Random();

    public static void main(String[] args) {

        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));
    }

    public static int findKthLargest(int[] nums, int k) {

        // 一、库实现  O(nlogn)， top-k算法需要O(n)
//        Arrays.sort(nums);
//        int i = nums.length - k;
//        return nums[i];

        // 二、快速排序
        // 快排每轮都会确定一个数的最终位置，所以只要某次划分的数的下标为倒数第k个的时候，就已经找到了答案
        // 在划分过程中，如果该 q 为需要的下标，则直接返回；如果 q 比目标下标小，则递归右子区间；否则递归左子区间
        // time complex O(n), space complex O(log n)
        int target_index = nums.length - k;
        return quickSelect(nums, 0, nums.length - 1, target_index);

        // 三、优先队列


    }

    public static int quickSelect(int[] nums, int left, int right, int target_index) {
        // 得到该轮排序后的数的位置索引
        int q = randomPartition(nums, left, right);
        if (q == target_index) {
            // 如果是目标索引，直接返回
            return nums[q];
        } else {
            // 如果目标索引大于当前索引，则处理右区间，否则处理左区间
            return q < target_index ? quickSelect(nums, q + 1, right, target_index) : quickSelect(nums, left, q - 1, target_index);
        }
    }

    public static int randomPartition(int[] nums, int l, int r) {
        // 1. 从 [l, r] 中随机选一个索引
        int i = random.nextInt(r - l + 1) + l;
        // 2. 交换nums[i], nums[r]，即将随机数先放在 [l, r]最右边的 nums[r] 上
        swap(nums, i, r);
        return partition(nums, l, r);
    }

    public static int partition(int[] nums, int l, int r) {
        // 3. 调用前，已经确定随机数是 nums[r]
        int x = nums[r], i = l - 1;

        // 处理 [l, r] 区间，其中 l <= j <= r
        // for循环使小于x的数值都存在 [l, i] 区间
        for (int j = l; j < r; j++) {
            // 4. nums[j] 与随机数 x 比较，小于x的数都跟 [l, r]左边区间交换
            // i = l - 1，所以 ++i=l，初始索引为l
            if (nums[j] <= x) {
                swap(nums, ++i, j);
            }
        }
        // 将 x 恢复原位
        swap(nums, i + 1, r);

        return i + 1;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
