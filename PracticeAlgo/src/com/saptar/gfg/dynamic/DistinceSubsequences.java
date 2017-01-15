/**
 * Count distinct occurrences as a subsequence
 * Given a two strings S and T, find count of distinct occurrences of T in S as a subsequence.
 */

package com.saptar.gfg.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class DistinceSubsequences {
	public static class DP {
		private String s;
		private String t;

		public DP() {

		}

		public DP(String s, String t) {
			this.s = s;
			this.t = t;
		}

		public String getS() {
			return s;
		}

		public void setS(String s) {
			this.s = s;
		}

		public String getT() {
			return t;
		}

		public void setT(String t) {
			this.t = t;
		}

		@Override
		public int hashCode() {
			return Objects.hash(s, t);
		}

		@Override
		public boolean equals(Object o) {
			if (o == this) {
				return true;
			}
			if (!(o instanceof DP)) {
				return false;
			}
			DP dp = (DP) o;
			return this.s.equals(dp.s) && this.t.equals(dp.t);
		}

	}

	public static Map<DP, Integer> map = new HashMap<DP, Integer>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			String str1 = scan.next();
			String str2 = scan.next();
			int result = findSubsequence(str1, str2);
			System.out.println(result);
		}
		scan.close();

	}

	private static int findSubsequence(String s, String t) {
		// if the string to match is ""
		// return 1
		if (t.trim().length() == 0) {
			return 1;
		}
		// if the string S to find subsequences of t is
		// "" then return 0
		if (s.trim().length() == 0) {
			return 0;
		}
		// start checking for match of character between t and s
		// starting from the back
		DP dp = new DP(s, t);
		if (map.get(dp) != null) {
			return map.get(dp);
		}
		int t_length = t.length();
		int s_length = s.length();
		if (s.charAt(s_length - 1) != t.charAt(t_length - 1)) {
			// reduce string s and recurse
			String str_new = s.substring(0, s_length - 1);

			int temp = findSubsequence(str_new, t);
			map.put(dp, temp);
			return map.get(dp);
		} else {
			// if there is a match
			// then find the total subsequence by considering this character
			// and add it to subsequence by not considering this character
			String s_new = s.substring(0, s_length - 1);
			String t_new = t.substring(0, t_length - 1);
			int temp = findSubsequence(s_new, t_new)
					+ findSubsequence(s_new, t);
			map.put(dp, temp);
			return map.get(dp);
		}
	}

}
