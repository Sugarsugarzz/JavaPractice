package Leetcode.easy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;
import java.util.function.IntPredicate;

/**
 * 
 * title: 相同的树
 * 
 * description：判断两棵树是否相同。
 * 
 * limit:
 * 
 * label：数组
 * 
 */

public class _0100_isSameTree {

	public static void main(String[] args) {
		// 测试
		TreeNode a1 = new TreeNode(1);
		TreeNode a2 = new TreeNode(2);
		TreeNode a3 = new TreeNode(3);
		a1.left = a2;
		a1.right = a3;

		TreeNode b1 = new TreeNode(1);
		TreeNode b2 = new TreeNode(2);
		TreeNode b3 = new TreeNode(3);
		b1.left = b2;
		b1.right = b3;

		boolean result = isSameTree(a1, b1);
		System.out.println(result);
	}

	// 使用队列进行层次遍历
	public static boolean isSameTree(TreeNode p, TreeNode q) {

		if (p == null && q == null)
			return true;
		if (!check(p, q))
			return false;

		ArrayDeque<TreeNode> deqP = new ArrayDeque<TreeNode>();
		ArrayDeque<TreeNode> deqQ = new ArrayDeque<TreeNode>();
		deqP.addLast(p);
		deqQ.addLast(q);

		while (!deqP.isEmpty()) {
			p = deqP.removeFirst();
			q = deqQ.removeFirst();

			if (!check(p, q))
				return false;
			if (p != null) {
				if (!check(p.left, q.left))
					return false;
				if (p.left != null) {
					deqP.addLast(p.left);
					deqQ.addLast(q.left);
				}
				if (!check(p.right, q.right))
					return false;
				if (p.right != null) {
					deqP.addLast(p.right);
					deqQ.addLast(q.right);
				}
			}
		}

		return true;
	}

	public static boolean check(TreeNode p, TreeNode q) {

		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;
		if (p.val != q.val)
			return false;

		return true;
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int x) {
		val = x;
	}
}
