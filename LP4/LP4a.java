package cs6301.g44;

import java.util.ArrayDeque;

import cs6301.g00.Graph;


public class LP4a {
	
	 static int VERBOSE = 0;
	    public static void main(String[] args) {
		if(args.length > 0) { 
			VERBOSE = Integer.parseInt(args[0]); 
			}
		Graph g = Graph.readDirectedGraph(new java.util.Scanner(System.in));
		cs6301.g00.Timer t = new cs6301.g00.Timer();
		LP4 handle = new LP4(g, null);
		long result = handle.countTopologicalOrders();
		if(VERBOSE > 0) { LP4.printGraph(g, null, null, null, 0); }
		System.out.println(result + "\n" + t.end());
	    }
}
