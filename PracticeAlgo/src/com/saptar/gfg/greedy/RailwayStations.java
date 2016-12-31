/**
 * Minimum Number of Platforms Required for a Railway/Bus Station
 * Given arrival and departure times of all trains that reach a railway station, 
 * find the minimum number of platforms required for the railway station so that no train waits.
 * We are given two arrays which represent arrival and departure times of trains that stop.
 * Details: http://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
 */

package com.saptar.gfg.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RailwayStations {
	// create a class to store the arrival and departure time for every train/
	public static class TrainTime implements Comparable<TrainTime> {
		private int arrTime;
		private int depTime;

		public TrainTime(int a, int d) {
			this.arrTime = a;
			this.depTime = d;
		}

		public TrainTime() {
		}

		public int getArrTime() {
			return arrTime;
		}

		public void setArrTime(int arrTime) {
			this.arrTime = arrTime;
		}

		public int getDepTime() {
			return depTime;
		}

		public void setDepTime(int depTime) {
			this.depTime = depTime;
		}

		public int compareTo(TrainTime arg0) {
			return this.getArrTime() - arg0.getArrTime();
		}

	}

	public static void main(String[] args) {
		int[] arr = { 900, 940, 950, 1100, 1500, 1800 };
		int[] dep = { 910, 1210, 1120, 1130, 1900, 2000 };
		int result = noOfPlatform(arr, dep);
		System.out.println(result);

	}

	private static int noOfPlatform(int[] arr, int[] dep) {
		int result = 0;
		if (arr.length == 0 || dep.length == 0 || dep.length > arr.length) {
			return result;
		}
		if (arr.length > 0 && dep.length == 0) {
			// none of the train leaves
			return arr.length;
		}
		// populate a arraylist of Traintimes.
		List<TrainTime> list = new ArrayList<TrainTime>();
		for (int i = 0; i < arr.length; i++) {
			// considering every arrival time has an associated dep time.
			TrainTime tt = new TrainTime(arr[i], dep[i]);
			list.add(tt);

		}
		// sort the list.
		Collections.sort(list);
		// create an array of platforms.
		// upper bound on platforms would be number of trains
		int[] platforms = new int[arr.length];
		// initialise the array to minimum dep time
		for (int i = 0; i < platforms.length; i++) {
			platforms[i] = 0;
		}
		// the value of each index contains the latest dep time
		for (TrainTime t : list) {
			int arr_time = t.getArrTime();
			int dep_time = t.getDepTime();
			for (int i = 0; i < platforms.length; i++) {
				if (arr_time > platforms[i]) {
					platforms[i] = dep_time;
					int p = i + 1;
					System.out.println("Train with arr time " + arr_time
							+ " is at platform " + p);
					break;
				}
			}
		}
		for (int i = 0; i < platforms.length; i++) {
			if (platforms[i] == 0) {
				result = i;
				break;
			}
		}
		return result;
	}
}
