package cs6301.g44;

import cs6301.g44.Graph;
import cs6301.g44.Graph.Vertex;
import cs6301.g44.Graph.Edge;

import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class LP4 {
	Graph g;
	Vertex s;

	public static long count = 0; // number of topological orders
	public static ArrayDeque<Vertex> bestPath=new ArrayDeque<>();
	public static ArrayDeque<Vertex> bfsPath=new ArrayDeque<>();

	// common constructor for all parts of LP4: g is graph, s is source vertex
	public LP4(Graph g, Vertex s) {
		this.g = g;
		this.s = s;
	}

	// Part a. Return number of topological orders of g
	public long countTopologicalOrders() {
		Vertex[] res=new Vertex[g.n];
		int index=0;
		count = 0;
		enumTopOrder(res,index,false);
		return count;
	}

	// Part b. Print all topological orders of g, one per line, and
	// return number of topological orders of g
	public long enumerateTopologicalOrders() {
		Vertex[] res=new Vertex[g.n];
		int index=0;
		count = 0;
		enumTopOrder(res,index,true);
		return count;
	}

	
	
	public void enumTopOrder(Vertex[] res,int index,boolean partb) {
		boolean flag = false; // to find whether all topological sorts are
								// checked or not

		for (Vertex x : g.v) {
			if (x.inDegree == 0 && !x.visited) {
				for (Edge ad : x.adj) {
					ad.otherEnd(x).inDegree--;
				}
				
				res[index++]=x;
				//result.addLast(x);
				x.visited = true;
				enumTopOrder(res,index,partb);

				x.visited = false;
				//result.removeLast();
				index--;
				for (Edge ad : x.adj) {
					ad.otherEnd(x).inDegree++;
				}

				flag = true;
			}
		}

		if (!flag) {
			if(partb){
			for(int i=0;i<index;i++)
				System.out.print(" "+res[i]);
			
				System.out.println();
			}
				count++;
			}

		}
	
	// Part c. Return the number of shortest paths from s to t
	// Return -1 if the graph has a negative or zero cycle
	public long countShortestPaths(Vertex t) {

		boolean nocycle = bellmanFord(s);
		if (!nocycle)
			return -1;

		for (Vertex v : g.v) {
			for (Edge e : v.adj) {
				Vertex from = e.from;
				Vertex to = e.to;
				if (to.distance != from.distance + e.weight)
					e.disabled = true;
			}
		}
		
		List<Vertex> topList=new LinkedList<>();
		topList=TopologicalOrder.toplogicalOrder2(g);
		int i=0;
		for(Vertex x:topList){
			i++;
			if(x.equals(t))
				break;
		}
		topList=topList.subList(0, i);
		for(Vertex v:g.v)
			v.topCount=0;
		
		s.topCount=1;
		long max=0;
		
		for(Vertex x:topList){
				for(Edge e:x.adj){
					if(!e.disabled){
						e.otherEnd(x).topCount=e.otherEnd(x).topCount+x.topCount;
						max=Math.max(max, e.otherEnd(x).count);
					}
				}
		}
		return t.topCount;
	}

	public boolean bellmanFord(Vertex src) {
		for (Vertex v : g.v) {
			v.distance = Integer.MAX_VALUE;
			v.parent = null;
			v.count = 0;
			v.visited = false;
		}
		src.distance = 0;
		src.visited = true;
		ArrayDeque<Vertex> q = new ArrayDeque<>();
		q.add(src);
		while (!q.isEmpty()) {
			Vertex u = q.remove();
			u.visited = false;
			u.count = u.count + 1;
			if (u.count >= g.n)
				return false;

			for (Edge e : u.adj) {
				Vertex v = e.otherEnd(u);
				if (v.distance > u.distance + e.weight) {
					v.distance = u.distance + e.weight;
					v.parent = u;
					if (!v.visited) {
						q.add(v);
						v.visited = true;
					}
				}
			}
		}

		return true;
	}

	// Part d. Print all shortest paths from s to t, one per line, and
	// return number of shortest paths from s to t.
	// Return -1 if the graph has a negative or zero cycle.
	public long enumerateShortestPaths(Vertex t) {
		boolean nocycle = bellmanFord(s);
		if (!nocycle)
			return -1;

		for (Vertex v : g.v) {
			for (Edge e : v.adj) {
				Vertex from = e.from;
				Vertex to = e.to;
				if (to.distance != from.distance + e.weight)
					e.disabled = true;
			}
		}

		for (Vertex x : g.v) {
			x.visited = false;
		}
		count = 0;
		Vertex[] path=new Vertex[g.n];
		int index=0;
		dfsPaths(s, t, path,index);
		return count;
	}

	public void dfsPaths(Vertex src, Vertex dst, Vertex[] paths,int index) {
		if (src.equals(dst)) {
			count++;
			paths[index++]=dst;
			for(int i=0;i<index;i++)
				System.out.print(paths[i]+" ");
			System.out.println();
			//paths.removeFirst();
		} else {
			src.visited = true;
			//paths.push(src);
			paths[index++]=src;
			for (Edge e : src.adj) {
				if (!e.otherEnd(src).visited && !e.disabled) {
					dfsPaths(e.otherEnd(src), dst, paths,index);
				}
			}
			src.visited = false;
			index--;
		}
	}
	
	// Part e. Return weight of shortest path from s to t using at most k edges
	public int constrainedShortestPath(Vertex t, int k) {
		
		for (Vertex x : g.v) {
			x.distance = Integer.MAX_VALUE;
			x.prevDistance=Integer.MAX_VALUE;
			x.parent = null;
		}
		g.getVertex(s.getName() + 1).distance = 0;
		g.getVertex(s.getName() + 1).prevDistance = 0;
		
		for (int i = 1; i <= k; i++) {
			for (Vertex x : g.v) {
				for (Edge e : x.adj) {
					Vertex v = e.otherEnd(x);
					if ((x.distance != Integer.MAX_VALUE) && v.distance > x.distance + e.weight) {
						// Updating prevDistance.Will update actual vertex
						// distances after completion of whole single iteration.
						if (v.prevDistance > x.distance + e.weight)
							v.prevDistance = x.distance + e.weight;
						v.parent = x;
					}
				}
			}

			// updating distances after completion of whole iteration
			for(Vertex f:g.v){
				f.distance=f.prevDistance;
			}
		}
		return g.getVertex(t.getName() + 1).distance;
	}
	
	class Bpath{
		Vertex[] bpath=new Vertex[g.n];
		int index;
		int bestReward;
		Vertex src,dst;
	}

	// Part f. Reward collection problem
	// Reward for vertices is passed as a parameter in a hash map
	// tour is empty list passed as a parameter, for output tour
	// Return total reward for tour
	public int reward(HashMap<Vertex, Integer> vertexRewardMap, List<Vertex> tour) {
		Bpath obj=new Bpath();
		
		Vertex src, dst,finalSource=null,finalDest=null;
		int bestIndex=0;
		src = this.s;
		int highestReward = 0, tempReward = 0;
		
		boolean nocycle = bellmanFord(s);
		if (!nocycle)
			return -1;
		
		for (Vertex v : g.v) {
			if (!v.equals(src)) {
				
				dst = v;
				dst.reward = 0;
				
				for(Vertex d:g.v){
					d.reward=vertexRewardMap.get(d);
				}
				
				Vertex[] temppath=new Vertex[g.n];
				int index=0;
				
				obj.src=s;
				obj.dst=dst;
				bestPath(s, dst, temppath,index,0,obj,0);
				
					tempReward=obj.bestReward;
				
				
				for (Vertex p : g.v) {
					p.disabled = false;
				}

					if (tempReward > highestReward) {
						highestReward=tempReward;
						bestPath.removeAll(bestPath);
						finalDest=obj.dst;
						finalSource=obj.src;
						for(int l=0;l<obj.index;l++){
							bestPath.add(obj.bpath[l]);
						}
						bestIndex=obj.index;
					}
				}
		}
		Vertex[] bb=new Vertex[bestIndex];
		for(int i=0;i<bestIndex;i++){
			bb[i]=bestPath.getFirst();
		}
		
		//to get final bfs path for best path
		checkAlternatePath(bb, bestIndex , finalSource, finalDest);
		for (Vertex x : bestPath) {
			tour.add(x);
		}
		
		for(Vertex d:bestPath)
			d.disabled=true;
		finalDest.disabled=false;
		finalSource.disabled=false;
		
		bfs1(finalDest);
		while(finalSource!=finalDest){
			bfsPath.add(finalSource);
			finalSource=finalSource.parent;
		}
		
		Iterator<Vertex> it =bfsPath.descendingIterator(); 
		while(it.hasNext())
			tour.add(it.next());
		
		return highestReward;
	}
	
	public void bestPath(Vertex src, Vertex dst, Vertex[] paths,int index,int maxReward,Bpath obj,int weight) {
		if (src.equals(dst) && weight==dst.distance) {
			count++;
			paths[index++]=dst;
			int tempReward=0;
			for(int i=0;i<index;i++){
				tempReward=tempReward+paths[i].reward;
			}
				if(tempReward>obj.bestReward && checkAlternatePath(paths,index,obj.src,obj.dst)){
					maxReward=tempReward;
					for(int i=0;i<index;i++){
						obj.bpath[i]=paths[i];
						obj.index=index;
						obj.bestReward=tempReward;
					}
				}
		} else {
			src.visited = true;
			paths[index++]=src;
			for (Edge e : src.adj) {
				if (!e.otherEnd(src).visited && !e.disabled) {
					if(weight<=dst.distance){
					weight=weight+e.weight;
					bestPath(e.otherEnd(src), dst, paths,index,maxReward,obj,weight);
					weight=weight-e.weight;
					}
				}
			}
			src.visited = false;
			index--;
		}
	}
	
	public boolean checkAlternatePath(Vertex[] path, int index,Vertex src, Vertex dst) {

		for (Vertex v : g.v) {
			v.disabled = false;
		}

		for(int i=0;i<index;i++)
			path[i].disabled=true;
		
		src.disabled = false;
		dst.disabled = false;
		
		bfs1(dst);
		boolean res; 
		if(src.seen)
			res=true;
		else
			res=false;
		
		return res;
	}
	
	public void bfs1(Vertex src) {
		for(Vertex v:g.v)
			v.seen=false;
		Queue<Vertex> q = new LinkedList<Vertex>();
		src.seen=true;
		q.add(src);
		Vertex temp,dec;
		while(!q.isEmpty()){
			temp=q.remove();
			if(!temp.disabled){
				for(Edge e:temp.adj){
					dec=e.otherEnd(temp);
					if(!dec.seen){
						dec.seen=true;
						q.add(dec);
						dec.parent=temp;
					}
						
				}
			}
		}
	  }

	// Do not modify this function
	static void printGraph(Graph g, HashMap<Vertex, Integer> map, Vertex s, Vertex t, int limit) {
		System.out.println("Input graph:");
		for (Vertex u : g) {
			if (map != null) {
				System.out.print(u + "($" + map.get(u) + ")\t: ");
			} else {
				System.out.print(u + "\t: ");
			}
			for (Edge e : u) {
				System.out.print(e + "[" + e.weight + "] ");
			}
			System.out.println();
		}
		if (s != null) {
			System.out.println("Source: " + s);
		}
		if (t != null) {
			System.out.println("Target: " + t);
		}
		if (limit > 0) {
			System.out.println("Limit: " + limit + " edges");
		}
		System.out.println("___________________________________");
	}

}
