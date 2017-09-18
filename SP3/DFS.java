package cs6301.g44;


import java.util.List;

import cs6301.g44.Graph.Edge;
import cs6301.g44.Graph.Vertex;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {

	public static final int INFINITY = Integer.MAX_VALUE;

	// Class to store information about a vertex in this algorithm
	public static class DFSVertex {
		boolean seen;
		int discoveryTime;
		Graph.Vertex parent;
		int low;
		boolean cutVertex;

		DFSVertex(Graph.Vertex u) {
			discoveryTime = INFINITY;
			seen = false;
			parent = null;
			cutVertex = false;
		}

	}

	DFS(Graph g) {
		super(g);
		node = new DFSVertex[g.size()];
		for (Graph.Vertex u : g) {
			node[u.getName()] = new DFSVertex(u);
		}
	}

	public List<Edge> dfsVisit(Vertex u, int time, List<Edge> bridges) {

		DFSVertex du = getVertex(u);
		int children = 0;
		du.seen = true;
		du.discoveryTime = du.low = ++time;

		for (Edge e : u.adj) {
			Vertex v = e.otherEnd(u);
			DFS.DFSVertex dv = getVertex(v);
			if (!dv.seen) {
				children++;
				dv.parent = u;
				dfsVisit(e.to, time, bridges);
				//Checking if the anyone of the child has backEdges and updating the low accordingly.
				du.low = Math.min(du.low, dv.low);
				
				//Condition to check if the edge from the current node to child node is a bridge.
				if (dv.low > du.discoveryTime)
					bridges.add(e);

			} else if (v != du.parent) {
				du.low = Math.min(dv.discoveryTime, du.low);
			}
			// Marking a node as cut vertex if it satisfies Rule one, i.e, if the current node is a root node. 
			if (du.parent == null && children > 1) {
				du.cutVertex = true;
			}
			// If the current node is not a root node checking if it is a cutVertex based on whether anyone of its children have a back edge. 
			if (du.parent != null && dv.low >= du.discoveryTime) {
				du.cutVertex = true;
			}
		}
		return bridges;

	}

	//Helper Function that returns if a given vertex is seen or not.
	boolean seen(Graph.Vertex u) {
		return getVertex(u).seen;
	}

}
