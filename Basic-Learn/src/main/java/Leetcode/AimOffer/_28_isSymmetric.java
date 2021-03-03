package Leetcode.AimOffer;

import Leetcode.ByTags.Tree.TreeNode;

/**
 * 28. 对称的二叉树
 * Easy
 */
public class _28_isSymmetric {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        System.out.println(isSymmetirc(root));
    }

    public static boolean isSymmetirc(TreeNode root) {
        if (root == null)
            return true;
        return isMatch(root.left, root.right);
    }

    public static boolean isMatch(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null)
            return true;
        if (n1 == null || n2 == null)
            return false;
        if (n1.val != n2.val)
            return false;
        return isMatch(n1.left, n2.right) && isMatch(n1.right, n2.left);
    }
}
