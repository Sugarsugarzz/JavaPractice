package Leetcode.ByDifficulty.Easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * title: 二叉树的层次遍历 II
 * 
 * description：给定一个二叉树，返回其节点值自底向上的层次遍历。 
 * 			（即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）。
 * 
 * limit:
 * 
 * label：二叉树
 * 
 */

public class _0107_levelOrderBottom {

	public static void main(String[] args) {
		// 测试
		TreeNode a1 = new TreeNode(3);
		TreeNode b1 = new TreeNode(5);
		TreeNode b2 = new TreeNode(10);
		TreeNode c1 = new TreeNode(20);
		TreeNode c2 = new TreeNode(15);
		a1.left = b1;
		a1.right = b2;
		b1.left = c1;
		b1.right = c2;
		
		
		List<List<Integer>> result = levelOrderBottom(a1);
		for (List<Integer> list : result) {
			for (Integer i : list)
				System.out.print(i + " ");
			System.out.println("\n");
		}
	}
	
	// 层次遍历
	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		
		List<List<Integer>> list = new ArrayList<>();
		if (root == null)
			return list;
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			int size = queue.size(); // 得到每一层数的个数
			List<Integer> temp = new ArrayList<>();
			for (int i=0; i< size; i++) {
				TreeNode p = queue.poll();
				temp.add(p.val);
				if (p.left != null)
					queue.offer(p.left);
				if (p.right != null)
					queue.offer(p.right);
			}
			list.add(temp);
		}
		Collections.reverse(list);
		return list;
	}
}
