package com.saptar.gfg.dq;

import java.util.Arrays;
import java.util.Scanner;

public class RectAreaHistorgram {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int noOfTestCases = scan.nextInt();
		while (noOfTestCases > 0) {
			int noOfBars = scan.nextInt();
			int[] hist = new int[noOfBars];
			for (int i = 0; i < noOfBars; i++) {
				hist[i] = scan.nextInt();
			}
			noOfTestCases--;
			int result = getMaxArea(hist);
			int result1 = getMaxArea(hist, 0, hist.length);
			System.out.println("Max area result " + result
					+ "\nMax area result1 " + result1);
		}
		scan.close();

	}

	/**
	 * 
	 * @param hist
	 * @return int
	 * @description: this is a naive method to calculate the max area in the
	 *               given histogram. The idea is to start with each bar and
	 *               calculate the area adding every other bar from the starting
	 *               point; while saving and updating the maximum calculated
	 *               area. This takes worst case runtime of O(n^2)
	 */
	private static int getMaxArea(int[] hist) {
		int noOfHist = hist.length;
		int result = -1;
		while (noOfHist > 0) {
			int barsToCompare = noOfHist - 1;
			int area = -1;
			int idx = 0;
			int getSize = Integer.MAX_VALUE - 1;
			while (barsToCompare > 0) {
				idx++;
				int getSizeNow = (hist[noOfHist - 1] > hist[barsToCompare - 1]) ? hist[barsToCompare - 1]
						: hist[noOfHist - 1];
				if (getSizeNow < getSize) {
					getSize = getSizeNow;
				}
				area = (area < (getSize * (1 + idx))) ? (getSize * (1 + idx))
						: area;
				barsToCompare--;

			}
			result = (result < area) ? area : result;
			noOfHist--;

		}
		return result;

	}

	/**
	 * @param: array
	 * @param: int
	 * @param: int
	 * @param: int
	 * @return: int
	 * @description: This method takes in array of historgram and the smallest
	 *               bar. And uses divide and conquer to find out the max area.
	 *               When we have the smallest bar , we can divide the
	 *               historgram into three pars, the left of the smallest bar
	 *               ,excluding the smallest bar, the right of the smallest bar,
	 *               excluding the smallest bar and the the smallest bar itself.
	 *               Now the max area value can come from one of the following
	 *               three i. The left area. ii. The right area or iii. The
	 *               smallest bar multiplied with the number of bars in the
	 *               historgram. We can solve the right and the left side using
	 *               recurrence.
	 */
	public static int getMaxArea(int[] hist, int start, int end) {
		// get the smallest element in the array
		if (hist.length < 1) {
			return -1;
		}
		if (start == end) {
			return hist[start];
		}
		int[] arrRight = {}, arrLeft = {};
		int smallest = getSmallest(hist);
		int index;
		for (index = 0; index < hist.length; index++) {
			if (hist[index] == smallest) {
				break;
			}
		}
		// divide the array into three parts
		if (index > 0) {
			arrLeft = Arrays.copyOfRange(hist, start, index);
		}
		if (index < end) {
			arrRight = Arrays.copyOfRange(hist, index + 1, end + 1);
		}

		// get the total area covered by rectangle with height as the smallest
		// bar
		int area = smallest * hist.length;
		return Math.max(area, Math.max(
				getMaxArea(arrLeft, 0, arrLeft.length - 1),
				getMaxArea(arrRight, 0, arrRight.length - 1)));
	}

	/**
	 * utility method to get the smallest number in an array
	 */
	private static int getSmallest(int arr[]) {
		int smallest = Integer.MAX_VALUE - 1;
		int arrLength = arr.length;
		while (arrLength > 0) {
			if (arr[arrLength - 1] < smallest) {
				smallest = arr[arrLength - 1];
			}
			arrLength--;
		}
		return smallest;
	}
}

/**
 * Here the divide and conquer rule does not give a perf enhancement as we need
 * linear time to find out the lowest bar. An alternative to this is using
 * segment trees.
 */
