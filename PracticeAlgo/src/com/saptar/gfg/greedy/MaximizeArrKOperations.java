/**
 * Maximize array sum after K negations
 * Given an array of size n and a number k. We must modify array K number of times. 
 * Here modify array means in each operation we can replace any array element arr[i] by -arr[i].
 * We need to perform this operation in such a way that after K operations, 
 * sum of array must be maximum.
 * For details: http://www.geeksforgeeks.org/maximize-array-sum-k-negations-set-2/
 */

package com.saptar.gfg.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class MaximizeArrKOperations {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int elements = scan.nextInt();
			int arr[] = new int[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			int k = scan.nextInt();
			int results = findMax(arr, k);
			System.out.println(results);
		}
		scan.close();

	}

	private static int findMax(int[] arr, int k) {
		// create a priority queue.
		// using java priority queue
		int sum = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int a : arr) {
			pq.add(a);
		}
		while (k > 0) {
			// retrieve and remove minimum element
			int temp = pq.poll();
			// modify minimum element and add back to the queue
			temp *= -1;
			pq.add(temp);
			k--;
		}

		// compute sum of all element sin priority queue
		for (int a : pq) {
			sum += a;
		}
		return sum;
	}
}
