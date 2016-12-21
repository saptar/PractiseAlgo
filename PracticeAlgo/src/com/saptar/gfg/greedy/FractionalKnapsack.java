/**
 * Fractional Knapsack Problem
 * Given weights and values of n items, 
 * we need put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
 */

package com.saptar.gfg.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FractionalKnapsack {

	public static class Item implements Comparable<Item> {
		private int value;
		private int weight;
		private int score;

		public Item(int v, int s) {
			this.value = v;
			this.weight = s;
			this.score = v / s;
		}

		public Item() {
		}

		public void setScore() {
			this.score = this.value / this.weight;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getScore() {
			return score;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int w) {
			this.weight = w;
		}

		public int compareTo(Item arg0) {
			return (this.score >= arg0.score) ? -1 : 1;
		}

	}

	public static void main(String[] args) {
		int arr[][] = { { 60, 10 }, { 100, 20 }, { 120, 30 } };
		int weight = 50;
		int result = maxValue(arr, weight);
		System.out.println(result);

	}

	private static int maxValue(int[][] arr, int weight) {
		int result = 0;
		// populate the items
		List<Item> items = new ArrayList<Item>();
		int remaining_weight = weight;
		for (int i = 0; i < arr.length; i++) {
			Item itm = new Item();
			for (int j = 0; j < arr[0].length; j++) {
				if (j > 0) {
					itm.setWeight(arr[i][j]);
				} else {
					itm.setValue(arr[i][j]);
				}
			}
			itm.setScore();
			items.add(itm);
		}
		// sort
		Collections.sort(items);
		for (Item item : items) {
			if (remaining_weight > item.getWeight()) {
				// get this in
				result += item.getValue();
				remaining_weight -= item.getWeight();
			} else {
				// break up the item
				int weight_can_take = remaining_weight;
				float fraction = (float) weight_can_take
						/ (float) item.getWeight();
				int value_can_take = (int) (item.getValue() * fraction);
				result += value_can_take;
				remaining_weight = 0;
			}
		}

		return result;
	}
}
