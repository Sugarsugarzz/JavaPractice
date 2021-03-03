package Leetcode.AimOffer;

import Leetcode.ByTags.Tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 32-III. 从上到下打印二叉树
 * Medium
 */
public class _32_03_levelOrder {
    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)  return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag)
                    list.add(node.val);
                else
                    list.add(0, node.val);
                if (node.left != null)  queue.offer(node.left);
                if (node.right != null)  queue.offer(node.right);
            }
            flag = !flag;
            res.add(list);
        }
        return res;

    }


}
