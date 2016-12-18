package com.saptar.gfg.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinimumEdgeReverse {

	public static int pathChanges = 0;
	public static int result = Integer.MAX_VALUE - 1;

	public static class Vertex {
		private int vertexId;
		private Boolean visited;

		Vertex(int index) {
			this.vertexId = index;
			this.visited = false;
		}

		public Vertex() {
		};

		public int getVertexId() {
			return vertexId;
		}

		public void setVertexId(int vertexId) {
			this.vertexId = vertexId;
		}

		public Boolean getVisited() {
			return visited;
		}

		public void setVisited(Boolean visited) {
			this.visited = visited;
		}
	}

	public static class Edge {
		private String edgeId;
		private Vertex startVertex;
		private Vertex endVertex;

		public Edge(String edge, Vertex start, Vertex end) {
			this.edgeId = edge;
			this.startVertex = start;
			this.endVertex = end;
		}

		public String getEdgeId() {
			return edgeId;
		}

		public void setEdgeId(String edgeId) {
			this.edgeId = edgeId;
		}

		public Vertex getStartVertex() {
			return startVertex;
		}

		public void setStartVertex(Vertex startVertex) {
			this.startVertex = startVertex;
		}

		public Vertex getEndVertex() {
			return endVertex;
		}

		public void setEndVertex(Vertex endVertex) {
			this.endVertex = endVertex;
		}

	}

	public static void main(String[] args) {
		List<Vertex> vertices = new ArrayList<Vertex>();
		List<Edge> edges = new ArrayList<Edge>();
		// driver program to add vertices and make necessary connection
		int no_of_vertex = 7;
		for (int i = 0; i < no_of_vertex; i++) {
			MinimumEdgeReverse.Vertex v = new MinimumEdgeReverse.Vertex(i);
			vertices.add(v);
		}
		// establish connections
		MinimumEdgeReverse.Edge e1 = new MinimumEdgeReverse.Edge("Edge_1",
				vertices.get(0), vertices.get(1));
		MinimumEdgeReverse.Edge e2 = new MinimumEdgeReverse.Edge("Edge_2",
				vertices.get(2), vertices.get(1));
		MinimumEdgeReverse.Edge e3 = new MinimumEdgeReverse.Edge("Edge_3",
				vertices.get(2), vertices.get(3));
		MinimumEdgeReverse.Edge e4 = new MinimumEdgeReverse.Edge("Edge_4",
				vertices.get(6), vertices.get(3));
		MinimumEdgeReverse.Edge e5 = new MinimumEdgeReverse.Edge("Edge_5",
				vertices.get(6), vertices.get(4));
		MinimumEdgeReverse.Edge e6 = new MinimumEdgeReverse.Edge("Edge_6",
				vertices.get(4), vertices.get(5));
		MinimumEdgeReverse.Edge e7 = new MinimumEdgeReverse.Edge("Edge_6",
				vertices.get(5), vertices.get(1));
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		edges.add(e5);
		edges.add(e6);
		edges.add(e7);
		minimumEdgeReverse(vertices, edges, vertices.get(0), vertices.get(6));
		System.out.println("Minimum number of edge reverse required is "
				+ result);
	}

	private static void minimumEdgeReverse(List<Vertex> vertices,
			List<Edge> edges, Vertex start, Vertex end) {
		Stack<Vertex> covered = new Stack<MinimumEdgeReverse.Vertex>();
		// beginning with start vertex
		// check if the vertex has been visited
		if (!start.getVisited()) {
			// if not visited
			// visit this node and put it in covered
			start.setVisited(true);
			tracePath(start, end, edges, covered);

			// find the next next nodes to visit

		}
	}

	private static void tracePath(Vertex start, Vertex end, List<Edge> edges,
			Stack<Vertex> covered) {
		List<Vertex> adjacentVertices = new ArrayList<MinimumEdgeReverse.Vertex>();

		Vertex nextVertex = new Vertex(), previousVertex = new Vertex();

		adjacentVertices = getAdjacentVertices(start, edges);
		if (!adjacentVertices.isEmpty()) {
			previousVertex = start;
		}
		Vertex adjV = new Vertex();
		for (Vertex v : adjacentVertices) {
			if (!v.getVisited()) {
				adjV = v;
				break;
			}
			adjV = null;
		}
		// select the vertex which is not visited
		if (adjV != null && !adjV.getVisited()
				&& adjV.getVertexId() != end.vertexId) {
			// add the vertex in the stack and put it in the next vertex
			nextVertex = adjV;
			adjV.setVisited(true);
			// covered.add(nextVertex);
			covered.add(start);
			// check for path change.
			if (pathChanged(previousVertex, nextVertex, edges)) {
				pathChanges++;
			}
			tracePath(nextVertex, end, edges, covered);

		} else if (adjV != null && adjV.getVertexId() == end.vertexId) {
			// consider this to be end of search and start popping the stack
			// get set out on alternate path.
			if (pathChanged(previousVertex, end, edges)) {
				pathChanges++;
			}
			result = (pathChanges < result) ? pathChanges : result;
			nextVertex = covered.pop();
			pathChanges = 0;
			tracePath(nextVertex, end, edges, covered);

		} else {
			if (!covered.isEmpty()) {
				nextVertex = covered.pop();

				tracePath(nextVertex, end, edges, covered);
			}
		}

	}

	private static boolean pathChanged(Vertex previousVertex,
			Vertex nextVertex, List<Edge> edges) {
		Boolean result = false;
		for (Edge e : edges) {
			if (e.startVertex.vertexId == previousVertex.vertexId
					&& e.endVertex.vertexId == nextVertex.vertexId) {
				result = false;
				break;
			} else if (e.startVertex.vertexId == nextVertex.vertexId
					&& e.endVertex.vertexId == previousVertex.vertexId) {
				result = true;
				break;
			}
		}
		return result;
	}

	private static List<Vertex> getAdjacentVertices(Vertex start,
			List<Edge> edges) {
		List<Vertex> adjVertex = new ArrayList<MinimumEdgeReverse.Vertex>();
		for (Edge e : edges) {
			if (e.startVertex.vertexId == start.vertexId) {
				// add the edges end vertex as adjacent
				adjVertex.add(e.endVertex);
			} else if (e.endVertex.vertexId == start.vertexId) {
				adjVertex.add(e.startVertex);
			}
		}

		return adjVertex;
	}
}
