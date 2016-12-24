/**
 * Bin Packing Problem (Minimize number of used Bins)
 * 
 * Given n items of different weights and bins each of capacity c, assign each item to a bin such that number of total used bins is minimized. 
 * It may be assumed that all items have weights smaller than bin capacity.
 * Details: http://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/
 */

package com.saptar.gfg.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class BinPacking {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int elements = scan.nextInt();
			int arr[] = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			int capacity = scan.nextInt();
			int result = noOfBins(arr, elements, capacity);
			System.out.println(result);
		}
		scan.close();
	}

	private static int noOfBins(int[] arr, int elements, int capacity) {
		int bins = 0;
		int[] bin_store = new int[elements];
		for (int i = 0; i < elements; i++) {
			bin_store[i] = capacity;
		}
		if (elements == 1 && arr[0] > capacity) {
			return -1;
		}
		// sort the array
		Arrays.sort(arr);
		for (int i = elements - 1; i >= 0; i--) {
			for (int j = 0; j < elements; j++) {
				if (arr[i] < bin_store[j]) {
					bin_store[j] -= arr[i];
					break;
				}
			}
		}
		// get the first index of 8 in the bin_Store array
		for (bins = 0; bins < elements; bins++) {
			if (bin_store[bins] == capacity) {
				break;
			}
		}

		return bins;
	}
}
