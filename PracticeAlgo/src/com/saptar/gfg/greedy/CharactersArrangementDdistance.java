/**
 * Rearrange a string so that all same characters become d distance away
 * Given a string and a positive integer d. Some characters may be repeated in the given string. 
 * Rearrange characters of the given string such that the same characters become d distance away from each other. 
 * Note that there can be many possible rearrangements, the output should be one of the possible rearrangements. 
 * If no such arrangement is possible, that should also be reported.
 * 
 * Details: http://www.geeksforgeeks.org/rearrange-a-string-so-that-all-same-characters-become-at-least-d-distance-away/
 */

package com.saptar.gfg.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CharactersArrangementDdistance {

	private static Character[] result;

	public static void main(String[] args) {
		String test = "saptarshi";
		int d = 3;
		rearrange(test, d);
		System.out.println(Arrays.toString(result));

	}

	private static void rearrange(String test, int d) {
		// create a hashmap of character and frequency.
		Map<Character, Integer> freqMap = new HashMap<Character, Integer>();
		// populate the hashmap
		for (int i = 0; i < test.length(); i++) {
			freqMap.put(
					test.charAt(i),
					(freqMap.get(test.charAt(i)) == null) ? 1 : (freqMap
							.get(test.charAt(i)) + 1));
		}
		// sort this in descending in terms of value.
		Set<Entry<Character, Integer>> set = freqMap.entrySet();
		// put it in a collections such as List.
		List<Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(
				set);
		// sort
		Collections.sort(list, new Comparator<Entry<Character, Integer>>() {

			public int compare(Entry<Character, Integer> arg0,
					Entry<Character, Integer> arg1) {
				return (arg1.getValue()).compareTo(arg0.getValue());
			}

		});

		// intialise a character array of size equal to test
		result = new Character[test.length()];
		int idx = 0;
		for (Entry<Character, Integer> element : list) {
			enterCharacterInArray(idx, element.getKey(), element.getValue(), d);
			System.out.println(Arrays.toString(result));
			idx++;
		}

	}

	private static void enterCharacterInArray(int i, Character key,
			Integer value, int d) {
		while (value > 0) {
			int position = (value - 1) * d + i;
			if (position > result.length - 1) {
				System.out.println("Cannot be arranged");
				return;
			}
			if (result[position] == null) {
				result[position] = key;
			} else {
				// get the index of the first null
				for (int k = 0; k < result.length; k++) {
					if (result[k] == null) {
						i = k + (value - 1) * d;
						if (i > result.length - 1) {
							System.out.println("Cannot be arranged");
							return;
						}
						result[i] = key;
						break;
					}
				}
			}
			value = value - 1;

		}

	}
}
