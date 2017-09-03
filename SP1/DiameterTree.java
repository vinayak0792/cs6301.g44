/**  Short Project 1: Class to find diamter of the tree 
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */

package cs6301.g44;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;
import cs6301.g44.Graph;

public class DiameterTree {

	static final int INF = Integer.MAX_VALUE;

	public static List<Graph.Vertex> getAdjacentVertices(Graph.Vertex u) {
		List<Graph.Vertex> adj = new ArrayList<Graph.Vertex>();
		for (Graph.Edge e : u.adj) {
			adj.add(e.otherEnd(u));
		}
		return adj;
	}

	// Checks if graph contains cycle or not.
	public static Boolean checkCyclic(Graph g) {
		int numVertices = g.n;
		boolean visited[] = new boolean[numVertices];

		// To detect cycles in all parts of graph
		for (int u = 0; u < numVertices; u++)
			if (!visited[u]) // Don't recur for u if already visited
				if (isCyclic(u, visited, -1, g))
					return true;

		return false;
	}
        
	//recursive function to find cycle
	public static Boolean isCyclic(int v, boolean visited[], int parent, Graph g) {
		// current node is marked as visited
		visited[v] = true;
		// Do this for all adjacent vertices
		List<Graph.Vertex> it = getAdjacentVertices(g.getVertex(v + 1));
		for (Graph.Vertex x : it) {

			if (!visited[x.name]) {
				if (isCyclic(x.name, visited, v, g))
					return true;
			}

			// If an adjacent vertex is visited and its not the parent then graph contains cycle
			else if (x.name != parent)
				return true;
		}
		return false;
	}

	// returns farthest vertex in BFS
	// parent array contains info on parent info of each node in BFS path
	public static Graph.Vertex BFS(Graph.Vertex s, Graph g, int[] parent) {
		Graph.Vertex temp; // This variable stores the farthest vertex
		boolean visited[] = new boolean[g.n];
		// Create a queue for BFS
		LinkedList<Graph.Vertex> queue = new LinkedList<Graph.Vertex>();

		// Mark the current node as visited and add it to queue
		visited[s.name] = true;
		queue.add(s);
		temp = s;
		parent[s.name] = INF;
		while (queue.size() != 0) {
			s = queue.poll();
			temp = s;

			List<Graph.Vertex> adj = getAdjacentVertices(s);
			for (Graph.Vertex x : adj) {
				if (!visited[x.name]) {
					visited[x.name] = true;
					queue.add(x);
					parent[x.name] = s.name;
				}
			}
		}
		return temp;
	}

	public static LinkedList<Graph.Vertex> diameter(Graph g) {
		int[] parent = new int[g.n];// stores parent info
		// Start BFS from a arbitrary vertex
		Graph.Vertex start = BFS(g.getVertex(1), g, parent);
		// Running BFS again
		Graph.Vertex end = BFS(g.getVertex(start.name + 1), g, parent);
		// Adding the nodes into path list
		LinkedList<Graph.Vertex> path = new LinkedList<Graph.Vertex>();

		int node = end.name, pnode = 0;
		path.add(end);
		while (pnode != INF) {
			pnode = parent[node];
			if (pnode != INF)
				path.add(g.getVertex(pnode + 1));
			node = pnode;
		}
		return path;
	}

	public static void main(String args[]) throws FileNotFoundException {
		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		System.out.println("Enter number of vertices followed by number of edges \n"
				+ "For each edge enter source, destination and weight of the edge");

		// Check for cycle in graph
		Graph g = Graph.readGraph(in, false);
		if (checkCyclic(g)) {
			System.out.println("Graph contains a cycle");
			System.exit(0);
		}

		LinkedList<Graph.Vertex> path = diameter(g);
		System.out.println("Diamter of tree");
		for (Graph.Vertex v : path) {
			System.out.print(" " + v);
		}
	}

}
