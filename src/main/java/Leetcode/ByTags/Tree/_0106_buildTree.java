package Leetcode.ByTags.Tree;

import java.util.Arrays;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * Medium
 */

public class _0106_buildTree {

    public static void main(String[] args) {

        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20 ,3};
        TreeNode root = buildTree(inorder, postorder);
        System.out.println(root.val);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {

        if (inorder.length == 0 || postorder.length == 0)
            return null;

        TreeNode root = new TreeNode(postorder[postorder.length-1]);
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[postorder.length - 1] == inorder[i]) {
                int[] in_left = Arrays.copyOfRange(inorder, 0, i);
                int[] in_right = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                int[] post_left = Arrays.copyOfRange(postorder, 0, i);
                int[] post_right = Arrays.copyOfRange(postorder, i, postorder.length - 1);
                root.left = buildTree(in_left, post_left);
                root.right = buildTree(in_right, post_right);
            }
        }
        return root;
    }
}
