/**  Short Project 6: Program to compute MST utilizing Priority Queue with edges in Prim's Algorithm 
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */
package cs6301.g44;

import java.util.Scanner;

import cs6301.g44.Graph.Edge;
import cs6301.g44.Graph.Vertex;

import java.lang.Comparable;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrimMST {
    static final int Infinity = Integer.MAX_VALUE;
    Graph g;
    
    public PrimMST(Graph g) {
    	this.g = g;
    }
    
//    public int compareTo(Edge other) {
//    	return this.;
//    }

    public int prim1(Graph.Vertex s) {
        int wmst = 0;
        for(Graph.Vertex u:g.v) {
        	u.seen = false;
        	u.parent = null;
        }
        s.seen = true;
        	
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // SP6.Q4: Prim's algorithm using PriorityQueue<Edge>:
        for(Edge e: s)
        	pq.add(e);
        while(!pq.isEmpty()) {
        	Edge e = pq.remove();
        	if(e.to.seen && e.from.seen)
        		continue;
        	else if(e.from.seen) {
        		e.to.seen = true;
            	e.to.parent = e.from;
            	wmst += e.weight;
            	//System.out.print(e.weight+" ");
            	for(Edge e2:e.to) {
            		if(!e2.otherEnd(e.to).seen)
            			pq.add(e2);
            	}
        	}
        	else if(e.to.seen) {
        		e.from.seen = true;
            	e.from.parent = e.to;
            	wmst += e.weight;
            	//System.out.print(e.weight+" ");
            	for(Edge e2:e.from) {
            		if(!e2.otherEnd(e.from).seen)
            			pq.add(e2);
            	}
        	}
        	
        	
        }
        

        return wmst;
    }

//    public int prim2(Graph.Vertex s) {
//        int wmst = 0;
//
//        // SP6.Q6: Prim's algorithm using IndexedHeap<PrimVertex>:
//
//        return wmst;
//    }

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
