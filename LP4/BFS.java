package cs6301.g44;
import java.util.Queue;
import java.util.LinkedList;



public class BFS extends GraphAlgorithm<BFS.BFSVertex> {
    public static final int INFINITY = Integer.MAX_VALUE;
    // Class to store information about a vertex in this algorithm
    static class BFSVertex {
	boolean seen;
	Graph.Vertex BFSparent;
	int distance;  // distance of vertex from source
	BFSVertex(Graph.Vertex u) {
	    seen = false;
	    BFSparent = null;
	    distance = INFINITY;
	}
    }

    Graph.Vertex src;

    public BFS(Graph g, Graph.Vertex src) {
	super(g);
	this.src = src;
	node = new BFSVertex[g.size()];
	// Create array for storing vertex properties
	for(Graph.Vertex u: g) {
	    node[u.getName()] = new BFSVertex(u);
	}
	// Set source to be at distance 0
	getVertex(src).distance = 0;
    }

    // reinitialize allows running BFS many times, with different sources
    void reinitialize(Graph.Vertex newSource) {
	src = newSource;
	for(Graph.Vertex u: g) {
	    BFSVertex bu = getVertex(u);
	    bu.seen = false;
	    bu.BFSparent = null;
	    bu.distance = INFINITY;
	}
	getVertex(src).distance = 0;
    }

    void bfs() {
	Queue<Graph.Vertex> q = new LinkedList<>();
	q.add(src);
	while(!q.isEmpty()) {
	    Graph.Vertex u = q.remove();
	    for(Graph.Edge e: u) {
		Graph.Vertex v = e.otherEnd(u);
		if(!seen(v)) {
		    visit(u,v);
		    q.add(v);
		}
	    }
	}
    }
    
    boolean seen(Graph.Vertex u) {
	return getVertex(u).seen;
    }

    Graph.Vertex getParent(Graph.Vertex u) {
	return getVertex(u).BFSparent;
    }

    int distance(Graph.Vertex u) {
	return getVertex(u).distance;
    }


    // Visit a node v from u
    void visit(Graph.Vertex u, Graph.Vertex v) {
	BFSVertex bv = getVertex(v);
	bv.seen = true;
	bv.BFSparent = u;
	bv.distance = distance(u) + 1;
    }
}
