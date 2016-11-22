/**
 * Unbounded binary search example
 * Find the point where a monotonically increasing function becomes positive for the very first time.
 * 
 * Given a function int f(unsigned int x) which takes in a positve integer and returns another integer,is a
 * monotonically increasing function when f(x)<f(x+1).
 * 
 * The task is to find a point n belonging to X when the function turns positive.
 * Meaning f(n+1),f(n-1) are all positive where as f(n-1), f(n-2) are all negative.
 * 
 * The naive approach would be to get the output of the function for every value of n and return n when the function
 * turns positive.
 * the worst case run time for such an algo is O(n)
 * 
 * Can we apply the principles of binary search to such a problem where the upper bound is not fixed?
 * 
 * For reference : http://www.geeksforgeeks.org/find-the-point-where-a-function-becomes-negative/
 */

package com.saptar.gfg.dq;

public class FirstPositive {
	public static int func(long x) {
		return (int) (x * x - 10 * x - 20);
	}

	public static void main(String[] args) {
		// get the lower and upper bounds
		int upperBound = getBounds();
		System.out.println("The upper bound is " + upperBound);
		int pointOfPositive = binSearch(upperBound / 2, upperBound);
		System.out
				.println("The monotonically increasing function becomes positive at "
						+ pointOfPositive);
	}

	private static int binSearch(int low, int high) {
		// base cases
		if (low > high) {
			return -1;
		}
		if (low == high) {
			return low;
		}
		// mid point
		int mid = low + (high - low) / 2;
		// if mid point itself is the first occurence of non-negative value
		if (mid >= low && func(mid) >= 0 && func(mid - 1) < 0) {
			return mid;
		}
		if (func(mid) > 0) {
			// look left
			return binSearch(low, mid - 1);
		} else {
			return binSearch(mid + 1, high);
		}
	}

	private static int getBounds() {
		int x = 1;
		while (func(x) < 0) {
			x *= 2;
		}
		return x;
	}

}
