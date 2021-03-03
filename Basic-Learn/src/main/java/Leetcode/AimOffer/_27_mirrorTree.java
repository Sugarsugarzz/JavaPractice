package Leetcode.AimOffer;

import Leetcode.ByTags.Tree.TreeNode;

/**
 * 27. 二叉树的镜像
 * Easy
 */
public class _27_mirrorTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        System.out.println(mirrorTree(root));
    }

    public static TreeNode mirrorTree(TreeNode root) {

        if (root == null)
            return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);

        return root;
    }
}
