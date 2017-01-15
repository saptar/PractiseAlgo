/**
 * Minimum number of jumps to reach end
 * Given an array of integers where each element represents the max number of 
 * steps that can be made forward from that element. 
 * Write a function to return the minimum number of jumps to reach the end of the array 
 * (starting from the first element). If an element is 0, then cannot move through that element.
 * Details: http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 */

package com.saptar.gfg.dynamic;

import java.util.Arrays;
import java.util.Scanner;

public class MinJumps {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int elements = scan.nextInt();
			int[] arr = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			int[] dp = new int[elements];
			Arrays.fill(dp, Integer.MAX_VALUE - 1);

			int result = minHops(arr, 0, elements - 1, dp);
			result = (result > elements) ? -1 : result;
			System.out.println(result);

		}
		scan.close();

	}

	private static int minHops(int[] arr, int start, int end, int[] dp) {
		// base cases
		if (start == end) {
			// end reached
			return 0;
		}
		if (start > end) {
			// cannot be the case
			return Integer.MAX_VALUE - 1;
		}
		if (arr[start] == 0) {
			// cannot go further, requires infnite hops

			return Integer.MAX_VALUE - 1;
		}
		if (dp[start] != Integer.MAX_VALUE - 1) {
			return dp[start];
		}
		int min_hops = Integer.MAX_VALUE - 1;
		for (int k = 1; k <= arr[start]; k++) {
			int hops = 1 + minHops(arr, start + k, end, dp);
			if (hops < min_hops) {
				min_hops = hops;
			}
		}
		dp[start] = min_hops;
		return dp[start];

	}
}
