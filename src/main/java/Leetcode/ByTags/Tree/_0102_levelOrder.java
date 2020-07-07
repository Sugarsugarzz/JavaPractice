package Leetcode.ByTags.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * Medium
 */

public class _0102_levelOrder {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> res = levelOrder(root);
        System.out.println(res);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {


        // 和107题一样，只不过在res.add的时候，都次都添加到第0位上
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.removeFirst();
                list.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // 107题，res.add(0, list)，这样就倒序了...
            res.add(list);
        }

        return res;
    }
}
