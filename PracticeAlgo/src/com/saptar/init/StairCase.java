package com.saptar.init;

import java.util.Scanner;

public class StairCase {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		drawStairCase(n);
		in.close();
	}

	private static void drawStairCase(int n) {
		// create a two dimensional int array
		int[][] arr = new int[n][n];
		// populate the array
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j >= 0; j--) {
				int temp_index = j + (n - 1 - i);
				arr[i][temp_index] = 1;
			}
		}
		// print the 2D array
		for (int k = 0; k < n; k++) {
			for (int p = 0; p < n; p++) {
				if (arr[k][p] == 1) {
					System.out.print("#");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}

	}

}
