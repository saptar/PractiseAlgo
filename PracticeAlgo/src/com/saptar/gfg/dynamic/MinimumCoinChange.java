/**
 * Minimum coin change
 * detailsimport java.util.Scanner;
.org/find-minimum-number-of-coins-that-make-a-change/
 */

package com.saptar.gfg.dynamic;

import java.util.Scanner;

public class MinimumCoinChange {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int elements = scan.nextInt();
			int value = scan.nextInt();
			int[] coins = new int[elements];
			int[][] dp = new int[elements][value + 1];
			for (int i = 0; i < elements; i++) {
				coins[i] = scan.nextInt();
			}
			int result = getMinimum(coins, elements - 1, value, dp);
			System.out.println((result > Integer.MAX_VALUE - 1000) ? -1
					: result);
		}
		scan.close();

	}

	private static int getMinimum(int[] arr, int k, int v, int[][] dp) {
		if (v == 0) {
			return 0;
		}
		if (v < 0) {
			return Integer.MAX_VALUE - 1;
		}
		if (k < 0 && v > 0) {
			// solution cannot be found
			return Integer.MAX_VALUE - 1;
		}
		if (arr[k] > v) {
			// proceed to the next coin
			return getMinimum(arr, k - 1, v, dp);

		}
		if (dp[k][v] != 0) {
			return dp[k][v];
		}
		int temp = Math.min(getMinimum(arr, k - 1, v, dp),
				1 + getMinimum(arr, k, v - arr[k], dp));
		dp[k][v] = temp;
		return dp[k][v];

	}

}
