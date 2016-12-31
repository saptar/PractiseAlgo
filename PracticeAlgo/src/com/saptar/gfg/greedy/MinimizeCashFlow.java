package com.saptar.gfg.greedy;


public class MinimizeCashFlow {
	public static final int PERSON = 3;

	public static void main(String[] args) {
		int[][] graph = { { 0, 1000, 2000 }, { 0, 0, 5000 }, { 0, 0, 0 } };
		minCashFlow(graph);
	}

	private static void minCashFlow(int[][] graph) {
		// create an amount array for each person(idx) which has the net amount.
		// -ve for payable/debitable and +ve for recievable/creditable
		int[] amount = new int[PERSON];
		// traverse the array
		for (int p = 0; p < graph.length; p++) {
			// for each p calculate the amount
			amount[p] = 0;
			for (int i = 0; i < graph[p].length; i++) {
				amount[p] += graph[i][p] - graph[p][i];
			}
		}
		minCashFlowRec(amount);
	}

	private static void minCashFlowRec(int[] amount) {
		// the idea is to get the max creditor and max debitor
		// find the min amount between the creditor and the debitor
		// subtract that amount from the creditor and add to the debitor
		// since this is a zero sum game, if there is a postive amount in the
		// amount arr
		// there must be a negative amount in the array as well
		int maxCredit = getMax(amount), maxDebit = getMin(amount);
		if (amount[maxCredit] == 0 && amount[maxDebit] == 0) {
			// all settled up
			return;
		}
		// Find the minimum amount between the maximum credit and max debit
		int min = findMin(-amount[maxDebit], amount[maxCredit]);
		// subtract that amount from creditor
		amount[maxCredit] -= min;
		// add the same to the debitor.
		amount[maxDebit] += min;

		System.out.println("Person " + maxDebit + " pays " + maxCredit
				+ " an amount of " + min);
		// recurse with the changed amount array
		minCashFlowRec(amount);

	}

	private static int findMin(int i, int j) {
		return (i < j) ? i : j;
	}

	private static int getMin(int[] amount) {
		int min = Integer.MAX_VALUE - 1;
		int min_idx = -1;
		for (int i = 0; i < amount.length; i++) {
			if (amount[i] < min) {
				min = amount[i];
				min_idx = i;
			}
		}
		return min_idx;
	}

	private static int getMax(int[] amount) {
		int max = Integer.MIN_VALUE + 1;
		int max_idx = -1;
		for (int i = 0; i < amount.length; i++) {
			if (amount[i] > max) {
				max = amount[i];
				max_idx = i;
			}
		}
		return max_idx;
	}
}
