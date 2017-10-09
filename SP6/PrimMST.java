/**  Short Project 6: Program to compute MST utilizing Priority Queue with edges in Prim's Algorithm 
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */
package cs6301.g44.SP6;

import java.util.Scanner;

import cs6301.g44.SP6.GraphAlgorithm;
import cs6301.g44.SP6.Graph.Edge;
import cs6301.g44.SP6.Graph.Vertex;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.PriorityQueue;

public class PrimMST extends GraphAlgorithm<PrimMST.PrimVertex> {
	static final int Infinity = Integer.MAX_VALUE;

	class PrimVertex {
		boolean seen;
		Vertex parent;

		PrimVertex(Vertex u) {
			seen = false;
			parent = null;
		}

	}

	public PrimMST(Graph g) {
		super(g);
		node = new PrimVertex[g.size()];
		for (Vertex u : g) {
			node[u.name] = new PrimVertex(u);
		}
	}

	// public int compareTo(Edge other) {
	// return this.;
	// }

	public int prim1(Vertex s) {
		PrimVertex sV = getVertex(s);
		int wmst = 0;
		sV.seen = true;

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// SP6.Q4: Prim's algorithm using PriorityQueue<Edge>:
		for (Edge e : s)
			pq.add(e);
		System.out.println("MST");
		while (!pq.isEmpty()) {
			Edge e = pq.remove();
			PrimVertex eTo = getVertex(e.to);
			PrimVertex eFrom = getVertex(e.from);
			if (eTo.seen && eFrom.seen)
				continue;
			else if (eFrom.seen) {
				eTo.seen = true;
				eTo.parent = e.from;
				wmst += e.weight;
				System.out.print(e.weight + " ");
				for (Edge e2 : e.to) {
					PrimVertex ePv = getVertex(e2.otherEnd(e.to));
					if (!ePv.seen)
						pq.add(e2);
				}
			} else if (eTo.seen) {
				eFrom.seen = true;
				eFrom.parent = e.to;
				wmst += e.weight;
				System.out.print(e.weight + " ");
				for (Edge e2 : e.to) {
					PrimVertex ePv = getVertex(e2.otherEnd(e.to));
					if (!ePv.seen)
						pq.add(e2);
				}
			}

		}

		return wmst;
	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner in;

		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		Graph g = Graph.readGraph(in);
		Graph.Vertex s = g.getVertex(1);

		Timer timer = new Timer();
		PrimMST mst = new PrimMST(g);
		int wmst = mst.prim1(s);
		timer.end();
		System.out.println(wmst);
	}
}
