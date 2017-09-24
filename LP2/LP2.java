/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
package cs6301.g44;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import cs6301.g44.Graph.Vertex;

public class LP2 {
	static int VERBOSE = 1;

	public static void main(String[] args) throws FileNotFoundException {
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
		if (args.length > 2) {
			VERBOSE = Integer.parseInt(args[2]);
		}

		Graph g = Graph.readDirectedGraph(in);
		Vertex startVertex = g.getVertex(start);

		Timer timer = new Timer();
		Euler euler = new Euler(g, startVertex); //Strongly connected & number ( incoming edge == outgoing edge) 
		euler.setVerbose(VERBOSE);
		
		// Checking if the grpah is eulerian
		boolean eulerian = euler.isEulerian(g);
		if (!eulerian) {
			return;
		}

		// finding the Euler tour
		List<Graph.Edge> tour = euler.findEulerTour(g);
		timer.end();
		if (VERBOSE > 0) {
			System.out.println("Its output (not unique):\n_________________________");
			for (Graph.Edge e : tour) {
				System.out.print(e);
			}
			System.out.println();
			System.out.println("_________________________");
		}
		System.out.println(timer);
	}
}
