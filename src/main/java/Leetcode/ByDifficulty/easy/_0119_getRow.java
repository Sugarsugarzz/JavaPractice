package Leetcode.ByDifficulty.easy;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**
 * 
 * title: 杨辉三角 II
 * 
 * description：给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 
 * limit: 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 
 * label：数组
 * 
 */

public class _0119_getRow {

	public static void main(String[] args) {
		// 测试
		int rowIndex = 3;
		List<Integer> result = getRow(rowIndex);
		System.out.println(result);
	}

	// 与118的区别就是，只要临时存上一行的数即可
	public static List<Integer> getRow(int rowIndex) {
		// tmp临时存储上一行的数
		List<Integer> tmp = new ArrayList<>();
		// 第一个for循环，构造每行的值
		for (int i = 1; i <= rowIndex+1; i++) {
			List<Integer> list = new ArrayList<>();
			// 第二个for循环，构造每行每位的值
			for (int j = 1; j <= i; j++) {
				// 第三行开始，中间的数需要上一行两个数求和得到
				if (i >= 3 && j != 1 && j != i) {
					list.add(tmp.get(j - 2) + tmp.get(j - 1));
				} else {
					list.add(1);
				}
			}
			// 临时存储
			tmp = list;
		}

		return tmp;
	}
}
