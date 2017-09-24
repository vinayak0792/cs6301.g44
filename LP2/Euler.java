/** Euler Class which computes the sub tours, euler tour and also checks for SCC and Eulerian graphs
/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */

// change following line to your group number
package cs6301.g44;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs6301.g44.Graph;
import cs6301.g44.Graph.Edge;
import cs6301.g44.Graph.Vertex;
import cs6301.g44.StronglyConnectComp;

public class Euler {
	int VERBOSE;
	List<Graph.Edge> tour;
	Graph.Vertex start;

	// Constructor
	Euler(Graph g, Graph.Vertex start) {
		VERBOSE = 1;
		tour = new LinkedList<>();
		this.start = start;
	}
	
	// Find tours starting at vertices with unexplored edges
	public static void findTours(Vertex u, List<Edge> tour, Iterator<Edge> it) {
		// Iterator<Edge> it = u.adj.iterator();
		Edge e;
		u.subTour = true;
		while (it.hasNext()) {
			e = it.next();
			if (!e.visited) {
				tour.add(e);
				e.visited = true;
				Vertex v = e.otherEnd(e.from);
				v.outgoing++; 
				it = v.adj.listIterator(v.outgoing-1);
			}
		}
	}

	// To do: function to find an Euler tour
	public List<Graph.Edge> findEulerTour(Graph g) {
		// Finding all the subTours for the start vertex
		start.vertexTour = new ArrayList<Edge>();
		Iterator<Edge> it = start.adj.iterator();
		findTours(start, start.vertexTour, it);

		// Checking if there exists another vertex V with unexplored edges
		// If any then we find subTours for that edge
		Iterator<Vertex> itVertex = g.iterator();
		while (itVertex.hasNext()) {
			Vertex v = itVertex.next();
			if (v.outgoing != v.adj.size()) {
				it = v.adj.listIterator(v.outgoing);
				//it = v.adj.iterator();
				v.vertexTour = new ArrayList<>();
				findTours(v, v.vertexTour, it);
			}
		}
		if (VERBOSE > 9) {
			printTours(g);
		}
		stitchTours(g, tour);
		return tour;
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
		return true;
	}

	void printTours(Graph g) {
		printTourUtil(start);
		Iterator<Vertex> it = g.iterator();
		while (it.hasNext()) {
			Vertex v = it.next();
			if (v.subTour && v != start) {
				printTourUtil(v);
			}
		}
	}

	// Helper function which print the sub tours of the respective vertices in
	// the specified format
	void printTourUtil(Vertex u) {
		System.out.print((u.name + 1) + ": ");
		Iterator<Edge> itEdge = u.vertexTour.iterator();
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
		Graph.Vertex temp = u;
		temp.subTour = false;
		for (Edge e : temp.vertexTour) {
			eulerTour.add(e);
			temp = e.otherEnd(temp);
			if (temp.subTour) {
				temp.subTour = false;
				explore(temp, eulerTour);

			}
		}
	}

	void setVerbose(int v) {
		VERBOSE = v;
	}
}
