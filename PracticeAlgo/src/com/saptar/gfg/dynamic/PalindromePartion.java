/**
 * Palindrome partiotioning
 * details: http://www.geeksforgeeks.org/dynamic-programming-set-17-palindrome-partitioning/
 */

package com.saptar.gfg.dynamic;

import java.util.Arrays;
import java.util.Scanner;

public class PalindromePartion {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			String str = scan.next();
			int result = findMinCut(str);
			System.out.println(result);
		}
		scan.close();
	}

	private static int findMinCut(String str) {
		int length = str.length();
		char[] arr = str.toCharArray();
		// create arrays to hold the cut value and is palindrome
		int[][] cut = new int[length][length];
		boolean[][] isPalindrome = new boolean[length][length];
		// each element is a palindrome in itself.
		for (int i = 0; i < length; i++) {
			isPalindrome[i][i] = true;
			cut[i][i] = 0;
		}
		// find the cuts required for every string starting at length 2
		for (int L = 2; L <= length; L++) {
			// for substring of size 2 set diff possible starting indexes.
			for (int i = 0; i < length - L + 1; i++) {
				// ending index
				int end_index = i + L - 1;
				// if the lenght is two check the two elements
				if (L == 2) {
					isPalindrome[i][end_index] = (arr[i] == arr[end_index]);
				} else {
					isPalindrome[i][end_index] = (arr[i] == arr[end_index])
							&& (arr[i + 1] == arr[end_index - 1]);
				}
				if (isPalindrome[i][end_index]) {
					cut[i][end_index] = 0;
				} else {
					// make cuts at every possible location starting from i to
					// end_index
					// get the minimum cost
					cut[i][end_index] = Integer.MAX_VALUE - 1;
					for (int k = 0; k <= end_index - 1; k++) {
						cut[i][end_index] = Math.min(cut[i][end_index], 1
								+ cut[i][k] + cut[k + 1][end_index]);
					}
				}
			}
		}
		System.out.println(Arrays.toString(cut[0]));
		return cut[0][length - 1];
	}

}
