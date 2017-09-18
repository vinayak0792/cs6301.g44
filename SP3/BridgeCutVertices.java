package cs6301.g44;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import cs6301.g44.Graph;
import cs6301.g44.Graph.Edge;

public class BridgeCutVertices {

	List<Graph.Edge> findBridgeCut(Graph g) {

		DFSBridges g1 = new DFSBridges(g);
		int time = 0;
		Iterator it = g.iterator();
		List<Graph.Edge> bridges = new ArrayList<Graph.Edge>();

		while (it.hasNext()) {
			Graph.Vertex u = (Graph.Vertex) it.next();
			if (!g1.seen(u)) {
				g1.dfsVisit(u, time, bridges);
			}
		}

		return bridges;

	}

	public static void main(String[] args) throws FileNotFoundException, Exception {

		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}
		System.out.println("Enter the number of vertices and edges");
		System.out.println("Enter the source vertex, destination vertex and the weight");
		Graph g = Graph.readGraph(in);
		BridgeCutVertices bc = new BridgeCutVertices();
		List<Edge> bridges = bc.findBridgeCut(g);

		for (Edge e : bridges)
			System.out.println(e.from + " " + e.to);

	}

}
