/**
 * Given a sorted array in which all elements appear twice (one after one) 
 * and one element appears only once. Find that element in O(log n) complexity.

	Example:

	Input:   arr[] = {1, 1, 3, 3, 4, 5, 5, 7, 7, 8, 8}
	Output:  4
	
	Input:   arr[] = {1, 1, 3, 3, 4, 4, 5, 5, 7, 7, 8}
	Output:  8
 */




package com.saptar.gfg.dq;

public class SearchElement {
	
	static boolean flag = false;

	public static void main(String[] args) {
		int input[] = {1,1,2,2,3,4,4,5,5,6,6,7,7};

		
		System.out.println("The element with single occurence"
				+ "in the array\n"+"is :");
		search(input,0,input.length-1);

	}

	private static void search(int[] input, int i, int j) {
		if(j<i ){
			return;
		}
		if(i == j){
			System.out.print(input[i]);
		}
		int midIndex = (i+j)/2;
		
		if(midIndex%2 == 0){
			// look right from midpt
			if(input[midIndex] == input[midIndex+1]){
				search(input,midIndex+2,j);
			}
			else{
				search(input,i,midIndex-2);
			}
		}
		if(midIndex%2 != 0){
			//look left from midpt
			if(input[midIndex] == input[midIndex-1]){
				search(input,midIndex+2,j);
			}
			else{
				search(input,i,midIndex-2);
			}
		}
		
	}

}
