/**
 * Graph Coloring 
 * Details: http://www.geeksforgeeks.org/graph-coloring-set-2-greedy-algorithm/
 */

package com.saptar.gfg.greedy;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphColoring {

	private int V;
	private LinkedList<Integer> adj[];

	public GraphColoring(int v) {
		this.V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	// add the edges
	void addEdge(int u, int v) {
		adj[u].add(v);
		adj[v].add(u);// undirected graph
	}

	// assigns colors starting from 0 to all vertices
	// and prints the assignement of color
	void greedyColoring() {
		int[] result = new int[V];
		// assign the first color to the first vertex
		result[0] = 0;
		// initialise remaining V-1 vertices as unassinged
		for (int i = 1; i < V; i++) {
			result[i] = -1;
		}
		// A temp arr to store the value of color.
		// if the value of available[cr] is true that means the
		// color is already assigned to one of the adj nodes to the node under
		// consideration.
		boolean[] available = new boolean[V];
		for (int cr = 0; cr < V; cr++) {
			available[cr] = false;
		}
		// assign color to the remaining V-1 vertices/nodes
		for (int i = 1; i < V; i++) {
			Iterator<Integer> itr = adj[i].iterator();
			while (itr.hasNext()) {
				int k = itr.next();
				if (result[k] != -1) {
					// node has been initialised and colored
					available[result[k]] = true;
				}
			}
			// find the first available color
			int cr;
			for (cr = 0; cr < V; cr++) {
				if (available[cr] == false) {
					break;
				}
			}
			result[i] = cr;
			// reset the value of the available cr back to false
			// for next iteration.
			itr = adj[i].iterator();
			while (itr.hasNext()) {
				int p = itr.next();
				if (result[p] != -1) {
					available[result[p]] = false;
				}
			}
		}
		// print the result
		for (int k = 0; k < V; k++) {
			System.out
					.println("Vertex " + k + " ----> " + "Color " + result[k]);
		}

	}

	public static void main(String[] args) {
		GraphColoring g1 = new GraphColoring(5);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(1, 2);
		g1.addEdge(1, 3);
		g1.addEdge(2, 3);
		g1.addEdge(3, 4);
		System.out.println("Coloring of graph 1");
		g1.greedyColoring();

		System.out.println();
		GraphColoring g2 = new GraphColoring(5);
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		g2.addEdge(1, 2);
		g2.addEdge(1, 4);
		g2.addEdge(2, 4);
		g2.addEdge(4, 3);
		System.out.println("Coloring of graph 2 ");
		g2.greedyColoring();

	}

}
