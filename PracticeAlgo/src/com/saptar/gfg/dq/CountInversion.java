/**
 * Count Inversions in an array
 * 
 * Inversion Count for an array indicates – how far (or close) the array is from being sorted. If array is already sorted then inversion count is 0. If array is sorted in reverse order that inversion count is the maximum. 
   Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j

  Example:
  The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
 * 
 * For more details: http://www.geeksforgeeks.org/counting-inversions/
 */

package com.saptar.gfg.dq;

import java.util.Scanner;

public class CountInversion {

	public static int result = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests > 0) {
			int elements = scan.nextInt();
			int[] arr = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			result = findCountInversion(arr, 0, elements - 1);
			System.out.println(result);
			tests--;

		}
		scan.close();

	}

	private static int findCountInversion(int[] arr, int start, int end) {
		if (start > end) {
			return -1;
		}
		int[] arr1 = findCountInversionHalves(arr, start, end);

		return result;
	}

	private static int[] findCountInversionHalves(int[] arr, int start, int end) {
		int mergeArr[] = new int[end - start + 1];
		// base case
		if (start > end) {
			return null;
		}
		if (start == end) {
			mergeArr[0] = arr[start];
			return mergeArr;
		}
		// mid point
		int mid = start + (end - start) / 2;
		return merge(findCountInversionHalves(arr, start, mid),
				findCountInversionHalves(arr, mid + 1, end));
	}

	private static int[] merge(int[] arr1, int[] arr2) {
		int arrLength = arr1.length + arr2.length, i = 0;
		String limitReached = "";
		int returnArr[] = new int[arrLength];
		int idx1 = 0, idx2 = 0;
		if (arr1.length == 0) {
			return arr2;
		}
		if (arr2.length == 0) {
			return arr1;
		}
		for (i = 0; i < arrLength; i++) {
			if (arr1[idx1] > arr2[idx2]) {
				result = result + arr1.length - idx1;
				returnArr[i] = arr2[idx2];
				idx2++;
				if (idx2 == arr2.length) {
					limitReached = "arr2";
					break;
				}
			} else {
				returnArr[i] = arr1[idx1];
				idx1++;
				if (idx1 == arr1.length) {
					limitReached = "arr1";
					break;
				}
			}
		}
		if (limitReached.equalsIgnoreCase("arr1") && idx2 < arr2.length) {
			// add rest of arr2 at the back of merge[]
			for (int idx = i + 1; idx < arrLength; idx++) {
				returnArr[idx] = arr2[idx2];
				idx2++;
			}
		} else if (limitReached.equalsIgnoreCase("arr2") && idx1 < arr1.length) {
			for (int idx = i + 1; idx < arrLength; idx++) {
				returnArr[idx] = arr1[idx1];
				idx1++;
			}
		}
		return returnArr;
	}
}
