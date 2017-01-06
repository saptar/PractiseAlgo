/**
 * Edit Distance
 * Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.

	Insert
	Remove
	Replace
	All of the above operations are of equal cost.
	
	Details: http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 */

package com.saptar.gfg.dynamic;

import java.util.Arrays;
import java.util.Scanner;

public class EditDistance {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int str1_length = scan.nextInt();
			int str2_length = scan.nextInt();
			String str1 = scan.next();
			String str2 = scan.next();
			char[] str1_arr = new char[str1_length];
			char[] str2_arr = new char[str2_length];
			str1_arr = str1.toCharArray();
			str2_arr = str2.toCharArray();
			int result = ED(str1_arr, str2_arr, str1_length - 1,
					str2_length - 1);
			System.out.println(result);

		}
		scan.close();

	}

	private static int ED(char[] str1, char[] str2, int m, int n) {
		// base case
		if (str1.length == 0) {
			return 0;
		}
		if (m == 0 || n == 0) {
			if (n == 0 && m > 0) {
				// remove the rest of elements from the str1
				// and return cost
				return m + 1;
			} else if (m == 0 && n > 0) {
				return n + 1;
			} else {
				return 0;
			}
		}
		if (str1[m] == str2[n]) {
			// nothing to change; no cost incurred
			return ED(str1, str2, m - 1, n - 1);
		} else {
			// case when the char don't match
			// figure out if we should remove or insert
			// replace is possible for all cases

			char[] temp_char_arr = {};
			if (n > m) {
				// insert
				temp_char_arr = new char[m + 2];
				temp_char_arr = insert(str1, str2, m, n);

			} else if (m > n) {
				// remove
				temp_char_arr = new char[m];
				temp_char_arr = remove(str1, str2, m, n);
			}
			char[] temp_char_arr_replace = new char[m + 1];
			temp_char_arr_replace = replace(str1, str2, m, n);

			return 1 + Math.min((n > m) ? ED(temp_char_arr, str2, m, n - 1)
					: ED(temp_char_arr, str2, m - 1, n),
					ED(temp_char_arr_replace, str2, m - 1, n - 1));
		}
	}

	private static char[] insert(char[] str1, char[] str2, int m, int n) {
		char[] arr = Arrays.copyOf(str1, m + 2);
		arr[m + 1] = str2[n];
		return arr;
	}

	private static char[] replace(char[] str1, char[] str2, int m, int n) {
		char[] arr = Arrays.copyOf(str1, m + 1);
		arr[m] = str2[n];
		return arr;

	}

	private static char[] remove(char[] str1, char[] str2, int m, int n) {
		char[] arr = Arrays.copyOf(str1, m);
		return arr;
	}

}
