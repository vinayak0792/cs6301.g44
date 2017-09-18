package cs6301.g44;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import cs6301.g44.Graph;
import cs6301.g44.Graph.Vertex;

public class TopologicalOrder {
	
	public static LinkedList<Graph.Vertex> toplogicalOrder1(Graph g)
	{
		int topNum=0;
		Graph.Vertex v;
		ArrayDeque<Graph.Vertex> q=new ArrayDeque<>();
		LinkedList<Graph.Vertex> topList=new LinkedList<>();
		for(Graph.Vertex u:g.v)
		{
			u.inDegree=u.revAdj.size();
			if(u.inDegree==0)
				q.add(u);
		}	
			while(!q.isEmpty())
			{
				 v=q.remove();
				 v.top=++topNum;
				 topList.add(v);
				 for(Graph.Edge e:v.adj)
				 {
					 Graph.Vertex ad=e.otherEnd(v);
					 ad.inDegree--;
					 if(ad.inDegree==0)
						 q.add(ad);
				 }
			}
			if(topNum!=g.n)
			{
				System.out.println("not a dag");
				System.exit(0);
			}
			return topList;
						
	}
	
	public static LinkedList<Graph.Vertex> toplogicalOrder2(Graph g)
	{
		Iterator<Graph.Vertex> it=g.iterator();
		LinkedList<Graph.Vertex> decList= DFS(it,g);
		return decList;
	}
	
	public static LinkedList<Graph.Vertex> DFS(Iterator<Graph.Vertex> it,Graph g)
	{
		int topNum=g.n;
		int time=0;
		int cno=0;
		LinkedList<Graph.Vertex> decList=new LinkedList<>();
		for(Graph.Vertex u:g.v)
			u.seen=false;
		
		while(it.hasNext())
		{
			Graph.Vertex u=it.next();
			if(!u.seen)
			{
				cno++;
				DFSVisit(u,decList,time,cno,topNum);
			}
		}
		return decList;
		
	}
	
	public static void DFSVisit(Graph.Vertex u,LinkedList<Graph.Vertex> decList,int time,int cno,int topNum)
	{
		u.seen=true;
		u.dis=++time;
		u.cno=cno;
		for(Graph.Edge e:u.adj)
		 {
			 Graph.Vertex v=e.otherEnd(u);
			 if(!v.seen)
			 {
				 v.parent=u;
				 DFSVisit(v, decList, time, v.cno,topNum);
			 }
		 
		 }		 
		 u.fin=++time;	 
		 u.fin=topNum--;
		 decList.addFirst(u);
		
		
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}
		
		Graph g = Graph.readGraph(in, true);
		
		LinkedList<Graph.Vertex> topList=toplogicalOrder1(g);
		System.out.println("Topological Order 1");
		for(Graph.Vertex v:topList)
		{
			System.out.print(" "+v);
		}
		
		LinkedList<Graph.Vertex> topList2=toplogicalOrder2(g);
		System.out.println("\nTopological Order 2");
		for(Graph.Vertex v:topList2)
		{
			System.out.print(" "+v);
		}
	}

}
