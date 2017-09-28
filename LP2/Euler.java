/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
package cs6301.g44.Lp2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs6301.g44.Lp2.Graph;
import cs6301.g44.Lp2.Graph.Edge;
import cs6301.g44.Lp2.Graph.Vertex;

public class Euler extends GraphAlgorithm<Euler.EulerVertex> {
	int VERBOSE;
	List<Graph.Edge> tour;
	Graph.Vertex start;

	// Making use of the Graph Algorithm to implement Euler Vertex which has all
	// the necessary variables
	/**
	 * EulerVertex class
	 * 
	 * @param subTour    : Boolean - True if vertex has a sub tour. 
	 * 		  vertexTour : ArrayList - Stores the sub tour if present for a vertex 
	 *        itEdge     : Iterator - To itearate over the outgoing edge list of a vertex
	 */
	class EulerVertex {
		Iterator<Edge> itEdge;
		boolean subTour;
		List<Edge> vertexTour;

		/**
		 * 
		 */
		public EulerVertex(Graph.Vertex u) {
			itEdge = u.adj.iterator();
			subTour = false;

		}

	}

	public Euler(Graph g, Graph.Vertex start) {
		super(g);
		node = new EulerVertex[g.size()];
		for (Graph.Vertex u : g) {
			node[u.name] = new EulerVertex(u);
		}
		VERBOSE = 1;
		tour = new LinkedList<Graph.Edge>();
		this.start = start;
	}

	boolean isEulerian(Graph g) {
		// Using the same code as in SP3 to check if there are SCC.
		StronglyConnectComp strongConnect = new StronglyConnectComp();
		if (strongConnect.stronglyConnectedComponents(g) == 1) {
			Iterator<Graph.Vertex> vertexIterator = g.iterator();
			// Checking for Eulerian graph
			while (vertexIterator.hasNext()) {
				Graph.Vertex ver = vertexIterator.next();
				// Check for each vertex in-degree is equal to out-degree
				if (ver.adj.size() != ver.revAdj.size()) {
					System.out.println("inDegree = " + ver.revAdj.size() + ", outDegree = " + ver.adj.size()
							+ " at Vertex " + (ver.name + 1));
					return false;
				}

			}
		} else {
			System.out.println("Graph is not strongly connected");
			return false;

		}
		System.out.println("Done with eulerian");
		return true;
	}

	public List<Graph.Edge> findEulerTour(Graph g) {
		// Finding all the subTours for the start vertex
		EulerVertex eV = getVertex(start);
		eV.vertexTour = new ArrayList<Edge>();
		findTours(start, eV.vertexTour);

		// Checking if there exists another vertex V with unexplored edges
		// If any then we find subTours for that edge
		Iterator<Vertex> itVertex = g.iterator();
		while (itVertex.hasNext()) {
			Vertex v = itVertex.next();
			EulerVertex eU = getVertex(v);
			if (eU.itEdge.hasNext() && eU != eV) {
				eU.vertexTour = new ArrayList<Edge>();
				findTours(v, eU.vertexTour);
			}
		}
		if (VERBOSE > 9) {
			printTours(g);
		}
		stitchTours(g, tour);
		return tour;
	}

	public void findTours(Vertex u, List<Edge> tour) {
		Edge e;
		EulerVertex eV = getVertex(u);
		eV.subTour = true;
		Iterator<Edge> it = eV.itEdge;
		while (it.hasNext()) {
			e = it.next();
			tour.add(e);
			Vertex v = e.otherEnd(e.from);
			it = getVertex(v).itEdge;

		}
	}

	void printTours(Graph g) {
		printTourUtil(start);
		Iterator<Vertex> it = g.iterator();
		while (it.hasNext()) {
			Vertex v = it.next();
			EulerVertex eV = getVertex(v);
			if (eV.subTour && v != start) {
				printTourUtil(v);
			}
		}
	}

	// Helper function which print the sub tours of the respective vertices in
	// the specified format
	void printTourUtil(Vertex u) {
		EulerVertex eU = getVertex(u);
		System.out.print((u.name + 1) + ": ");
		Iterator<Edge> itEdge = eU.vertexTour.iterator();
		while (itEdge.hasNext()) {
			System.out.print(itEdge.next());
		}
		System.out.println();

	}

	// Stitch tours into a single tour using the algorithm discussed in class
	void stitchTours(Graph g, List<Edge> eulerTour) {
		explore(start, eulerTour);
	}

	// Helper method that recursively iterates over all the sub tours if any and
	// stitches them into one tour.
	void explore(Graph.Vertex u, List<Edge> eulerTour) {
		EulerVertex temp = getVertex(u);
		temp.subTour = false;
		Iterator<Edge> itEdge = temp.vertexTour.iterator();
		while (itEdge.hasNext()) {
			Edge e = itEdge.next();
			eulerTour.add(e);
			Vertex v1 = e.otherEnd(e.from);
			temp = getVertex(v1);
			if (temp.subTour) {
				temp.subTour = false;
				explore(v1, eulerTour);

			}
		}
	}

	void setVerbose(int v) {
		VERBOSE = v;
	}

}
