package cs6301.g44.Lp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import cs6301.g44.Lp2.Graph.Edge;
import cs6301.g44.Lp2.Graph.Vertex;

/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
public class EulerianGraph {
	
	Graph.Vertex start;
	/**
	 * 
	 */
	public EulerianGraph(Graph.Vertex start) {
		this.start = start;
	}
    
	public void findEulerTour(Graph g) {

		// Finding all the subTours for the start vertex
		start.vertexTour = new ArrayList<>();
		Iterator<Edge> it = start.adj.iterator();
		findTours(start, start.vertexTour, it);

		// Checking if there exists another vertex V with unexplored edges
		// If any then we find subTours for that edge
		for (Vertex v : g.v) {
			if (v.outgoing != v.adj.size()) {
				it = v.adj.listIterator(v.outgoing);
				v.vertexTour = new ArrayList<>();
				findTours(v, v.vertexTour, it);
			}
		}
	}
	
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
				it = v.adj.iterator();
			}
		}
	}
	
	public static void main(String[] args)throws FileNotFoundException {
		Scanner in;

		// Checking whether to read from file or command line
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		// Checking if a start vertex is provided or not
		int start = 1;
		if (args.length > 1) {
			start = Integer.parseInt(args[1]);
		}

		// Check for verbose to suppress the output or not

		Graph g = Graph.readDirectedGraph(in);
		Vertex startVertex = g.getVertex(start);

		Timer timer = new Timer();
		EulerianGraph euler = new EulerianGraph(startVertex);
		euler.findEulerTour(g);
		
	}
}
