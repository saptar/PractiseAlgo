package com.saptar.gfg.dynamic;

import java.util.Scanner;

public class OneOutOfThree {

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
			int result = findMinSum(arr, 0, elements - 1, dp);
			System.out.println(result);
		}
		scan.close();

	}

	private static int findMinSum(int[] arr, int start, int end, int[] dp) {
		if (arr.length == 1) {
			return arr[0];
		}
		if (arr.length == 2) {
			return (arr[0] < arr[1]) ? arr[1] : arr[0];
		}
		if (end - start < 2) {
			return 0;
		}
		if (end - start == 2) {
			// return the min between three value
			return findMinIn3(arr[start], arr[start + 1], arr[end]);
		}
		if (dp[start] != 0) {
			return dp[start];
		}
		int select1 = arr[start] + findMinSum(arr, start + 1, end, dp);
		int select2 = arr[start + 1] + findMinSum(arr, start + 2, end, dp);
		int select3 = arr[start + 2] + findMinSum(arr, start + 3, end, dp);
		dp[start] = findMinIn3(select1, select2, select3);
		return dp[start];
	}

	private static int findMinIn3(int i, int j, int k) {
		int temp = (i < j) ? i : j;
		int result = (temp < k) ? temp : k;
		return result;
	}

}
