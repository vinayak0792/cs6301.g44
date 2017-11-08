package cs6301.g44;
import cs6301.g00.Graph;
import cs6301.g00.Graph.Vertex;
import cs6301.g00.Graph.Edge;

import java.util.List;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;

public class LP4 {
    Graph g;
    Vertex s;

    public static long count=0; //number of topological orders
    
    // common constructor for all parts of LP4: g is graph, s is source vertex
    public LP4(Graph g, Vertex s) {
	this.g = g;
	this.s = s;
    }


    // Part a. Return number of topological orders of g
    public long countTopologicalOrders() {
    	boolean[] visited=new boolean[g.n];
    	int res=0;
    	getcountTopologicalSorts(visited,res);
	return count;
    }

    public void getcountTopologicalSorts(boolean[] visited,int res){
		boolean flag=false; // to find whether all topological sorts are checked or not
		
		for(Vertex x:g.v){
			if(x.inDegree==0 && !x.visited){
				for(Edge ad:x.adj){
					ad.otherEnd(x).inDegree--;
				}
				
				res++;
				x.visited=true;
				getcountTopologicalSorts(visited, res);
				
				x.visited=false;
				res--;
				
				for(Edge ad:x.adj){
					ad.otherEnd(x).inDegree++;
				}
				flag=true;
			}
		}
		if(!flag){
			if(res==g.n)
			{
				count++;
			}
		}
	}
    
    // Part b. Print all topological orders of g, one per line, and 
    //	return number of topological orders of g
    public long enumerateTopologicalOrders() {
	// To do
    	boolean[] visited=new boolean[g.n];
		ArrayDeque<Vertex> result=new ArrayDeque<>();
		count=0;
		enumTopOrder(result, visited);
        return count;
    }

    
    public void enumTopOrder(ArrayDeque<Vertex> result, boolean[] visited){
		boolean flag=false; // to find whether all topoligcal sorts are checked or not
		
		for(Vertex x:g.v){
			if(x.inDegree==0 && !x.visited){
				for(Edge ad:x.adj){
					ad.otherEnd(x).inDegree--;
				}
				
				result.addLast(x);
				x.visited=true;
				enumTopOrder(result, visited);
				
				x.visited=false;
				result.removeLast();
				
				for(Edge ad:x.adj){
					ad.otherEnd(x).inDegree++;
				}
				
				flag=true;
			}
		}
		
		if(!flag){
			if(result.size()==g.n)
			{
				Iterator<Vertex> it=result.descendingIterator();
				while(it.hasNext())
					System.out.print(" "+it.next());
				System.out.println();
				count++;
			}
			
		}
	}
    
    

    // Part c. Return the number of shortest paths from s to t
    // 	Return -1 if the graph has a negative or zero cycle
    public long countShortestPaths(Vertex t) {
	// To do
	return 0;
    }

    
    // Part d. Print all shortest paths from s to t, one per line, and 
    //	return number of shortest paths from s to t.
    //	Return -1 if the graph has a negative or zero cycle.
    public long enumerateShortestPaths(Vertex t) {
        // To do
        return 0;
    }


    // Part e. Return weight of shortest path from s to t using at most k edges
    public int constrainedShortestPath(Vertex t, int k) {
	// To do
    	int[] distance=new int[g.n];
    	for(int i=0;i<distance.length;i++)
    		distance[i]=Integer.MAX_VALUE;
    	distance[s.getName()]=0;
    	for(Vertex x:g.v){
    		x.distance=Integer.MAX_VALUE;
    		x.parent=null;
    	}
    	g.getVertex(s.getName()+1).distance=0;
    	//s.distance=0;
    	for(int i=1;i<=k;i++){
    		for(Vertex x:g.v){
    			for(Edge e:x.adj){
    				Vertex v=e.otherEnd(x);
    				if((x.distance!=Integer.MAX_VALUE) && v.distance>x.distance+e.weight){
    					distance[v.getName()]=x.distance+e.weight;
    					v.parent=x;
    					//e.otherEnd(x).distance=x.distance+e.weight;
    					//e.otherEnd(x).parent=x;
    				}
    			}
    		}
    		
    		for(int j=0;j<distance.length;j++)
    			g.getVertex(j+1).distance=distance[j];
    		
    	}
	return g.getVertex(t.getName()+1).distance;
    }


    // Part f. Reward collection problem
    // Reward for vertices is passed as a parameter in a hash map
    // tour is empty list passed as a parameter, for output tour
    // Return total reward for tour
    public int reward(HashMap<Vertex,Integer> vertexRewardMap, List<Vertex> tour) {
	// To do
        return 0;
    }

    // Do not modify this function
    static void printGraph(Graph g, HashMap<Vertex,Integer> map, Vertex s, Vertex t, int limit) {
	System.out.println("Input graph:");
	for(Vertex u: g) {
	    if(map != null) { 
		System.out.print(u + "($" + map.get(u) + ")\t: ");
	    } else {
		System.out.print(u + "\t: ");
	    }
	    for(Edge e: u) {
		System.out.print(e + "[" + e.weight + "] ");
	    }
	    System.out.println();
	}
	if(s != null) { System.out.println("Source: " + s); }
	if(t != null) { System.out.println("Target: " + t); }
	if(limit > 0) { System.out.println("Limit: " + limit + " edges"); }
	System.out.println("___________________________________");
    }
}
