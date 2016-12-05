/**
 * You are given a one dimensional array that may 
 * contain both positive and negative integers, 
 * find the sum of contiguous subarray of numbers which has the largest sum.

	For example, if the given array is {-2, -5, 6, -2, -3, 1, 5, -6}, 
	then the maximum subarray sum is 7 (see highlighted elements).
	For details refer to: http://www.geeksforgeeks.org/divide-and-conquer-maximum-sum-subarray/
 */

package com.saptar.gfg.dq;

import java.util.Scanner;

public class LargestSumSubArray {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int noOfTestCases = scan.nextInt();
		while (noOfTestCases > 0) {
			int arraySize = scan.nextInt();
			int arr[] = new int[arraySize];
			for (int i = 0; i < arraySize; i++) {
				arr[i] = scan.nextInt();
			}
			noOfTestCases--;
			int result = getSum(arr, 0, arraySize - 1);
			System.out.println("The largest sum of contiguous subarray is "
					+ result);
			scan.close();
		}

	}

	private static int getSum(int[] arr, int start, int end) {
		// base case
		if (start > end) {
			return Integer.MIN_VALUE;
		}
		if (start == end) {
			return arr[start];
		}

		int mid = start + (end - start) / 2;
		// case 1: check the left subarray
		int max_left = getSum(arr, start, mid);
		// case 2: check right subarray
		int max_right = getSum(arr, mid + 1, end);
		// case 3: check the add of mid to left subarray and mid+1 to right sub
		// array
		int max_mid_left = getMaxSum(arr, start, mid, "left");
		int max_mid_right = getMaxSum(arr, mid + 1, end, "right");
		int max_mid = max_mid_left + max_mid_right;
		return Math.max(Math.max(max_right, max_left), max_mid);

	}

	private static int getMaxSum(int[] arr, int start, int end, String subArr) {
		if (subArr.equalsIgnoreCase("left")) {
			int temp_max = (Integer.MIN_VALUE + 1) / 2, currentSum = arr[end];
			// calculate for mid to left sub array
			while (end > start) {
				end--;
				currentSum += arr[end];
				if (currentSum > temp_max) {
					temp_max = currentSum;
				}
			}
			return temp_max;
		} else if (subArr.equalsIgnoreCase("right")) {
			int temp_max = (Integer.MIN_VALUE + 1) / 2, currentSum = arr[start];
			// calculate for mid to left sub array
			while (start < end) {
				start++;
				currentSum += arr[start];
				if (currentSum > temp_max) {
					temp_max = currentSum;
				}
			}
			return temp_max;
		}
		return Integer.MIN_VALUE + 1;
	}

}
