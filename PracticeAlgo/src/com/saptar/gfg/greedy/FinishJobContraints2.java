package com.saptar.gfg.greedy;

public class FinishJobContraints2 {

	public static void main(String[] args) {
		int[] job = { 10, 7, 8, 12, 6, 8 };
		int n = job.length;
		int k = 4, T = 5;
		int result = findMinTime(job, k, T, n);
		System.out.println(result);

	}

	// the idea is to get the maximum time required
	// which will be the addition of all the job times.
	// so we can use binary search to find the min time required

	private static int findMinTime(int[] job, int k, int t, int n) {
		int start = 0;
		int end = findMaxTime(job);
		// find the max time taken by a job
		int job_max = maxTimeJob(job);
		// apply binary search
		int result = end;
		while (end >= start) {
			// get the mid
			int mid = (start + end) / 2;
			if (mid >= job_max && isDoable(mid, k, t, job)) {
				// look left for the minimum time
				result = Math.min(result, mid);
				end = mid - 1;
			} else {
				start = mid + 1;
			}

		}
		return result * t;
	}

	// this function return true or false
	// on whether given a time limit mid with k assignees
	// where each unit of job takes t time can be completed
	// by k or less assigness.
	private static boolean isDoable(int mid, int k, int t, int[] job) {
		int assignee = 0;
		int job_time = 0;
		// the idea is to add every task to one assignee
		// till the time taken to complete all the assigned task
		// becomes greater than time alloted i.e mid.
		for (int j = 0; j < job.length; j++) {
			// consider each job
			if (job[j] + job_time <= mid) {
				job_time += job[j];
			} else {
				job_time = job[j];
				assignee++;
			}
		}

		boolean result = (assignee > k - 1) ? false : true;
		return result;
	}

	private static int maxTimeJob(int[] job) {
		int max = Integer.MIN_VALUE + 1;
		for (int i = 0; i < job.length; i++) {
			if (job[i] > max) {
				max = job[i];
			}
		}
		return max;
	}

	private static int findMaxTime(int[] job) {
		int max_time = 0;
		for (int i = 0; i < job.length; i++) {
			max_time += job[i];
		}
		return max_time;
	}

}
