package Leetcode.easy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.function.IntPredicate;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.xml.namespace.QName;

/**
 * 
 * title: 对称二叉树
 * 
 * description：给定一个二叉树，检查它是否是镜像对称的。
 * 
 * limit:
 * 
 * label：二叉树
 * 
 */

public class _0101_isSymmetric {

	public static void main(String[] args) {
		// 测试
		TreeNode a1 = new TreeNode(1);
		TreeNode b1 = new TreeNode(2);
		TreeNode b2 = new TreeNode(3);
		TreeNode b3 = new TreeNode(4);
		TreeNode c1 = new TreeNode(2);
		TreeNode c2 = new TreeNode(3);
		TreeNode c3 = new TreeNode(4);
		
		a1.left = b1;
		a1.right = c1;
		b1.left = b2;
		b1.right = b3;
		c1.left = c3;
		c1.right = c2;
		
		boolean result = isSymmetric_1(a1);
		System.out.println(result);
	}
	
	// 迭代法
	// 将树的左右节点按照相反的顺序存入队列中。
	// 队列中连续出的两个节点，应该是相等的。
	public static boolean isSymmetric(TreeNode root) {
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		queue.add(root);
		
		while (!queue.isEmpty()) {
			TreeNode p = queue.poll();
			TreeNode q = queue.poll();
			
			if (p == null && q == null)
				continue;
			if (p == null || q == null)
				return false;
			if (p.val != q.val)
				return false;
			
			queue.add(p.left);
			queue.add(q.right);
			queue.add(p.right);
			queue.add(q.left);
		}

		return true;
	}
	
	// 递归法
	public static boolean isSymmetric_1(TreeNode root) {
		if (root == null)
			return true;
		return isSymmetric(root.left, root.right);
	}
	
	public static boolean isSymmetric(TreeNode root1, TreeNode root2) {
		
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		if (root1.val != root2.val)
			return false;
		
		return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
	}
}
