package com.saptar.gfg.dynamic;

import java.util.Scanner;

public class LongestCommonSubstring {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int element1 = scan.nextInt();
			int element2 = scan.nextInt();
			String str1 = scan.next();
			String str2 = scan.next();
			char[] str1_arr = new char[element1];
			char[] str2_arr = new char[element2];
			str1_arr = str1.toCharArray();
			str2_arr = str2.toCharArray();
			int[][] LCStr = new int[element1 + 1][element2 + 1];
			int result = findLongestCommonStr(str1_arr, str2_arr, LCStr);
			System.out.println(result);
		}
		scan.close();

	}

	private static int findLongestCommonStr(char[] arr1, char[] arr2, int[][] dp) {
		// initialise the first row and colm of dp array to 0
		boolean[][] match = new boolean[dp.length][dp[0].length];
		match[0][0] = true;
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		for (int i = 0; i < dp[0].length; i++) {
			dp[0][i] = 0;
		}
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				if (arr1[i - 1] == arr2[j - 1]) {
					if (match[i - 1][j - 1]) {
						dp[i][j] = 1 + dp[i - 1][j - 1];
					} else {
						dp[i][j] = 1;
					}
					match[i][j] = true;
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		return dp[arr1.length][arr2.length];
	}

}
