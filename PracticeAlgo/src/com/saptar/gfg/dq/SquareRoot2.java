/**
 * Given an integer x, find square root of it. 
 * If x is not a perfect square, then return floor(√x).

	Examples:

	Input: x = 4
	Output: 2
	
	Input: x = 11
	Output: 3
 */



package com.saptar.gfg.dq;

public class SquareRoot2 {
	public static long floorSqrRoot(int input){
		// base case 
		if(input == 0 || input == 1){
			return input;
		}
		long start = 1, end = input/2;
		long result = input;
		while(start<=end){
			long mid = (start+end)/2;
			if(mid * mid == input){
				result = mid;
				return result;
			}
			if(mid * mid > input){
				end = mid - 1;
			}
			else{
				start = mid + 1;
				result  = mid;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int input = 111;
		long output = 0;
		output = floorSqrRoot(input);
		System.out.println("the square root of "+input+" is "+output);

	}

}
