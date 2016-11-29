/**
 * Divide and Conquer | Set 4 (Karatsuba algorithm for fast multiplication)
 * 
 * for details pls visit:
 * http://www.geeksforgeeks.org/divide-and-conquer-set-2-karatsuba-algorithm-for-fast-multiplication/
 */

package com.saptar.gfg.dq;

import java.util.Scanner;

public class KaratsubaAlgo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long a = scan.nextLong();
		long b = scan.nextLong();
		long result = multiply(a, b);
		System.out.println("The result of multiplication " + result);
		scan.close();

	}

	private static long multiply(long a, long b) {
		// get the length of a and b
		int lengthA = getLength(a);
		int lenghtB = getLength(b);
		// get the max of the lenght of the number
		int N = Math.max(lengthA, lenghtB);
		// if the lenght is less than 10
		// do a simple multiplication
		// base case
		if (N < 3) {
			return a * b;
		}
		// get the mid
		N = (int) Math.ceil(N / 2);
		long powF = (long) Math.pow(10, N);
		// get the left-right components
		long xLeft = a / powF;
		long xRight = a - (xLeft * powF);
		long yLeft = b / powF;
		long yRight = b - (yLeft * powF);
		// get the multiplicative componenets
		long z1 = multiply(xLeft, yLeft);
		long z2 = multiply((xLeft + xRight), (yLeft + yRight));
		long z3 = multiply(xRight, yRight);
		// get the answer
		return (long) ((Math.pow(10, 2 * N) * z1) + ((z2 - z1 - z3) * powF) + z3);

	}

	private static int getLength(long number) {
		int i = 0;
		while (number > 0) {
			number /= 10;
			i++;
		}
		return i;
	}

}
/**
 * the base case was t multiply two number using the o(n^2) method when the
 * number were only two digits long
 */
