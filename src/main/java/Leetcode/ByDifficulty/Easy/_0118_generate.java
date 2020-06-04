package Leetcode.ByDifficulty.Easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * title: 杨辉三角
 * 
 * description：给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 
 * limit: 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 
 * label：数组
 * 
 */

public class _0118_generate {

	public static void main(String[] args) {
		// 测试
		int numRows = 5;
		List<List<Integer>> result = generate(numRows);
		for (List<Integer> list : result) {
			for (Integer i : list) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}
		System.out.println(result);

	}

	// 构造杨辉三角
	public static List<List<Integer>> generate(int numRows) {
		// result存储结果list
		List<List<Integer>> result = new ArrayList<>();
		// 第一个for循环，构造每行的值
		for (int i=1; i<=numRows; i++) {
			List<Integer> list = new ArrayList<>();
			// 第二个for循环，构造每行每位的值
			for (int j=1; j<=i; j++) {
				// 第三行开始，中间的数需要上一行两个数求和得到
				if (i >= 3 && j!=1 && j!=i) {
					list.add(result.get(i-2).get(j-2) + result.get(i-2).get(j-1));
				} else {
					list.add(1);	
				}
			}
			result.add(list);
		}
		return result;
	}
}
