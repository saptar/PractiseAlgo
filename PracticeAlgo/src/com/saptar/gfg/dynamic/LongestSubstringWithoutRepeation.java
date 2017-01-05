/**
 * Length of the longest substring without repeating characters
 * Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substrings without repeating characters for “ABDEFGABEF” are “BDEFGA” and “DEFGAB”, 
 * with length 6. For “BBBB” the longest substring is “B”, with length 1. For “GEEKSFORGEEKS”, 
 * there are two longest substrings shown in the below diagrams, with length 7.
 * details: http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 */

package com.saptar.gfg.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LongestSubstringWithoutRepeation {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			String str = scan.next();
			int result = findLongestSS(str, str.length());
			System.out.println(result);
		}
		scan.close();

	}

	private static int findLongestSS(String str, int length) {
		// init hashmap
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int current_length = 0;
		int global_length = current_length;
		// iterate over the string
		for (int i = 0; i < length; i++) {
			if (map.size() == 0 || map.get(str.charAt(i)) == null) {
				// this is the first occurence of the letter
				map.put(str.charAt(i), 1);
				current_length = current_length + 1;
			} else {
				// this letter is previously encountered
				if (global_length < current_length) {
					global_length = current_length;
				}
				current_length = 1;
				map.clear();
				int j = i - 1;
				while (str.charAt(j) != str.charAt(i)) {
					current_length = current_length + 1;
					// add to the map
					map.put(str.charAt(j), 1);
					j--;
				}
				// start the next sequence
				map.put(str.charAt(i), 1);
			}
		}
		return (global_length < current_length) ? current_length
				: global_length;
	}
}
