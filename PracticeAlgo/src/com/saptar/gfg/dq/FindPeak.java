/**
 * Given an array of integers. Find a peak element in it. An array element is peak if it is NOT smaller than its neighbors. For corner elements, we need to consider only one neighbor. For example, for input array {5, 10, 20, 15}, 20 is the only peak element. For input array {10, 20, 15, 2, 23, 90, 67}, there are two peak elements: 20 and 90. Note that we need to return any one peak element.

	Following corner cases give better idea about the problem.
	1) If input array is sorted in strictly increasing order, the last element is always a peak element. For example, 50 is peak element in {10, 20, 30, 40, 50}.
	2) If input array is sorted in strictly decreasing order, the first element is always a peak element. 100 is the peak element in {100, 80, 60, 50, 20}.
	3) If all elements of input array are same, every element is a peak element.
	
	For reference pls visit: http://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
 */

package com.saptar.gfg.dq;

import java.util.Arrays;
import java.util.Scanner;

public class FindPeak {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int noOfTests = scan.nextInt();
		while (noOfTests > 0) {
			int noOfElements = scan.nextInt();
			int[] arr = new int[noOfElements];
			for (int idx = 0; idx < noOfElements; idx++) {
				arr[idx] = scan.nextInt();
			}
			System.out.println("" + Arrays.toString(arr));
			noOfTests--;
			int result = findPeak(arr, 0, noOfElements - 1);
			System.out.println("Peak element is " + result);
		}
		scan.close();
	}

	private static int findPeak(int[] arr, int low, int high) {
		// base cases
		if (low > high) {
			// no peak found; all number are same
			return arr[low];
		}
		if (low == high) {
			return arr[high];
		}
		// mid element
		int mid = low + (high - low) / 2;
		// check if the mid element is the peak
		if (low >= 0 && arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
			return arr[mid];
		}
		// divide step
		if (low < mid && arr[mid] < arr[mid - 1]) {
			// there is a peak on the left side
			return findPeak(arr, low, mid - 1);
		} else {
			return findPeak(arr, mid + 1, high);
		}

	}

}
