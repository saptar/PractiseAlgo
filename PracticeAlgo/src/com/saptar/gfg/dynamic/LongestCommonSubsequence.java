/**
 * Longest Common Subsequence
 * LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them. 
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous. 
 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”. 
 * So a string of length n has 2^n different possible subsequences.
 * Details: http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 */

package com.saptar.gfg.dynamic;

import java.util.Scanner;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int elements_str1 = scan.nextInt();
			int elements_str2 = scan.nextInt();
			String str1 = scan.next();
			String str2 = scan.next();
			char[] str1_arr = str1.toCharArray();
			char[] str2_arr = str2.toCharArray();
			System.out.println(LCS(str1_arr, elements_str1, str2_arr,
					elements_str2) + "");
		}
		scan.close();

	}

	private static String LCS(char[] str1_arr, int elements_str1,
			char[] str2_arr, int elements_str2) {
		// create a 2D int array to hold the lenght of the CS
		int[][] table = new int[elements_str1 + 1][elements_str2 + 1];
		// fill the first row and firt colum with zero
		for (int i = 0; i <= elements_str2; i++) {
			table[0][i] = 0;
		}
		for (int i = 0; i <= elements_str1; i++) {
			table[i][0] = 0;
		}
		// loop thru the rows and colms and fill the table
		for (int i = 1; i <= elements_str1; i++) {
			for (int j = 1; j <= elements_str2; j++) {
				// if matches
				if (str1_arr[i - 1] == str2_arr[j - 1]) {
					table[i][j] = table[i - 1][j - 1] + 1;
				} else {
					table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
				}
			}
		}
		// result
		int result = 0;
		result = table[elements_str1][elements_str2];
		return result + "";
	}
}
