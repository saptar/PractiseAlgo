/**
 *Input: Array of buildings
	       { (1,11,5), (2,6,7), (3,13,9), (12,7,16), (14,3,25),
	         (19,18,22), (23,13,29), (24,4,28) }
	Output: Skyline (an array of rectangular strips)
	        A strip has x coordinate of left side and height 
	        (1, 11), (3, 13), (9, 0), (12, 7), (16, 3), (19, 18),  
	        (22, 3), (25, 0)
	The below figure (taken from here) demonstrates input and output.  
	The left side shows buildings and right side shows skyline.
	
	refer http://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/
	refer http://javabypatel.blogspot.in/2016/08/skyline-problem-solution-in-java.html
 *
 */




package com.saptar.gfg.dq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {
	
	private static List<int[]> generateSkyline(int[][] arr,int low,int high){
		// split the cluster of buildings recursively
		int midPoint = low +(high-low)/2;
		// list to hold the diagonal co-ordinates of individual strip
		// the leaf would have the diagaonal co-oridnates of a single building
		List<int[]> diagonalCord = new ArrayList<int[]>();
		// check the high and the low values
		if(low>high){
			return diagonalCord;
		}
		else if(low == high){// the leaf case
			int D0[] = new int[2];// to hold the start pt and the ht
			int D1[] = new int[2];// to hold the end pt and ht=0
			D0[0] = arr[low][0];
			D0[1] = arr[low][1];
			D1[0] = arr[low][2];
			D1[1] = 0; // the ht of the end diagonal co-ord would be always zero.
			diagonalCord.add(D0);
			diagonalCord.add(D1);
			return diagonalCord;
		}
		else{
			// recurse
			List<int[]> sky1 = generateSkyline(arr, low, midPoint);
			List<int[]> sky2 = generateSkyline(arr, midPoint+1, high);
			// merge the individual skyline to make a bigger block
			return mergeSkyline(sky1,sky2);
		}
	}

	private static List<int[]> mergeSkyline(List<int[]> sky1, List<int[]> sky2) {
		// check the sky1->x and sky2->x
		// which ever is smaller will be in the final list
		List<int[]> blockSkyline = new ArrayList<int[]>();
		// height of the sky
		int previousHeightSky1 = 0;
		int previousHeightSky2 = 0;
		int sky1Count = 0,sky2Count = 0;
		while(!sky1.isEmpty() || !sky2.isEmpty()){
			int tempCordinate[] = new int[2];
			if(sky1.isEmpty() && !sky2.isEmpty()){
				blockSkyline.add(sky2.get(0));
				sky2.remove(0);
				continue;
			}
			if(sky2.isEmpty() && !sky1.isEmpty()){
				blockSkyline.add(sky1.get(0));
				sky1.remove(0);
				continue;
			}
			if(sky1.get(sky1Count)[0] < sky2.get(sky2Count)[0]){
				// checking for the x, which ever is smaller stays
				tempCordinate[0] = sky1.get(sky1Count)[0];
				tempCordinate[1] = Math.max(sky1.get(sky1Count)[1],previousHeightSky2);
				previousHeightSky1 = tempCordinate[1];
				blockSkyline.add(tempCordinate);
				sky1.remove(0);
			}
			else{
				// checking for the x, which ever is smaller stays
				tempCordinate[0] = sky2.get(sky2Count)[0];
				tempCordinate[1] = Math.max(sky2.get(sky1Count)[1],previousHeightSky1);
				previousHeightSky2 = tempCordinate[1];
				blockSkyline.add(tempCordinate);
				sky2.remove(0);
			}
		}
		return blockSkyline;
	}

	public static void main(String[] args) {
		int arr[][]={
				{1,11,5},
				{2,6,7},
				{3,13,9},
				{14,3,25},
				{19,18,22},
				{23,13,29},
				{24,4,28},
		};
		List<int[]> skyline = new ArrayList<int[]>();
		List<int[]> skylineSet = new ArrayList<int[]>();
		skyline = generateSkyline(arr, 0, arr.length-1);
		// remove consecutive occurence of co-ordinates with same height.
		skylineSet.add(skyline.get(0));
		for(int j = 1,k = 0;j<skyline.size();j++){
			if(skylineSet.size()>0 && skylineSet.get(k)[1]!=skyline.get(j)[1]){
				skylineSet.add(skyline.get(j));
				k++;
			}
		}
		for(int i=0;i<skylineSet.size();i++){
			int tempArr[] = skylineSet.get(i);
			System.out.print("{");
			System.out.print(Arrays.toString(tempArr));
			System.out.print("}");
		}

	}

}
