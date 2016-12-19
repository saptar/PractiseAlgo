/**
 * Minimum Cost to cut a board into squares
 * A board of length m and width n is given, we need to break this board into m*n 
 * squares such that cost of breaking is minimum. cutting cost for each edge will be 
 * given for the board. 
 * In short we need to choose such a sequence of cutting such that cost is minimized.
 * for details: http://www.geeksforgeeks.org/minimum-cost-cut-board-squares/ 
 */
package com.saptar.gfg.greedy;

import java.util.Arrays;

public class CutBoardMinCost {

	public static void main(String[] args) {

		int hCost[] = { 2, 1, 3, 1, 4 };
		int vCost[] = { 4, 1, 2 };
		int minCost = findMinCost(hCost, vCost);
		System.out.println(minCost);
	}

	private static int findMinCost(int[] hCost, int[] vCost) {
		int minCost = 0;
		int lenH = hCost.length - 1;
		int lenV = vCost.length - 1;
		int hCut = 1, vCut = 1;
		// sort the array
		Arrays.sort(hCost);
		Arrays.sort(vCost);
		while (lenH >= 0 && lenV >= 0) {
			if (hCost[lenH] > vCost[lenV]) {
				// select the last element of Horizontal
				// and make the cut
				minCost = minCost + hCost[lenH] * vCut;
				hCut++;
				lenH--;
			} else {
				// select the last element of vertical
				// and make the cut
				minCost = minCost + vCost[lenV] * hCut;
				vCut++;
				lenV--;
			}
		}
		// work with the remaining horizontal or vertical cuts
		while (lenH >= 0) {
			minCost = minCost + hCost[lenH] * vCut;
			lenH--;
		}
		while (lenV >= 0) {
			minCost = minCost + vCost[lenH] * hCut;
			lenV--;
		}
		return minCost;
	}
}
