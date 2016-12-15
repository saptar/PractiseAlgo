/**
 * Check for Majority Element in a sorted array
 * Question: Write a C function to find if a given integer x appears more than n/2 times in a sorted array of n integers. 

 * Basically, we need to write a function say isMajority() that takes an array (arr[] ),
 * array’s size (n) and a number to be searched (x) as parameters
 * and returns true if x is a majority element (present more than n/2 times).
 * For details pls visit: http://www.geeksforgeeks.org/check-for-majority-element-in-a-sorted-array/
 */

package com.saptar.gfg.dq;

import java.util.Scanner;

public class MajorityElement {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests > 0) {
			int elements = scan.nextInt();
			int x = scan.nextInt();
			int[] arr = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			Boolean result = isMajorityElement(arr, elements, x);
			System.out.println((result) ? "True" : "False");
			tests--;
		}
		scan.close();

	}

	private static Boolean isMajorityElement(int[] arr, int elements, int x) {
		if (elements == 1 && arr[elements - 1] == x) {
			return true;
		}
		// use binary search to find the first occurence of x
		// return the index
		int first_occurence = findOccurence(arr, 0, elements - 1, x);
		int last_occurent = findNonOccurence(arr, first_occurence,
				elements - 1, x);
		if ((last_occurent - first_occurence) >= (elements - 1) / 2) {
			return true;
		} else {
			return false;
		}
	}

	private static int findNonOccurence(int[] arr, int first_occurence,
			int end, int x) {
		if (first_occurence > end || first_occurence < 0) {
			return -1;
		}
		if (first_occurence == end) {
			if (arr[first_occurence] != x) {
				return first_occurence - 1;
			} else {
				return arr.length - 1;
			}
		}
		// get the mid point
		int mid = first_occurence + (end - first_occurence) / 2;
		if (arr[mid] == x) {
			// since its a sorted array and arr[first_occurence] is x
			// everything in between must be x.
			// so look right
			return findNonOccurence(arr, mid + 1, end, x);
		} else {
			// look left
			return findNonOccurence(arr, first_occurence, mid, x);
		}
	}

	private static int findOccurence(int[] arr, int start, int end, int x) {
		// base case
		if (start > end) {
			return -1;
		}
		if (start == end) {
			if (arr[start] == x) {
				return start;
			} else {
				return -1;
			}
		}
		// mid point
		int mid = start + (end - start) / 2;
		if (arr[mid] >= x) {
			// look left
			return findOccurence(arr, start, mid, x);
		} else {
			// look left
			return findOccurence(arr, mid + 1, end, x);
		}

	}
}
