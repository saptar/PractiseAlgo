package com.saptar.gfg.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordBreak {

	public static Map<String, Boolean> map = new HashMap<String, Boolean>();

	public static void main(String[] args) {
		// construct the hashmap
		constructHashMap(map);
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			String str = scan.next();
			Map<String, Boolean> dp = new HashMap<String, Boolean>();
			Boolean result = inDict(str, dp);
			System.out.println((result) ? "YES" : "NO");
		}
		scan.close();

	}

	private static Boolean inDict(String str, Map<String, Boolean> dp) {
		// base case
		if (str.length() == 0) {
			return true;
		}
		if (dp.get(str) != null) {
			return dp.get(str);
		}
		for (int i = 1; i <= str.length(); i++) {
			if (map.get(str.substring(0, i)) != null
					&& inDict(str.substring(i, str.length()), dp)) {
				dp.put(str, true);
				return true;
			}
		}
		dp.put(str, false);
		return false;
	}

	private static void constructHashMap(Map<String, Boolean> m) {
		m.put("i", true);
		m.put("like", true);
		m.put("sam", true);
		m.put("sung", true);
		m.put("samsung", true);
		m.put("mobile", true);
		m.put("ice", true);
		m.put("cream", true);
		m.put("icecream", true);
		m.put("man", true);
		m.put("go", true);
		m.put("mango", true);

	}

}
