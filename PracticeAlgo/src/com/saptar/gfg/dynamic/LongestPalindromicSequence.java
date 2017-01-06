/**
 * Longest palindromic sequence
 * Given a sequence, find the length of the longest palindromic subsequence in it. 
 * For example, if the given sequence is “BBABCBCAB”, 
 * then the output should be 7 as “BABCBAB” is the longest palindromic subseuqnce in it. 
 * “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, but not the longest ones.
 * details: http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
 */

package com.saptar.gfg.dynamic;

import java.util.Scanner;

public class LongestPalindromicSequence {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			String phrase = scan.next();
			char[] arr = new char[phrase.length()];
			arr = phrase.toCharArray();
			int result = findLongestPalindrome(arr, 0, phrase.length() - 1);
			System.out.println(result);
		}
		scan.close();

	}

	private static int findLongestPalindrome(char[] a, int start, int end) {
		// base cases
		if (start > end) {
			return 0;
		}
		if (start == end) {
			return 1;
		}
		if (a[start] == a[end]) {
			return findLongestPalindrome(a, start + 1, end - 1) + 2;
		}
		return Math.max(findLongestPalindrome(a, start, end - 1),
				findLongestPalindrome(a, start + 1, end));
	}
}
