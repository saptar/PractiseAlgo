/**
 * Find a Fixed Point in a given array
 * Given an array of n distinct integers sorted in ascending order, write a function that returns 
 * a Fixed Point in the array, 
 * if there is any Fixed Point present in array, else returns -1. Fixed Point in an array is an index i 
 * such that arr[i] is equal to i. Note that integers in array can be negative.
 * 
 * for details pls refer to : http://www.geeksforgeeks.org/find-a-fixed-point-in-a-given-array/
 */

package com.saptar.gfg.dq;

import java.util.Scanner;

public class FixedPoint {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests > 0) {
			int elements = scan.nextInt();
			int[] arr = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			int result = findFixedPoint(arr, 0, elements - 1);
			System.out.println(result);

		}
		scan.close();
	}

	private static int findFixedPoint(int[] arr, int start, int end) {
		// base cases
		if (start > end) {
			return -1;
		}
		// find mid
		int mid = start + (end - start) / 2;
		if (mid == arr[mid]) {
			return arr[mid];
		}
		if (mid < arr[mid]) {
			// look left
			return findFixedPoint(arr, start, mid - 1);
		} else {
			return findFixedPoint(arr, mid + 1, end);
		}
	}
}
