/**
 * Tricky subset problem
 * For details: http://www.practice.geeksforgeeks.org/problem-page.php?pid=1588
 */

package com.saptar.gfg.dynamic;

import java.util.Arrays;
import java.util.Scanner;

public class TrickySubset {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			long S = scan.nextInt();
			int elements = scan.nextInt();
			long match = scan.nextLong();
			long[] arr = new long[elements + 1];
			arr[0] = S;
			for (int i = 1; i <= elements; i++) {
				arr[i] = scan.nextLong();
			}
			long[] sum = new long[elements + 1];
			boolean canCal = getMatch(arr, elements, sum, match);
			System.out.println((canCal) ? "yes" : "no");
		}
		scan.close();
	}

	private static boolean getMatch(long[] arr, int end, long[] sum, long match) {
		sum[0] = 1;
		long[] dp = new long[end];
		for (int i = 1; i <= end; i++) {
			sum[i] = arr[i] + getAllSum(arr, i - 1, sum, dp);
		}
		// find the match can be constructed.
		int near_small = 0;
		for (int i = 0; i < sum.length; i++) {
			if (sum[i] == match) {
				return true;
			}
			if (sum[i] > match) {
				near_small = i;
				break;
			}
		}

		return canForm(Arrays.copyOf(sum, near_small), match);
	}

	private static boolean canForm(long[] arr, long match) {

		if (arr.length == 0) {
			return false;
		}
		long temp = match - arr[arr.length - 1];
		int near_small = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == temp) {
				return true;
			}
			if (arr[i] > temp) {
				near_small = i;
				break;
			}
		}
		return canForm(Arrays.copyOf(arr, near_small), temp);

	}

	private static long getAllSum(long[] arr, int idx, long[] sum, long[] dp) {
		// base case
		if (idx == 0) {
			return (long) sum[0];
		}
		if (dp[idx] != 0) {
			return dp[idx];
		}
		long temp = sum[idx] + getAllSum(arr, idx - 1, sum, dp);
		dp[idx] = temp;
		return dp[idx];

	}
}
