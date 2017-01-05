/**
 * Binomial Coefficient
 * Following are common definition of Binomial Coefficients.
	1) A binomial coefficient C(n, k) can be defined as the coefficient of X^k in the expansion of (1 + X)^n.

	2) A binomial coefficient C(n, k) also gives the number of ways, disregarding order, 
	that k objects can be chosen from among n objects; more formally, the number of k-element subsets 
	(or k-combinations) of an n-element set.
	
	details: http://www.geeksforgeeks.org/dynamic-programming-set-9-binomial-coefficient/
	
	There is a optimal substructure for binomial coefficient
	c(n,k) = c(n-1,k-1)+c(n-1,k)
	and c(n,n)=c(n,0)=1
 */

package com.saptar.gfg.dynamic;

import java.util.Scanner;

public class BinomialCoefficient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int n = scan.nextInt();
			int k = scan.nextInt();
			int[][] dp = new int[n + 1][k + 1];
			int result = binomial(n, k, dp);
			System.out.println(result);
		}
		scan.close();

	}

	private static int binomial(int n, int k, int[][] dp) {
		if (k == n) {
			return 1;
		}
		if (k == 0) {
			return 1;
		}
		if (dp[n][k] != 0) {
			return dp[n][k];
		}
		dp[n][k] = binomial(n - 1, k - 1, dp) + binomial(n - 1, k, dp);
		return dp[n][k];
	}

}
