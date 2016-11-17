/**
 * Given a set of points in cartesian plane, the task is to find the shortest
 * distance between any two points
 * 
 * The naive technique of comparing every point to every other point would take
 * a worst case runtime of n^2
 * 
 * The idea is to use the concepts of divide and conquer to reduce the worst case
 * runtime to nlogn.
 * 
 * the full details of the problem statement can be found at
 * http://www.geeksforgeeks.org/closest-pair-of-points-onlogn-implementation/
 * 
 * and the technique used is elaborated for 1-d,2-d and n-dimensional plane in the following
 * https://www.cs.ucsb.edu/~suri/cs235/ClosestPair.pdf
 */

package com.saptar.gfg.dq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ClosestPair {
	// create point object with x and y co-ordinates
	public static class Point {
		public final double x;
		public final double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	// create class to hold pair of points and
	// hold the distance between them.
	public static class Pair {
		public Point p1;
		public Point p2;
		public double distance = 0.0;

		public Pair() {
		}

		public Pair(Point point1, Point point2) {
			this.p1 = point1;
			this.p2 = point2;
			calcDistance();
		}

		public void update(Point point1, Point point2, double distance) {
			this.p1 = point1;
			this.p2 = point2;
			this.distance = distance;
		}

		public void calcDistance() {
			this.distance = distance(this.p1, this.p2);
		}

		public String toString() {
			return p1 + " - " + p2 + " : " + distance;
		}
	}

	public static double distance(Point p1, Point p2) {
		return Math.hypot((p2.x - p1.x), (p2.y - p2.y));
	}

	public static Pair bruteForce(List<? extends Point> points) {
		int numOfPoints = points.size();
		if (numOfPoints < 2) {
			// a pair does not exist
			return null;
		}
		Pair pair = new Pair(points.get(0), points.get(1));
		if (numOfPoints > 2) {
			// there are more than two points in the cartesial plane
			for (int i = 0; i < numOfPoints - 1; i++) {
				Point point1 = points.get(i);
				for (int j = i + 1; j < numOfPoints; j++) {
					Point point2 = points.get(j);
					double distance = distance(point1, point2);
					if (distance < pair.distance) {
						pair.update(point1, point2, distance);
					}
				}
			}
		}
		return pair;
	}

	public static void sortByX(List<? extends Point> points) {
		Collections.sort(points, new Comparator<Point>() {
			public int compare(Point point1, Point point2) {
				if (point1.x < point2.x)
					return -1;
				if (point1.x > point2.x)
					return 1;
				return 0;
			}
		});
	}

	public static void sortByY(List<? extends Point> points) {
		Collections.sort(points, new Comparator<Point>() {
			public int compare(Point point1, Point point2) {
				if (point1.y < point2.y)
					return -1;
				if (point1.y > point2.y)
					return 1;
				return 0;
			}
		});
	}

	public static Pair divideAndConquer(List<? extends Point> points) {
		List<Point> pointsSortedByX = new ArrayList<Point>(points);
		sortByX(pointsSortedByX);
		List<Point> pointsSortedByY = new ArrayList<Point>(points);
		sortByY(pointsSortedByY);
		return divideAndConquer(pointsSortedByX, pointsSortedByY);
	}

	private static Pair divideAndConquer(List<? extends Point> pointsSortedByX,
			List<? extends Point> pointsSortedByY) {
		int numPoints = pointsSortedByX.size();
		if (numPoints <= 3)
			return bruteForce(pointsSortedByX);

		int dividingIndex = numPoints >>> 1;
		List<? extends Point> leftOfCenter = pointsSortedByX.subList(0,
				dividingIndex);
		List<? extends Point> rightOfCenter = pointsSortedByX.subList(
				dividingIndex, numPoints);

		List<Point> tempList = new ArrayList<Point>(leftOfCenter);
		sortByY(tempList);
		Pair closestPair = divideAndConquer(leftOfCenter, tempList);

		tempList.clear();
		tempList.addAll(rightOfCenter);
		sortByY(tempList);
		Pair closestPairRight = divideAndConquer(rightOfCenter, tempList);

		if (closestPairRight.distance < closestPair.distance)
			closestPair = closestPairRight;

		tempList.clear();
		double shortestDistance = closestPair.distance;
		double centerX = rightOfCenter.get(0).x;
		for (Point point : pointsSortedByY)
			if (Math.abs(centerX - point.x) < shortestDistance)
				tempList.add(point);

		for (int i = 0; i < tempList.size() - 1; i++) {
			Point point1 = tempList.get(i);
			for (int j = i + 1; j < tempList.size(); j++) {
				Point point2 = tempList.get(j);
				if ((point2.y - point1.y) >= shortestDistance)
					break;
				double distance = distance(point1, point2);
				if (distance < closestPair.distance) {
					closestPair.update(point1, point2, distance);
					shortestDistance = distance;
				}
			}
		}
		return closestPair;
	}

	public static void main(String[] args) {
		int numPoints = (args.length == 0) ? 1000 : Integer.parseInt(args[0]);
		List<Point> points = new ArrayList<Point>();
		Random r = new Random();
		for (int i = 0; i < numPoints; i++)
			points.add(new Point(r.nextDouble(), r.nextDouble()));
		System.out.println("Generated " + numPoints + " random points");
		long startTime = System.currentTimeMillis();
		Pair bruteForceClosestPair = bruteForce(points);
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Brute force (" + elapsedTime + " ms): "
				+ bruteForceClosestPair);
		startTime = System.currentTimeMillis();
		Pair dqClosestPair = divideAndConquer(points);
		elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Divide and conquer (" + elapsedTime + " ms): "
				+ dqClosestPair);
		if (bruteForceClosestPair.distance != dqClosestPair.distance)
			System.out.println("MISMATCH");
	}

}
