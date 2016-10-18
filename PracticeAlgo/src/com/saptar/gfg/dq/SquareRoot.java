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

public class SquareRoot {

	public static void main(String[] args) {
		// input
		int input = Integer.MAX_VALUE-2 ;
		long output = 0;
		output = findSqrRt(input);
		System.out.println("the square root of "+input+" is "+output);

	}

	private static long findSqrRt(int input) {
		long result = Integer.MAX_VALUE-1;
		long term = input;
		if(input == 0 || input == 1)
		{
			return input;
		}
		while(result >= input){
			result = (term/2)*(term/2);
			term = (int) Math.floor(term/2);
		}
		// now we get the limit where to find the sqr root
		// i.e term and term*2.
		for(long i= term; i<=term*2;i++){
			if(i*i>input){
				return i-1;
			}
			else if(i*i == input){
				return i;
			}
		}
		return 0;
	}

}
