package Leetcode.AimOffer;

import Leetcode.ByTags.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 26. 树的子结构
 * Medium
 */
public class _26_isSubStructure {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.right = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(2);

        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(1);

        System.out.println(isSubStructure(root1, root2));
    }

    public static boolean isSubStructure(TreeNode A, TreeNode B) {

        // 一、BFS
//        if (A == null || B == null)
//            return false;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(A);
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            if (node.val == B.val) {
//                boolean b = isSameTree(node, B);
//                if (b)
//                    return true;
//            }
//            if (node.left != null)  queue.offer(node.left);
//            if (node.right != null)  queue.offer(node.right);
//        }
//        return false;

        // 二、DFS
        if (A == null || B == null)
            return false;
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public static boolean isSameTree(TreeNode A, TreeNode B) {
        if (B == null)
            return true;
        if (A == null)  // A == null, B != null
            return false;
        if (A.val != B.val)
            return false;
        return isSameTree(A.left, B.left) && isSameTree(A.right, B.right);
    }

    public static boolean recur(TreeNode A, TreeNode B) {
        if (B == null)  return true;
        if (A == null || A.val != B.val)  return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}
