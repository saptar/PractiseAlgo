/**
 * Find minimum time to finish all jobs with given constraints
 * Given an array of jobs with different time requirements. 
 * There are K identical assignees available and 
 * we are also given how much time an assignee takes to do one unit of job. 
 * Find the minimum time to finish all jobs with following constraints.
 * 
 * An assignee can be assigned only contiguous jobs. 
 * For example, an assignee cannot be assigned jobs 1 and 3, but not 2.
 * 
 * Two assignees cannot share (or co-assigned) a job, i.e., 
 * a job cannot be partially assigned to one assignee and partially to other
 * 
 * details: http://www.geeksforgeeks.org/find-minimum-time-to-finish-all-jobs-with-given-constraints/
 */

package com.saptar.gfg.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FinishJobsConstraits {

	public static Integer[] jobs = new Integer[] { 10, 7, 8, 12, 6, 8 };

	public static Map<Integer, Integer[]> assignment = new HashMap<Integer, Integer[]>();

	public static void main(String[] args) {

		Integer assignees = 4;
		int taskTime = 5;
		Integer[] tempJobs = new Integer[jobs.length];
		tempJobs = jobs;
		// initalize the map
		for (int i = 0; i < assignees; i++) {
			assignment.put(i, new Integer[jobs.length]);
		}
		int result = getMinimumTime(assignment, taskTime, assignees, tempJobs);
		System.out.println(result);

	}

	private static int getMinimumTime(Map<Integer, Integer[]> assignment,
			int taskTime, Integer assignees, Integer[] jobs) {

		if (jobs.length == 0) {
			// all jobs have been mapped
			// find the maximum time taken
			int result = 0;
			for (Integer key : assignment.keySet()) {
				Integer[] tasks = assignment.get(key);
				int result_temp = 0;
				for (Integer i : tasks) {
					if (i != null)
						result_temp += i;
				}
				if (result_temp > result) {
					result = result_temp;
				}
			}
			return result;

		}
		Integer[] tempArr = new Integer[jobs.length];
		for (int i = 0; i < jobs.length; i++) {
			tempArr[i] = jobs[i];
		}
		// sort the array in descending
		Arrays.sort(tempArr);
		// find the first assignee with no assinged job
		Integer key = findNonAssigned();
		if (key < 0) {
			// every one is assigned a job
			// find the nearest neigbour of the job.
			Integer job = nearestNeighbour(tempArr[tempArr.length - 1]);
			// find the assignee assigned to the nearest job.
			Integer assigneeKey = findAssigned(job);
			// add this job to the assigneekey
			int index = getNextIndex(assignment.get(assigneeKey));
			assignment.get(assigneeKey)[index] = job;
			// recurse
			Integer[] arr = new Integer[tempArr.length - 1];
			for (int i = 0; i < tempArr.length - 1; i++) {
				arr[i] = tempArr[i];
			}
			return getMinimumTime(assignment, taskTime, assignees, arr);
		} else {
			// there are un assinged user
			assignment.get(key)[0] = tempArr[tempArr.length - 1];
			// recurse
			// remove the job that has been mapped
			Integer[] arr = new Integer[tempArr.length - 1];
			for (int i = 0; i < tempArr.length - 1; i++) {
				arr[i] = tempArr[i];
			}
			return getMinimumTime(assignment, taskTime, assignees, arr);
		}
	}

	private static int getNextIndex(Integer[] integers) {
		int reslt = -1;
		for (int i = 0; i < integers.length; i++) {
			if (integers[i] == null) {
				reslt = i;
				break;
			}
		}
		return reslt;
	}

	private static Integer findAssigned(Integer job) {
		// go thru the hasmap and find the integer array
		// which has the mention job.
		// return the corresponding key.
		Boolean found_flag = false;
		Integer result = -1;
		for (Integer key : assignment.keySet()) {
			Integer[] arr = new Integer[assignment.get(key).length];
			arr = assignment.get(key);
			for (Integer task : arr) {
				if (task == job) {
					found_flag = true;
					break;
				}
			}
			if (found_flag) {
				result = key;
				break;
			}
		}
		// TODO: if more than one assigned user
		// find the user with minimum jobs and return.
		return result;
	}

	private static Integer nearestNeighbour(Integer job) {
		// find the smallest neighbour to this job
		int result = -1, i;
		for (i = 0; i < jobs.length; i++) {
			if (job == jobs[i]) {
				break;
			}
		}
		if (i == 0) {
			result = jobs[i + 1];
		} else if (i == jobs.length - 1) {
			result = jobs[i - 1];
		} else {
			result = (jobs[i - 1] > jobs[i + 1]) ? jobs[i + 1] : jobs[i - 1];
		}
		return result;
	}

	private static Integer findNonAssigned() {
		int result_key = -1;
		for (Integer key : assignment.keySet()) {
			if (assignment.get(key)[0] == null) {
				result_key = key;
				break;
			}
		}
		return result_key;
	}
}
