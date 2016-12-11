/**
 * Find the maximum element in an array which is first increasing and then decreasing
 * for details pls refer: http://www.geeksforgeeks.org/find-the-maximum-element-in-an-array-which-is-first-increasing-and-then-decreasing/
 */

package com.saptar.gfg.dq;

import java.util.Scanner;

public class MaxElementIncreaseDecrease {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests > 0) {
			int elements = scan.nextInt();
			int[] arr = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			tests--;
			int result = findMaxElement(arr, 0, elements - 1);
			System.out.println(result);
		}
		scan.close();

	}

	private static int findMaxElement(int[] arr, int start, int end) {
		// base cases
		if (start > end) {
			return -1;
		}
		if (start == end) {
			// no dip found; return the last max element at the end
			return arr[start];
		}

		int mid = start + (end - start) / 2;
		if (mid > 0 && mid < arr.length - 1 && arr[mid] > arr[mid - 1]
				&& arr[mid] > arr[mid + 1]) {
			// we have got the peak
			return arr[mid];
		}
		// divide and select which half to proceed
		if (mid != 0 && arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
			// look right
			return findMaxElement(arr, mid + 1, end);
		} else {
			// look left
			return findMaxElement(arr, start, mid - 1);
		}
	}
}
