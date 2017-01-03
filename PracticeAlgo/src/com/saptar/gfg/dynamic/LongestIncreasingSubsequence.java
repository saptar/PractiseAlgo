/**
 * Longest Increasing Subsequence
 * Details: http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 */

package com.saptar.gfg.dynamic;

import java.util.Scanner;

public class LongestIncreasingSubsequence {

	private static int[] Table;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int elements = scan.nextInt();
			Table = new int[elements];
			int[] arr = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			getLIS(arr, elements);
			Table = null;

		}
		scan.close();

	}

	private static void getLIS(int[] arr, int elements) {
		// initialise an array to hold the max LIS value for
		// that element
		int[] LISS = new int[elements];
		int k = 0;
		for (int i = 0; i < elements; i++) {
			LISS[i] = 1;
		}
		if (elements == 1) {
			return;
		}
		Table[0] = 1;
		while (k < elements) {
			getLISForEachElement(arr, arr[k], k, LISS, k - 1);
			k = k + 1;
		}
		int result = 0;
		for (int j = 0; j < LISS.length; j++) {
			if (result < LISS[j]) {
				result = LISS[j];
			}
		}
		System.out.println(result);

	}

	private static int getLISForEachElement(int[] arr, int element, int idx,
			int[] LISS, int p) {
		if (Table[idx] != 0) {
			return Table[idx];
		}
		while (p >= 0) {
			if (arr[p] >= element) {
				// cannot to move to next step
				p--;
			} else {
				LISS[idx] = Math.max(LISS[idx],
						1 + getLISForEachElement(arr, arr[p], p, LISS, p - 1));
				Table[idx] = LISS[idx];
				p--;
			}
		}
		return LISS[idx];

	}
}
