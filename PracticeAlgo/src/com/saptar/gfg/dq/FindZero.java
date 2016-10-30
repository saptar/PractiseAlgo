/**
 * Given an array of 1s and 0s which has all 1s first followed by all 0s. Find the number of 0s. Count the number of zeroes in the given array.

	Examples:
	
	Input: arr[] = {1, 1, 1, 1, 0, 0}
	Output: 2
	
	Input: arr[] = {1, 0, 0, 0, 0}
	Output: 4
	
	Input: arr[] = {0, 0, 0}
	Output: 3
	
	Input: arr[] = {1, 1, 1, 1}
	Output: 0
	
	refer : http://www.practice.geeksforgeeks.org/problem-page.php?pid=897
	for detailed question.
 */



package com.saptar.gfg.dq;

import java.util.Arrays;
import java.util.Scanner;

public class FindZero {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int testCases = scan.nextInt();
		while(testCases>0){
			int arrLength = scan.nextInt();
			int arr[]=new int[arrLength];
			for(int i=0;i<arrLength;i++){
				arr[i]=scan.nextInt();
			}
			testCases--;
			int firstZero = findZero(arr,0,arr.length-1);
			System.out.println("No of zeros in the array"
					+ " "+ Arrays.toString(arr)+" is:"+ 
					((firstZero>=0)?(arr.length - firstZero):0));
		}
		scan.close();
		

	}

	private static int findZero(int[] arr, int start, int end) {
		int zeroes = -1;
		// find the mid point
		int mid = start + (end-start)/2;
		// base cases
		if(start > end){
			// no zeroes found
			return zeroes;
		}
		if(arr[mid] == 0 && mid == 0){
			// all zeroes encountered
			return mid;
		}
		// recursive breakdown
		if(arr[mid] == 0 && arr[mid-1] == 1){
			// this is the first occurence of 0
			return mid;
		}
		else if(arr[mid] == 0 && arr[mid-1] != 1){
			// look left
			return findZero(arr, start, mid-1);
		}
		else {
			// look right
			return findZero(arr, mid+1, end);
		}
	}

}
