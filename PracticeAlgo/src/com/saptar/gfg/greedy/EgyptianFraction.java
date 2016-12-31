/**
 * Greedy Algorithm for Egyptian Fraction
 * Every positive fraction can be represented as sum of unique unit fractions. 
 * A fraction is unit fraction if numerator is 1 and denominator is a positive integer, 
 * for example 1/3 is a unit fraction. 
 * Such a representation is called Egyptial Fraction as it was used by ancient Egyptians.
 * Details: http://www.geeksforgeeks.org/greedy-algorithm-egyptian-fraction/
 */

package com.saptar.gfg.greedy;

import java.util.Scanner;

public class EgyptianFraction {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int nr = scan.nextInt();
			int dr = scan.nextInt();
			System.out.println(findUnitFractions(nr, dr));
		}
		scan.close();

	}

	private static String findUnitFractions(int nr, int dr) {
		// base cases
		if (nr == 0 || dr == 0) {
			return "No unit fractions";
		}
		if (nr == 1 && dr > 1) {
			// end of recursion
			return nr + "/" + dr;
		}
		// if the dr divides the br completely
		// then no a fraction
		if (nr % dr == 0) {
			return ("Not a fraction" + " " + nr + "/" + dr);
		}
		// if the nr divide the denomator then
		if (dr % nr == 0) {
			// simple fraction
			return (nr / nr + "/" + dr / nr + "");
		}
		if (nr > dr) {
			// not a proper fraction
			return (nr + "/" + dr + "" + findUnitFractions(nr % dr, dr));
		}

		// otherwise
		// get the denominator of the first unit fraction.
		int denominator = (dr / nr) + 1;
		// get the remaining fraction.
		int rem_nr = (nr * denominator) - (1 * dr);
		int rem_dr = (denominator * dr);
		return 1 + "/" + denominator + "+" + findUnitFractions(rem_nr, rem_dr);

	}
}
