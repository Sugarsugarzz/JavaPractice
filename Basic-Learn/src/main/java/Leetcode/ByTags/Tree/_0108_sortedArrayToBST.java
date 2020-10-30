package Leetcode.ByTags.Tree;

import java.util.Random;

/**
 * 108. 将有序数组转换为二叉搜索树
 * Easy
 */

public class _0108_sortedArrayToBST {

    public static void main(String[] args) {

        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode res = sortedArrayToBST(nums);
        System.out.println(res);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {

        // 有序数组中间为根节点，然后从左右两边取中间的作为左右子节点，递归
        return helper(0, nums.length - 1, nums);
    }

    public static TreeNode helper(int left, int right, int[] nums) {

        if (left > right)
            return null;
        int p = (right + left) / 2;
        // 偶数数列，随机取左边或右边的数
        if ((left + right) % 2 == 1)
            p += new Random().nextInt(2);

        // 构建树
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(left, p - 1, nums);
        root.right = helper(p + 1, right, nums);
        return root;
    }
}
