/** StronglyConnectComp class which checks if a given graph is SCC or not
/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
package cs6301.g44.Lp2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

public class StronglyConnectComp {

	int stronglyConnectedComponents(Graph graph) {
		Graph.Vertex src = graph.getVertex(1); // DFS starts from vertex 1
		ArrayDeque<Graph.Vertex> stack = new ArrayDeque<>();
		boolean[] visited = new boolean[graph.size()];
		ArrayList<ArrayList<Graph.Vertex>> listOfComp = new ArrayList<>();
		dfs(src, visited, stack);
		int count = 0;

		for (int i = 0; i < graph.size(); i++)
			visited[i] = false;

		Graph.Vertex ver; // For each element from stack run DFS for transpose
							// graph
		while (!stack.isEmpty()) { // to get strongly connected components
			ArrayList<Graph.Vertex> list = new ArrayList<>();
			ver = stack.pop();
			if (visited[ver.getName()])
				continue;
			dfsReverse(ver, visited, list);
			listOfComp.add(list); // Add all individual components to a list
		}
		for (ArrayList<Graph.Vertex> comp : listOfComp) {
			count++;
		}
		return count;
	}

	// DFS for transpose graph
	static void dfsReverse(Graph.Vertex src, boolean[] visited, ArrayList<Graph.Vertex> list) {
		visited[src.getName()] = true;
		list.add(src);
		Iterator<Graph.Edge> itr = src.revAdj.iterator(); // Using revAdj to get
															// transpose of a
															// graph
		Graph.Vertex vertex;
		while (itr.hasNext()) {
			vertex = itr.next().otherEnd(src);
			if (visited[vertex.getName()])
				continue;
			dfsReverse(vertex, visited, list);
		}
	}

	// DFS traversal
	static void dfs(Graph.Vertex src, boolean[] visited, ArrayDeque<Graph.Vertex> stack) {
		visited[src.getName()] = true;
		Iterator<Graph.Edge> itr = src.iterator();
		Graph.Vertex nextNode;
		while (itr.hasNext()) {
			nextNode = itr.next().otherEnd(src);
			if (!visited[nextNode.getName()]) {
				dfs(nextNode, visited, stack);
			}

		}
		stack.offerFirst(src); // Insert elements in the front of ArrayDeque
	} // to get elements in the decreasing order of finish time
}