package Leetcode.AimOffer;

import Leetcode.ByTags.Tree.TreeNode;

import java.util.Arrays;

/**
 *  07. 重建二叉树
 *  由前序和中序重建
 *  Medium
 */

public class _07_buildTree {

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        buildTree(preorder, inorder);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 0 || inorder.length == 0)
            return null;

        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                int[] pre_left = Arrays.copyOfRange(preorder, 1, i+1);
                int[] pre_right = Arrays.copyOfRange(preorder, i+1, preorder.length);
                int[] in_left = Arrays.copyOfRange(inorder, 0, i);
                int[] in_right = Arrays.copyOfRange(inorder, i+1, inorder.length);
                root.left = buildTree(pre_left, in_left);
                root.right = buildTree(pre_right, in_right);
                break;
            }
        }

        return root;
    }
}
