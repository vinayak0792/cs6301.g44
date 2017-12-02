package cs6301.g44;
import cs6301.g44.Graph.Edge;
import cs6301.g44.Graph.Vertex;
import java.util.Scanner;
import java.util.HashMap;

public class LP8 {
    static int VERBOSE = 0;
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	Graph g = Graph.readDirectedGraph(in);
	Timer timer = new Timer();
	int s = in.nextInt();
	int t = in.nextInt();
	HashMap<Edge,Integer> capacity = new HashMap<>();
	HashMap<Edge, Integer> cost = new HashMap<>();
	for(Vertex u: g) {
	    for(Edge e: u) {
			capacity.put(e, 1);
			cost.put(e,e.getWeight());
	    }
	}
	MinCostFlow f = new MinCostFlow(g, g.getVertex(s), g.getVertex(t), capacity, cost);
	int value = f.cycleCancellingMinCostFlow(3);


	System.out.println(value);

/*	if(VERBOSE > 0) {
	    for(Vertex u: g) {
		    System.out.print(u + " : ");
		    for(Edge e: u) {
			System.out.print(e + ":" + f.flow(e) + "/" + f.capacity(e) + " | ");
		    }
		    System.out.println();
		}
	}
	System.out.println(timer.end());*/
    }
}
	