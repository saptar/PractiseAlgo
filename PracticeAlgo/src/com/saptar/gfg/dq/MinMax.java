/**
 * Maximum and minimum of an array using minimum number of comparisons.
 */

package com.saptar.gfg.dq;

import java.util.Scanner;

public class MinMax {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests > 0) {
			int elements = scan.nextInt();
			int[] arr = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			int[] result = new int[2];
			// result[0] = getMin(arr, 0, elements - 1);
			// result[1] = getMax(arr, 0, elements - 1);
			result = getMinMax(arr, 0, elements - 1);
			System.out.println(result[0] + " " + result[1]);
			tests--;
		}
		scan.close();

	}

	private static int[] getMinMax(int[] arr, int start, int end) {
		int result[] = new int[2];
		if (start > end) {
			return null;
		}
		if (start == end) {
			result[0] = arr[start];
			result[1] = arr[end];
			return result;
		}
		if (end - start == 1) {
			if (arr[start] > arr[end]) {
				result[0] = arr[start];
				result[1] = arr[end];
			} else {
				result[0] = arr[end];
				result[1] = arr[start];
			}
			return result;
		}
		int mid = start + (end - start) / 2;
		int[] left_max_min = getMinMax(arr, 0, mid);
		int[] right_max_min = getMinMax(arr, mid + 1, end);
		if (left_max_min[0] > right_max_min[0]) {
			result[0] = left_max_min[0];
		} else {
			result[0] = right_max_min[0];
		}
		if (left_max_min[1] < right_max_min[1]) {
			result[1] = left_max_min[1];
		} else {
			result[1] = right_max_min[1];
		}

		return result;
	}

}
