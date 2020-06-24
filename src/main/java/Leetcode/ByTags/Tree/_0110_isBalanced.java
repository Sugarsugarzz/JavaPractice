package Leetcode.ByTags.Tree;

/**
 * 110. 平衡二叉树
 * Easy
 */

public class _0110_isBalanced {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        boolean res = isBalanced(root);
        System.out.println(res);
    }

    public static boolean isBalanced(TreeNode root) {

        return recur(root) != -1;
    }

    public static int recur(TreeNode root) {

        if (root == null)
            return 0;
        int left = recur(root.left);
        if (left == -1)
            return -1;
        int right = recur(root.right);
        if (right == -1)
            return -1;

        return Math.abs(left - right) < 2 ? Math.max(left, right) +1 : -1;
    }
}
