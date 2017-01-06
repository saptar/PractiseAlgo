/**
 * Cutting a Rod
 * details: http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 */

package com.saptar.gfg.dynamic;

public class RodCutting {

	public static void main(String[] args) {
		int[] cutPrice = { 1, 5, 8, 9, 10, 17, 17, 20, 21, 22, 23, 45, 46, 47,
				48 };
		int rodLength = cutPrice.length;
		int[][] dp = new int[rodLength + 1][rodLength + 1];
		long startTime = System.nanoTime();
		int result = maxPrice(cutPrice, cutPrice.length, rodLength, dp);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime) / 100000;

		System.out.println(result + " calculated in " + duration + " ms");

	}

	private static int maxPrice(int[] price, int m, int sum, int[][] dp) {
		// base cases
		if (sum <= 0) {
			// nothing left to cut
			return 0;
		}
		if (sum < m && m >= 1) {
			dp[sum][m - 1] = maxPrice(price, m - 1, sum, dp);
			return dp[sum][m - 1];
		}
		if (m <= 0) {
			// no more cut length to consider
			return 0;
		}
		if (dp[sum][m] != 0) {
			return dp[sum][m];
		}
		// consider cut of length m and cut of length without m
		// find the max price and return
		dp[sum][m] = Math.max(price[m - 1] + maxPrice(price, m, sum - m, dp),
				maxPrice(price, m - 1, sum, dp));
		return dp[sum][m];

	}
}
