package cs6301.g44;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;
import cs6301.g44.Graph.Edge;
import cs6301.g44.Graph;

public class DiameterTree {
	
	// Returns true if the graph contains a cycle, else false.
    public static Boolean checkCyclic(Graph g)
    {
    	int numVertices=g.n;
        Boolean visited[] = new Boolean[numVertices];
        for (int i = 0; i < numVertices; i++)
            visited[i] = false;
 
        // function detect cycles in different parts of graph
        for (int u = 0; u < numVertices; u++)
            if (!visited[u]) // Don't recur for u if already visited
                if (isCyclic(u, visited, -1,g))
                    return true;
 
        return false;
    }
    
    
    public static Boolean isCyclic(int v, Boolean visited[], int parent,Graph g)
    {
        // current node is marked as visited
        visited[v] = true;
        Integer i;
 
        // Recur for all the vertices adjacent to this vertex
        List<Graph.Vertex> it = getAdjacentVertices(g.getVertex(v+1));
        for(Graph.Vertex x:it)
        {
 
            if (!visited[x.name])
            {
                if (isCyclic(x.name, visited, v,g))
                    return true;
            }
 
            // If an adjacent is visited and not parent of current
            // vertex, then there is a cycle.
            else if (x.name != parent)
                return true;
        }
        return false;
    }
	
	public static void diameter(Graph.Vertex s, Graph.Vertex d, Graph g)
	{
	    // Mark all the vertices as not visited
	    boolean[] visited = new boolean[g.n];
	 
	    // Create an array to store paths
	    Graph.Vertex[] path = new Graph.Vertex[g.n];
	    int path_index = 0; // Initialize path[] as empty
	 
	    // Initialize all vertices as not visited
	    for (int i = 0; i < g.n; i++)
	        visited[i] = false;
	 
	    // Call the recursive helper function to print all paths
	    getPath(s, d, visited, path, path_index,g);
	}
	
	public static void getPath(Graph.Vertex u, Graph.Vertex d, boolean visited[],
			Graph.Vertex path[], int path_index,Graph g)
	{
		// Mark the current node and store it in path[]
		visited[u.name] = true;
		path[path_index] = u;
		path_index++;

		// If current vertex is same as destination, then print
		// current path[]
		if (u.name == d.name)
		{
			for (int i = 0; i<path_index; i++)
				System.out.print(" "+path[i]);
				System.out.println();
		}
		else // If current vertex is not destination
		{
			// Recur for all the vertices adjacent to current vertex
			List<Graph.Vertex> adj= getAdjacentVertices(g.getVertex(u.name+1));
			for(Graph.Vertex x:adj)
				if(!visited[x.name])
					getPath(x, d, visited, path, path_index,g);
		}
		// Remove current vertex from path[] and mark it as unvisited
		path_index--;
		visited[u.name] = false;
	}
	
	public static List<Graph.Vertex> getAdjacentVertices(Graph.Vertex u)
	{
		List<Graph.Vertex> adj=new ArrayList<Graph.Vertex>();
		for(Graph.Edge e:u.adj)
		{
			adj.add(e.otherEnd(u));
		}
		//adj.remove(u);
		return adj;
	}
	
	
	public static Graph.Vertex BFS(Graph.Vertex s,Graph g)
    {
        // Mark all the vertices as not visited
    	Graph.Vertex temp;
        boolean visited[] = new boolean[g.n];
 
        // Create a queue for BFS
        LinkedList<Graph.Vertex> queue = new LinkedList<Graph.Vertex>();
 
        // Mark the current node as visited and enqueue it
        visited[s.name]=true;
        queue.add(s);
        temp=s;
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            temp=s;
            Iterator<Edge> i = s.iterator();
            while (i.hasNext())
            {
                Graph.Edge n = i.next();
                if (!visited[n.to.name])
                {
                    visited[n.to.name] = true;
                    queue.add(n.to);
                }
                
                if (!visited[n.from.name])
                {
                    visited[n.from.name] = true;
                    queue.add(n.from);
                }
                
            }
        }
        return temp;
    }
	
	
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.println("Enter number of vertices followed by number of edges \n"
				+ "For each edge enter source, destination and weight of the edge");
		
//		Check for cycle in graph
		Graph g = Graph.readGraph(in,false);
		if(checkCyclic(g))
		{
			System.out.println("Graph contains a cycle");
			System.exit(0);
		}
		
//		Start BFS from a arbitrary vertex 
		Graph.Vertex n=BFS(g.getVertex(1),g);
		
//		Running BFS again
		Graph.Vertex x=BFS(g.getVertex(n.name+1),g);
		
//		Diameter of the tree
		System.out.println("Diameter of tree is: ");
		diameter(g.getVertex(n.name+1), g.getVertex(x.name+1), g);
			
	}

}
