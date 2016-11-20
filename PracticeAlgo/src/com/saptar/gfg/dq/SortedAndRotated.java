/**
 * A sorted array is rotated at some unknown point, find the minimum element in it.

	Following solution assumes that all elements are distinct.
	
	Examples
	
	Input: {5, 6, 1, 2, 3, 4}
	Output: 1
	
	Input: {1, 2, 3, 4}
	Output: 1
	
	Input: {2, 1}
	Output: 1
 */

package com.saptar.gfg.dq;

import java.util.Scanner;

public class SortedAndRotated {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int noOfTests = scan.nextInt();
		while (noOfTests > 0) {
			int noOfElements = scan.nextInt();
			int[] rotatedArray = new int[noOfElements];
			for (int idx = 0; idx < noOfElements; idx++) {
				rotatedArray[idx] = scan.nextInt();
			}
			noOfTests--;
			int ans = smallestNumber(rotatedArray, 0, noOfElements - 1);
			System.out.println("" + ans + "\n");
		}
		scan.close();

	}

	private static int smallestNumber(int[] arr, int start, int end) {
		// base cases
		if (start > end) {
			return arr[0];
		}
		if (start == end) {
			return arr[start];
		}
		// get the middle of the array
		int mid = start + (end - start) / 2;
		// check if the middle element is the smallest value
		// in the rotated array
		if (mid >= start && arr[mid] < arr[mid - 1]) {
			return arr[mid];
		}
		if (arr[mid] < arr[end]) {
			// choose to look to the left of the mid
			return smallestNumber(arr, start, mid - 1);
		} else {
			return smallestNumber(arr, mid + 1, end);
		}

	}

}

/**
 * The crux of the problem is to find out the condition where to look for right
 * or left half of the array. In this case it is to check if the mid is greater
 * than the last element as this is only possible if the array has been rotated
 * at a point after the mid. Code works for ascendingly sorted array.
 */
