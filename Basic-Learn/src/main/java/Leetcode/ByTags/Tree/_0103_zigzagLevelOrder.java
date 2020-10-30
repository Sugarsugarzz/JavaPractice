package Leetcode.ByTags.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层次遍历
 * Medium
 */

public class _0103_zigzagLevelOrder {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> res = zigzagLevelOrder(root);
        System.out.println(res);
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        // BFS
        List<List<Integer>> res = new ArrayList<>();

        if (root == null)
            return res;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 在102题基础上，加个这个标识是否是左向右即可
        boolean isClockwise = true;

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.removeFirst();
                if (isClockwise) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            isClockwise = !isClockwise;
            res.add(list);
        }

        return res;
    }
}
