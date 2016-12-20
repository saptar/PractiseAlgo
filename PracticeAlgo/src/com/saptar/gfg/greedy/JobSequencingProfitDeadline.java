/**
 * Job Sequencing Problem 
 * Given a set of n jobs where each job i has a deadline di >=1
 * and profit pi>=0. Only one job can be scheduled at a time. 
 * Each job takes 1 unit of time to complete. 
 * We earn the profit if and only if the job is completed by its deadline. 
 * The task is to find the subset of jobs that maximizes profit.
 * for details: http://www.geeksforgeeks.org/job-sequencing-using-disjoint-set-union/
 */

package com.saptar.gfg.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JobSequencingProfitDeadline {

	public static class Job implements Comparable<Job> {

		private String jobId;
		private int deadline;
		private int profit;

		public Job(String jId, int dline, int pft) {
			this.jobId = jId;
			this.deadline = dline;
			this.profit = pft;
		}

		public Job() {
		}

		public String getJobId() {
			return jobId;
		}

		public void setJobId(String jobId) {
			this.jobId = jobId;
		}

		public int getDeadline() {
			return deadline;
		}

		public void setDeadline(int deadline) {
			this.deadline = deadline;
		}

		public int getProfit() {
			return profit;
		}

		public void setProfit(int profit) {
			this.profit = profit;
		}

		public int compareTo(Job j1) {
			return j1.profit - this.profit;
		}

	}

	public static class DisjoinSet {
		int arr[];

		public DisjoinSet(int n) {
			arr = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				// initialize every parent node to the item itself
				arr[i] = i;
			}
		}

		// path compression
		int find(int s) {
			if (arr[s] == s) {
				return s;
			}
			// path compression
			return arr[s] = find(arr[s]);
		}

		void union(int c, int p) {
			arr[c] = p;
		}
	}

	public static void main(String[] args) {
		List<Job> jobList = new ArrayList<Job>();
		// create job object
		Job j1 = new Job("a", 4, 100);
		Job j2 = new Job("b", 1, 19);
		Job j3 = new Job("c", 2, 27);
		Job j4 = new Job("d", 1, 25);
		Job j5 = new Job("e", 3, 15);
		jobList.add(j1);
		jobList.add(j2);
		jobList.add(j3);
		jobList.add(j4);
		jobList.add(j5);
		getMaxProfitJob(jobList);

	}

	private static void getMaxProfitJob(List<Job> jobList) {
		// sort the jobs in descending
		Collections.sort(jobList);
		int largestDeadline = getLargestDeadline(jobList);
		// use this to create the parent array
		DisjoinSet djs = new DisjoinSet(largestDeadline);
		for (Job temp : jobList) {
			int schedule = djs.find(temp.getDeadline());
			if (schedule > 0) {
				// the job can be scheduled
				// merge the schedule with the schedule-1
				// so when ever the next job requests for the greatest available
				// timeslot in accordance with the deadline, the merged value
				// with
				// been shown.
				djs.union(schedule, djs.find(schedule - 1));
				System.out.print(temp.getJobId() + "\t");
			}
		}

	}

	private static int getLargestDeadline(List<Job> jobList) {
		int result = 0;
		for (Job temp : jobList) {
			if (temp.getDeadline() > result) {
				result = temp.getDeadline();
			}
		}
		return result;
	}

}
