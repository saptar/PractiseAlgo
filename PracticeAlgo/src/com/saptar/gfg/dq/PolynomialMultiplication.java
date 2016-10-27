/**
 * Given two polynomials represented by two arrays, 
 * write a function that multiplies given two polynomials. 

	Example:
	
	Input:  A[] = {5, 0, 10, 6} 
	        B[] = {1, 2, 4} 
	Output: prod[] = {5, 10, 30, 26, 52, 24}
	
	The first input array represents "5 + 0x^1 + 10x^2 + 6x^3"
	The second array represents "1 + 2x^1 + 4x^2" 
	And Output is "5 + 10x^1 + 30x^2 + 26x^3 + 52x^4 + 24x^5"
	
	For a detailed input and output format pls refer
	http://www.practice.geeksforgeeks.org/problem-page.php?pid=574
 * 
 */



package com.saptar.gfg.dq;

import java.util.Arrays;

public class PolynomialMultiplication {

	public static void main(String[] args) {
		int noOfTestCases =Integer.parseInt(args[0]);
		while(noOfTestCases>0){
			int degOfM = Integer.parseInt(args[1]),degOfN = Integer.parseInt(args[2]);
			int[] coeffM = new int[degOfM],coeffN = new int[degOfN];
			int i,k=0,j=0;
			for(i=3;i<(degOfM+3);i++){
				coeffM[k] = Integer.parseInt(args[i]);
				k++;
			}
			for(j=i,k=0;j<(degOfN+i);j++){
				coeffN[k] = Integer.parseInt(args[j]);
				k++;
			}
			int[] output = polyMultiply(coeffM, coeffN, degOfM, degOfN);
			System.out.println("The output is: "+Arrays.toString(output));
			args = Arrays.copyOfRange(args, j-1, args.length);
			noOfTestCases--;
		}

	}

	private static int[] polyMultiply(int[] coeffM, int[] coeffN, int degOfM, int degOfN) {
		int[] output = new int[degOfM+degOfN-1];
		int count = 0;
		while(count < degOfM){
			for(int i=0;i<degOfN;i++){
				output[count+i] += coeffM[count]*coeffN[i];
			}
			count++;
		}
		return output;
	}

}
