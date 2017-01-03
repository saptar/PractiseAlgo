/**
 * Min Cost Path
 * Given a cost matrix cost[][] and a position (m, n) in cost[][], 
 * write a function that returns cost of minimum cost path to reach (m, n) from (0, 0). 
 * Each cell of the matrix represents a cost to traverse through that cell. 
 * Total cost of a path to reach (m, n) is sum of all the costs on that path 
 * (including both source and destination). You can only traverse down, 
 * right and diagonally lower cells from a given cell, 
 * i.e., from a given cell (i, j), cells (i+1, j), (i, j+1) and (i+1, j+1) can be traversed. 
 * You may assume that all costs are positive integers.
 * 
 * details: http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
 */

package com.saptar.gfg.dynamic;

public class MinCostPath {

	public static void main(String[] args) {
		int[][] cost = { { 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 } };
		int result = minCost(cost, 2, 2);
		int result_dynamic = minCostDP(cost, 2, 2);
		System.out.println(result + " " + result_dynamic);

	}

	private static int minCostDP(int[][] cost, int m, int n) {
		// in DP we tabulate
		int[][] dp = new int[m + 1][n + 1];
		// while calclating we would need values from the upper and the left
		// edges which can be easily derived
		dp[0][0] = cost[0][0];
		// get the value of the upper edge
		for (int i = 1; i < n + 1; i++) {
			dp[0][i] = cost[0][i] + dp[0][i - 1];
		}
		for (int i = 1; i < m + 1; i++) {
			dp[i][0] = cost[i][0] + dp[i - 1][0];
		}
		// for the rest of the elements
		for (int i = 1; i < m + 1; i++) {
			// each row
			for (int j = 1; j < n + 1; j++) {
				// each column
				dp[i][j] = cost[i][j]
						+ Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]),
								dp[i - 1][j - 1]);
			}
		}
		return dp[m][n];
	}

	private static int minCost(int[][] cost, int m, int n) {
		if (m < 0 || n < 0) {
			return Integer.MAX_VALUE - 1;
		}
		if (m == 0 && n == 0) {
			return cost[m][n];
		} else {
			int min1 = Math.min(minCost(cost, m - 1, n),
					minCost(cost, m, n - 1));
			return (Math.min(min1, minCost(cost, m - 1, n - 1)) + cost[m][n]);
		}
	}
}
