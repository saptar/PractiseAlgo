/**
 * Find maximum sum possible equal sum of three stacks
 * Given three stack of the positive numbers, 
 * the task is to find the possible equal maximum sum of the stacks with 
 * removal of top elements allowed. Stacks are represented as array, 
 * and the first index of the array represent the top element of the stack.
 */

package com.saptar.gfg.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaximumSumStack {

	public static void main(String[] args) {
		Integer[] stack1 = new Integer[] { 3, 2, 1, 1, 1 };
		Integer[] stack2 = new Integer[] { 4, 3, 2 };
		Integer[] stack3 = new Integer[] { 2, 5, 4, 1 };
		List<Integer[]> arr_list = new ArrayList<Integer[]>();
		arr_list.add(stack1);
		arr_list.add(stack2);
		arr_list.add(stack3);
		int result = findMaxSum(arr_list);
		System.out.println(result);
	}

	private static int findMaxSum(List<Integer[]> arr_list) {
		Boolean flag = false;
		// base cases
		// if any of the stack has sum of its elements as 0 or has only one
		// element and its 0
		// return zero
		for (Integer[] a : arr_list) {
			if (sumArr(a) == 0) {
				return 0;
			}
		}
		// sort the collection in terms of greater value of stack
		Collections.sort(arr_list, new Comparator<Integer[]>() {
			public int compare(Integer[] arg0, Integer[] arg1) {
				return sumArr(arg1) - sumArr(arg0);
			}
		});
		// get the minimum value
		int minStackSum = sumArr(arr_list.get(arr_list.size() - 1));
		if (sumArr(arr_list.get(0)) > minStackSum) {
			flag = true;
		} else if (sumArr(arr_list.get(0)) == minStackSum) {
			// check the sum of the other stack
			for (int i = 1; i < arr_list.size() - 1; i++) {
				if (sumArr(arr_list.get(i)) != minStackSum) {
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			// remove the top from the largest stack and recurse on the arr_list
			Integer[] arrMod = new Integer[arr_list.get(0).length - 1];
			for (int i = 1; i < arr_list.get(0).length; i++) {
				int j = i - 1;
				arrMod[j] = arr_list.get(0)[i];
			}
			arr_list.set(0, arrMod);
			return findMaxSum(arr_list);
		}
		return minStackSum;
	}

	private static int sumArr(Integer[] a) {
		int sum = 0;
		if (a.length < 1) {
			return sum;
		} else {
			for (Integer ele : a) {
				sum += ele;
			}
		}
		return sum;
	}
}
