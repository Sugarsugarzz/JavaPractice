package Leetcode.AimOffer;

import Leetcode.ByTags.Tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 32-I. 从上到下打印二叉树
 * Medium
 */
public class _32_01_levelOrder {
    public static void main(String[] args) {

    }

    public int[] levelOrder(TreeNode root) {

        if (root == null)
            return new int[0];

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null)  queue.offer(node.right);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        // List直接用转Array需要5ms，神奇
        // res.stream().mapToInt(Integer::intValue).toArray();
        return res;
    }


}
