/**
 * 0-1 Knapsack Problem
 * Given weights and values of n items, put these items in a knapsack of capacity W 
 * to get the maximum total value in the knapsack. In other words, 
 * given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated 
 * with n items respectively. Also given an integer W which represents knapsack capacity, 
 * find out the maximum value subset of val[] such that sum of the weights of this 
 * subset is smaller than or equal to W. 
 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
 */

package com.saptar.gfg.dynamic;

import java.util.Scanner;

public class Knapsack10 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int items = scan.nextInt();
			int cap = scan.nextInt();
			int[] wt = new int[items], val = new int[items];
			for (int i = 0; i < items; i++) {
				wt[i] = scan.nextInt();
			}
			for (int i = 0; i < items; i++) {
				val[i] = scan.nextInt();
			}
			int[][] dp = new int[cap + 1][items + 1];
			int result = ks(val, wt, cap, items, dp);
			System.out.println(result);
		}
		scan.close();

	}

	private static int ks(int[] wt, int[] val, int cap, int items, int[][] dp) {
		// base cases
		if (cap <= 0) {
			// we cannot take in more
			return 0;
		}
		if (items <= 0) {
			// all items have been covered
			return 0;
		}
		if (wt[items - 1] > cap) {
			// move to the next item
			return ks(wt, val, cap, items - 1, dp);
		}
		if (dp[cap][items] != 0) {
			return dp[cap][items];
		}
		dp[cap][items] = Math.max(ks(wt, val, cap, items - 1, dp),
				val[items - 1]
						+ ks(wt, val, cap - wt[items - 1], items - 1, dp));
		return dp[cap][items];
	}

}
