package cs6301.g44;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;

import cs6301.g44.Graph.Edge;
import cs6301.g44.Graph.Vertex;

public class countTopoligicalOrder {

	public static int count=0; //number of topological orders 
	
	public static void getTopologicalSorts(ArrayDeque<Vertex> result, boolean[] visited,Graph g){
		boolean flag=false; // to find whether all topoligcal sorts are checked or not
		
		for(Vertex x:g.v){
			if(x.inDegree==0 && !x.visited){
				for(Edge ad:x.adj){
					ad.otherEnd(x).inDegree--;
				}
				
				result.addLast(x);
				x.visited=true;
				getTopologicalSorts(result, visited, g);
				
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
				for(Vertex ver:result){
					System.out.print(" "+ver);
				}
				System.out.println();
				count++;
			}
			
		}
	}
	
	public static void baseToplogicalSort(Graph g){
		boolean[] visited=new boolean[g.n];
		ArrayDeque<Vertex> result=new ArrayDeque<>();
		getTopologicalSorts(result, visited,g);
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		System.out.println("Enter number of vertices followed by number of edges \n"
				+ "For each edge enter source, destination and weight of the edge");

		Graph g = Graph.readGraph(in, true);
		
		System.out.println("Topological orders are: ");
		baseToplogicalSort(g);
		System.out.println("Number of topological orders are: "+count);
	}
}
