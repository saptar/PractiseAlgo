/**
 * Connect n ropes with minimum cost
 * There are given n ropes of different lengths, we need to connect these ropes into one rope. 
 * The cost to connect two ropes is equal to sum of their lengths. 
 * We need to connect the ropes with minimum cost.
 * Details: http://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/
 */

package com.saptar.gfg.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class NRopesMinimumCost {

	public static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		while (tests-- > 0) {
			int elements = scan.nextInt();
			Integer[] arr = new Integer[elements];
			for (int i = 0; i < elements; i++) {
				arr[i] = scan.nextInt();
			}
			int result = minimumCost(arr);
			System.out.println(result);
			pq.clear();
		}
		scan.close();

	}

	private static int minimumCost(Integer[] arr) {
		int cost = 0;
		// load arr data into pq

		for (Integer i : arr) {
			pq.add(i);
		}
		// select the mimimum two values from the pq
		// add them up and put it back
		while (pq.size() > 1) {
			int temp = pq.poll() + pq.poll();
			cost += temp;
			pq.add(temp);
		}
		return cost;
	}

}
