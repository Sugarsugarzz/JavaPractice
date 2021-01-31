package Leetcode.AimOffer;

import Leetcode.ByTags.Tree.TreeNode;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    static Map<Integer, Integer> map = new HashMap<>();
    static int[] pre_order;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        // 1. 划分左右子树数组 递归
//        if (preorder.length == 0 || inorder.length == 0)
//            return null;
//
//        TreeNode root = new TreeNode(preorder[0]);
//        for (int i = 0; i < inorder.length; i++) {
//            if (preorder[0] == inorder[i]) {
//                int[] pre_left = Arrays.copyOfRange(preorder, 1, i+1);
//                int[] pre_right = Arrays.copyOfRange(preorder, i+1, preorder.length);
//                int[] in_left = Arrays.copyOfRange(inorder, 0, i);
//                int[] in_right = Arrays.copyOfRange(inorder, i+1, inorder.length);
//                root.left = buildTree(pre_left, in_left);
//                root.right = buildTree(pre_right, in_right);
//                break;
//            }
//        }
//
//        return root;

        // 2. 递归，利用Map存储inorder下标
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        pre_order = preorder;
        return recur(0, 0, inorder.length - 1);
    }

    public static TreeNode recur(int pre_idx, int in_left_idx, int in_right_idx) {

        if (in_left_idx > in_right_idx)
            return null;

        TreeNode root = new TreeNode(pre_order[pre_idx]);
        int idx = map.get(pre_order[pre_idx]);

        root.left = recur(pre_idx + 1, in_left_idx, idx - 1);
        // 右子树pre_idx = root长度 + 左子树长度 + 1
        root.right = recur(idx - in_left_idx + pre_idx + 1, idx + 1, in_right_idx);

        return root;
    }
}
