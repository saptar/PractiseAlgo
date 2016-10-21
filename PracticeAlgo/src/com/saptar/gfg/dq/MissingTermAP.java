/**
 * Given an array that represents elements of arithmetic progression in order.
 *  One element is missing in the progression, find the missing number.

	Examples:
	
	Input: arr[]  = {2, 4, 8, 10, 12, 14}
	Output: 6
	
	Input: arr[]  = {1, 6, 11, 16, 21, 31};
	Output: 26
 */


package com.saptar.gfg.dq;

public class MissingTermAP {

	private static int d;

	public static void main(String[] args) {
		int arr[] = {1, 6, 11, 16, 21, 31};
		System.out.println("The missing term is "+missingAPTerm(arr));
		
	}

	private static int missingAPTerm(int[] arr) {
		
		d = findDifference(arr);
		int res = findTerm(arr,0,arr.length-1);
		return res;
	}

	private static int findTerm(int[] arr, int i, int j) {
		// find the mid
		int mid = (i + j)/2;
		int midValue = arr[mid];
		// base case
		if(arr[mid+1] - midValue != d){
			// the missing element is between mid and mid+1;
			return midValue+d;
		}
		// calculate the n/2th term for the series
		int nby2th = arr[i]+((j-i)/2)*d;
		if(midValue > nby2th){
			// look for the anomali in the left
			return findTerm(arr, i, mid-1);
		}
		else{
			return findTerm(arr, mid+1, j);
		}
	}

	private static int findDifference(int[] arr) {
		int res = (arr[arr.length -1] - arr[0])/(arr.length - 1+1);
		// adding +1 to the denominator because missing term reduces the 
		// total length of the sum with given 1st and last term by one.
		return res;
	}

}
