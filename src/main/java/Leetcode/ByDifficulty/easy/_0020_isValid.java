package Leetcode.ByDifficulty.easy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.print.attribute.standard.NumberUpSupported;
import javax.xml.bind.annotation.W3CDomHandler;

/**
 * 
 * title:给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 
 * limit:有效字符串需满足：
	左括号必须用相同类型的右括号闭合。
	左括号必须以正确的顺序闭合。
	注意空字符串可被认为是有效字符串。
	
 * label：字符串
 */

public class _0020_isValid {

	public static void main(String[] args) {

		// 测试
		String s = "(])";
		boolean result = isValid(s);
		System.out.println(result);
	}
	
	// 自己的方法
	// 哈希存储对应关系，利用栈进行括号匹配
	public static boolean isValid(String s) {
		
		// 奇数个直接false
		if (s.length() % 2 != 0) return false;
		
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		
		Stack<Character> stack = new Stack<Character>();
		char[] cs = s.toCharArray();
		for (int i=0; i<cs.length; i++) {
			if (map.containsValue(cs[i]))
				stack.push(cs[i]);
			if (map.containsKey(cs[i])) {
				if (stack.isEmpty()) return false;
				if (stack.peek() == map.get(cs[i])) 
					stack.pop();
				else
					stack.push(cs[i]);
			}
		}
		if (stack.isEmpty())
			return true;
		else
			return false;
	}
	
}
