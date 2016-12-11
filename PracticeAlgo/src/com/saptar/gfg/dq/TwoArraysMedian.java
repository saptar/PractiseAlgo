/**
 * There are 2 sorted arrays A and B of size n each. 
 * Write an algorithm to find the median of the array obtained after merging the above 2 
 * arrays(i.e. array of length 2n). The complexity should be O(log(n))
 * check https://www.youtube.com/watch?v=MHNTl_NvOj0 for details
 */

package com.saptar.gfg.dq;

public class TwoArraysMedian {

	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3, 4, 7, 9, 11, 13, 15, 17, 19 };
		int[] arr2 = { 0, 5, 6, 12 };
		int result = findMedian(arr1, arr2);
		System.out.println("The median is " + result);

	}

	/**
	 * 
	 * @param arr1
	 * @param arr2
	 * @return int
	 */
	private static int findMedian(int[] arr1, int[] arr2) {
		// get the median value of arr1 and arr2
		int med_index_arr1 = -1, med_index_arr2 = -1;
		med_index_arr1 = getMedianIndex(arr1);
		med_index_arr2 = getMedianIndex(arr2);
		if (arr1[med_index_arr1] == arr2[med_index_arr2]) {
			return arr1[med_index_arr1];
		}
		int result = getMedian(arr1, arr2, 0, arr1.length - 1, 0,
				arr2.length - 1);
		if (result < 0) {
			result = getMedian(arr2, arr1, 0, arr2.length - 1, 0,
					arr1.length - 1);
		}
		return result;
	}

	private static int getMedian(int[] arr1, int[] arr2, int sa1, int se1,
			int sa2, int se2) {
		// handle error case
		Boolean flag = false;
		int mid_ele_arr2 = sa2 + (se2 - sa2) / 2;
		int med_arr2 = -1;
		if (mid_ele_arr2 <= 0) {
			med_arr2 = arr2[0];
			flag = true;
		} else if (mid_ele_arr2 >= arr2.length - 1) {
			med_arr2 = arr2[arr2.length - 1];
			flag = true;
		} else {
			med_arr2 = arr2[mid_ele_arr2];
		}
		// base cases
		if (sa1 > se1 || med_arr2 < 0) {
			// not doing negative elements in array
			return -1;
		}
		// get the median element
		int mid = sa1 + (se1 - sa1) / 2;
		int med_ele = arr1[mid]; // arrays are pre-sorted
		// check if the median condition is satisfied wrt arr2
		if (flag) {
			if (mid_ele_arr2 == 0 && med_arr2 <= med_ele) {
				// return the med_element
				return med_ele;
			} else if (mid_ele_arr2 >= arr2.length - 1 && med_arr2 > med_ele
					&& se1 == sa1) {
				// return the med_element
				return med_ele;
			} else {
				int mid_ele2 = -1;
				mid_ele2 = mid_ele_arr2;
				if (med_arr2 > med_ele) {
					// the median must lie right of the med_ele
					return getMedian(arr1, arr2, mid + 1, se1, 0, mid_ele2 - 1);
				} else {
					return getMedian(arr1, arr2, sa1, mid - 1, mid_ele2 + 1,
							se2);
				}
			}
		} else {
			if (med_arr2 <= med_ele && med_ele > arr2[mid_ele_arr2 + 1]) {
				// return the med_element
				return med_ele;
			} else {
				int mid_ele2 = -1;
				mid_ele2 = mid_ele_arr2;
				if (med_arr2 > med_ele) {
					// the median must lie right of the med_ele
					return getMedian(arr1, arr2, mid + 1, se1, 0, mid_ele2 - 1);
				} else {
					return getMedian(arr1, arr2, sa1, mid - 1, mid_ele2 + 1,
							se2);
				}
			}
		}

	}

	private static int getMedianIndex(int[] arr) {
		return (int) Math.floor((arr.length) / 2);
	}

}
