/**
 * Minimize the maximum difference between the heights
 * Given heights of n towers and a value k. 
 * We need to either increase or decrease height of every tower by k (only once) where k > 0. 
 * The task is to minimize the difference between the heights of the longest 
 * and the shortest tower after modifications, and output this difference.
 * for details: http://www.geeksforgeeks.org/minimize-the-maximum-difference-between-the-heights/
 */

package com.saptar.gfg.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class MinimizeMaxDifference {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int elements = scan.nextInt();
			int[] arr = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			int offset = scan.nextInt();
			int result = findMin(arr, elements, offset);
			System.out.println(result);
		}
		scan.close();

	}

	private static int findMin(int[] arr, int arr_length, int offset) {
		// sort the array
		Arrays.sort(arr);
		int temp[] = new int[arr_length];
		System.out.println(Arrays.toString(arr));
		// base case
		// if offset is greater than or equal to the difference between
		// the max and the min in the array then simply do add/subtract
		// on all the array elements. eg {6,10} offset=10
		if (offset >= (arr[arr_length - 1] - arr[0])) {
			for (int i = 0; i < arr_length; i++) {
				temp[i] = arr[i] - offset;
			}
			return temp[arr_length - 1] - temp[0];
		}
		// add offset to min and subtract from max
		temp[0] = arr[0] + offset;
		temp[arr_length - 1] = arr[arr_length - 1] - offset;
		int pivot = Math.max(temp[0], temp[arr_length - 1]);
		// for the elements between the first and the last element of the array
		for (int i = 1; i < arr_length - 1; i++) {
			if (arr[i] < pivot) {
				// add the offset
				// if after addition the sum is greater than pivot, then
				// substract
				temp[i] = ((arr[i] + offset) > pivot) ? arr[i] - offset
						: arr[i] + offset;
			} else {
				temp[i] = arr[i] - offset;
			}
		}
		// sort the temp Array
		Arrays.sort(temp);
		System.out.println(Arrays.toString(temp));
		return temp[arr_length - 1] - temp[0];
	}
}

/**
 * Tested with the following input
 * 
 * 6 3 1 15 10 6 4 1 5 15 10 3 2 4 6 10 2 6 10 3 6 1 10 14 14 14 15 6 3 1 2 3 2
 * 
 * */
