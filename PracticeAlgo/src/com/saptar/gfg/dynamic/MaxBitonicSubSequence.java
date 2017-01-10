/**
 * Max bitonic sequence
 * details: http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
 */

package com.saptar.gfg.dynamic;

import java.util.Scanner;

public class MaxBitonicSubSequence {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int elements = scan.nextInt();
			int[] arr = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			int result = maxBitonicSequence(arr, elements);
			System.out.println(result);
		}
		scan.close();

	}

	private static int maxBitonicSequence(int[] arr, int index) {
		int[] LIS = new int[index], LDS = new int[index];
		for (int i = 0; i < index; i++) {
			LIS[i] = 1;
			LDS[i] = 1;
		}
		if (index == 1) {
			return 1;
		}
		for (int i = 1; i < index; i++) {
			// consider the first item in the array
			for (int k = 0; k < i; k++) {
				if (arr[k] < arr[i] && LIS[i] < LIS[k] + 1) {
					LIS[i] = LIS[k] + 1;
				}
			}
		}
		for (int i = index - 2; i >= 0; i--) {
			// consider last but one item
			for (int k = index - 1; k > i; k--) {
				if (arr[k] < arr[i] && LDS[i] < LDS[k] + 1) {
					LDS[i] = LDS[k] + 1;
				}
			}
		}
		int max = LIS[0] + LDS[0] - 1;
		for (int i = 1; i < index; i++) {
			int temp = LDS[i] + LIS[i] - 1;
			if (temp > max) {
				max = temp;
			}
		}
		return max;

	}
}
