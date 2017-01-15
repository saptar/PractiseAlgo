/**
 * Egg Dropping problem
 * details: http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
 */

package com.saptar.gfg.dynamic;

import java.util.Scanner;

public class EggDropping {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int eggs = scan.nextInt();
			int floors = scan.nextInt();
			int[][] dp = new int[eggs + 1][floors + 1];
			int result = minTrials(eggs, floors, dp);

			System.out.println(result);
		}
		scan.close();

	}

	private static int minTrials(int eggs, int floors, int[][] dp) {
		// base cases
		if (floors == 1 || floors == 0) {
			return floors;
		}
		if (eggs == 1) {
			return floors;
		}
		if (dp[eggs][floors] != 0) {
			return dp[eggs][floors];
		}
		int min = Integer.MAX_VALUE - 1;
		for (int k = 1; k <= floors; k++) {
			int temp = 1 + Math.max(minTrials(eggs - 1, k - 1, dp),
					minTrials(eggs, floors - k, dp));
			if (temp < min) {
				min = temp;
			}
		}
		dp[eggs][floors] = min;
		return dp[eggs][floors];

	}

}
