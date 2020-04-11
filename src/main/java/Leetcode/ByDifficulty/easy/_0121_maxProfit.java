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
 * title: 买卖股票的最佳时机
 * 
 * description：给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 
 * limit: 注意你不能在买入股票前卖出股票。
 * 
 * label：数组、动态规划
 * 
 */

public class _0121_maxProfit {

	public static void main(String[] args) {
		// 测试
		int[] prices = {7,1,5,3,6,4};
		int result = maxProfit_2(prices);
		System.out.println(result);
	}

	// 暴力法
	public static int maxProfit(int[] prices) {
		
		int max = 0;
		// 从前到后遍历数组，找到差值最大的值
		for (int i=0; i<prices.length; i++) {
			for (int j=i+1; j<prices.length; j++) {
				int tmp = prices[j] - prices[i];
				if (tmp > max)
					max = tmp;
			}
		}
		return max;
	}
	
	// 动态规划
	public static int maxProfit_2(int[] prices) {
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		for (int i=0; i<prices.length; i++) {
			if (prices[i] < min)
				// 谷底
				min = prices[i];
			else if (prices[i] - min > max)
				// 谷峰 - 谷底
				max = prices[i] - min;
		}

		return max;
	}
}
