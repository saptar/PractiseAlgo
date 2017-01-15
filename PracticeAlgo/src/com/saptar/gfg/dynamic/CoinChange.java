/**
 * coin change
 * Given a value N, if we want to make change for N cents, 
 * and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, 
 * how many ways can we make the change? The order of coins doesn’t matter.

   For example, for N = 4 and S = {1,2,3}, 
   there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. 
   For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. 
   So the output should be 5.
 */

package com.saptar.gfg.dynamic;

public class CoinChange {

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3 };
		int amt = 4;
		int[][] map = new int[arr.length + 1][amt + 1];
		long startTime = System.nanoTime();
		int result = cc(arr, arr.length, amt, map);
		// int result = coinChangeRecursive(arr, 0, amt);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime) / 100000;

		long startTime1 = System.nanoTime();
		int result1 = ccDP(arr, amt);
		long endTime1 = System.nanoTime();

		long duration1 = (endTime1 - startTime1) / 100000;
		// map<left sum, left coins>//
		System.out.println(result1 + " " + result + " duration " + " "
				+ duration1 + " " + duration);

	}

	private static int ccDP(int[] arr, int amt) {
		int[][] dp = new int[arr.length + 1][amt + 1];
		// base case when amt is 0
		// only one possible combination of coins i.e no coints
		for (int i = 0; i < arr.length; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= amt; j++) {
				if (arr[i - 1] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - arr[i - 1]];
				}

			}
		}
		return dp[arr.length][amt];
	}

	private static int cc(int[] S, int m, int n, int[][] dp) {

		// top down method is basically a selection problem
		// so select the mth coin and reduce the total sum or
		// ignore the mth element and keep the total same.
		// recurse.

		if (n == 0)
			return 1;

		// If n is less than 0 then no solution exists
		if (n < 0)
			return 0;

		// If there are no coins and n is greater than 0, then no solution exist
		if (m <= 0 && n >= 1)
			return 0;

		// count is sum of solutions (i) including S[m-1] (ii) excluding S[m-1]
		if (dp[m][n] != 0) {
			return dp[m][n];
		}

		dp[m][n] = cc(S, m - 1, n, dp) + cc(S, m, n - S[m - 1], dp);
		return dp[m][n];
	}

	// another recursive approach much like the previous one.
	private static int coinChangeRecursive(int[] coins, int start, int amount) {
		if (coins.length == 1 && coins[0] == 0) {
			// we cannot form the sum
			return 0;
		}
		if (amount == 0) {
			return 1;
		}
		if (amount < 0) {

			return 0;
		}
		int result1 = 0;
		// loop through the set of coins and choise the ith coin
		for (int i = 0; i < coins.length - start; i++) {
			result1 += coinChangeRecursive(coins, start + i, amount
					- coins[start + i]);
		}
		return result1;
	}
}
