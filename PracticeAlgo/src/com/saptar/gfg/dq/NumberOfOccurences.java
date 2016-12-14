/**
 * Count the number of occurrences in a sorted array
 * Given a sorted array arr[] and a number x, write a function that counts the occurrences of x in arr[]. 
 * Expected time complexity is O(Logn) 
 * for details pls visit: http://www.geeksforgeeks.org/count-number-of-occurrences-in-a-sorted-array/
 */

package com.saptar.gfg.dq;

import java.util.Scanner;

public class NumberOfOccurences {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests > 0) {
			int elements = scan.nextInt();
			int element = scan.nextInt();
			int[] arr = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			int result = getNumberOfOccurence(arr, 0, elements - 1, element);
			result = (result == 0) ? -1 : result;
			System.out.println(result);
			tests--;
		}
		scan.close();

	}

	private static int getNumberOfOccurence(int[] arr, int start, int end, int x) {
		// base case
		if (start > end) {
			return 0;
		}
		// get the mid
		int mid = start + (end - start) / 2;
		if (start == end && arr[mid] == x) {
			return 1;
		}
		if (arr[mid] == x && start != end) {
			return (1 + getNumberOfOccurence(arr, start, mid - 1, x) + getNumberOfOccurence(
					arr, mid + 1, end, x));

		}
		if (arr[mid] > x) {
			// look left
			return getNumberOfOccurence(arr, start, mid - 1, x);
		} else {
			return getNumberOfOccurence(arr, mid + 1, end, x);
		}

	}

}
