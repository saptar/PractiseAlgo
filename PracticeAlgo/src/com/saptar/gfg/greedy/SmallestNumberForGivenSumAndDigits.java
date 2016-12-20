/**
 * 
 */

package com.saptar.gfg.greedy;

import java.util.Scanner;

public class SmallestNumberForGivenSumAndDigits {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int sum = scan.nextInt();
			int digits = scan.nextInt();
			int result = getSmallestNumber(sum, digits);
			System.out.println(result);
		}
		scan.close();

	}

	private static int getSmallestNumber(int sum, int digits) {
		// get the largest possible number at one's place
		// then move on to the next.
		if (digits * 9 < sum) {
			return -1;
		}
		if (digits == 1 && sum / 10 == 0) {
			return sum;
		}
		int result = 0;
		int idx = 0;
		int d = digits;
		while (digits > 0) {
			// get largest decimal integer smaller than the sum
			int digit = 9;
			while (digit > sum) {
				digit--;
			}

			if (sum - digit == 0 && idx < d - 1) {
				digit--;
			}
			result = (int) (result + Math.pow(10, idx) * digit);
			idx++;
			sum = sum - digit;
			digits--;

		}
		return result;
	}
}
