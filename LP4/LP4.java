package cs6301.g44;

import cs6301.g44.Graph;
import cs6301.g44.Graph.Vertex;
import cs6301.g44.Graph.Edge;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class LP4 {
	Graph g;
	Vertex s;

	public static long count = 0; // number of topological orders

	// common constructor for all parts of LP4: g is graph, s is source vertex
	public LP4(Graph g, Vertex s) {
		this.g = g;
		this.s = s;
	}

	// Part a. Return number of topological orders of g
	public long countTopologicalOrders() {
		boolean[] visited = new boolean[g.n];
		int res = 0;
		getcountTopologicalSorts(visited, res);
		return count;
	}

	public void getcountTopologicalSorts(boolean[] visited, int res) {
		boolean flag = false; // to find whether all topological sorts are
								// checked or not

		for (Vertex x : g.v) {
			if (x.inDegree == 0 && !x.visited) {
				for (Edge ad : x.adj) {
					ad.otherEnd(x).inDegree--;
				}

				res++;
				x.visited = true;
				getcountTopologicalSorts(visited, res);

				x.visited = false;
				res--;

				for (Edge ad : x.adj) {
					ad.otherEnd(x).inDegree++;
				}
				flag = true;
			}
		}
		if (!flag) {
			if (res == g.n) {
				count++;
			}
		}
	}

	// Part b. Print all topological orders of g, one per line, and
	// return number of topological orders of g
	public long enumerateTopologicalOrders() {
		// To do
		boolean[] visited = new boolean[g.n];
		ArrayDeque<Vertex> result = new ArrayDeque<>();
		count = 0;
		enumTopOrder(result, visited);
		return count;
	}

	public void enumTopOrder(ArrayDeque<Vertex> result, boolean[] visited) {
		boolean flag = false; // to find whether all topological sorts are
								// checked or not

		for (Vertex x : g.v) {
			if (x.inDegree == 0 && !x.visited) {
				for (Edge ad : x.adj) {
					ad.otherEnd(x).inDegree--;
				}

				result.addLast(x);
				x.visited = true;
				enumTopOrder(result, visited);

				x.visited = false;
				result.removeLast();

				for (Edge ad : x.adj) {
					ad.otherEnd(x).inDegree++;
				}

				flag = true;
			}
		}

		if (!flag) {
			if (result.size() == g.n) {
				Iterator<Vertex> it = result.descendingIterator();
				while (it.hasNext())
					System.out.print(" " + it.next());
				System.out.println();
				count++;
			}

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

		for (Vertex x : g.v) {
			x.visited = false;
		}

		count = 0;
		ArrayDeque<Vertex> paths = new ArrayDeque<>();
		dfsCount(s, t, paths);

		return count;
	}

	public void dfsPaths(Vertex src, Vertex dst, ArrayDeque<Vertex> paths) {
		if (src.equals(dst)) {
			count++;
			paths.addFirst(dst);
			Iterator<Vertex> it = paths.descendingIterator();
			while (it.hasNext())
				System.out.print(" " + it.next());
			System.out.println();
			paths.removeFirst();
		} else {
			src.visited = true;
			paths.push(src);

			for (Edge e : src.adj) {
				if (!e.otherEnd(src).visited && !e.disabled) {
					dfsPaths(e.otherEnd(src), dst, paths);
				}
			}
			src.visited = false;
			paths.pop();
		}
	}

	public void dfsCount(Vertex src, Vertex dst, ArrayDeque<Vertex> paths) {
		if (src.equals(dst)) {
			count++;
		} else {
			src.visited = true;
			paths.push(src);

			for (Edge e : src.adj) {
				if (!e.otherEnd(src).visited && !e.disabled) {
					dfsCount(e.otherEnd(src), dst, paths);
				}
			}
			src.visited = false;
			paths.pop();
		}
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
		// To do
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
		ArrayDeque<Vertex> paths = new ArrayDeque<>();
		dfsPaths(s, t, paths);
		return count;
	}

	// Part e. Return weight of shortest path from s to t using at most k edges
	public int constrainedShortestPath(Vertex t, int k) {
		// To do
		int[] distance = new int[g.n]; // parallel array to maintain distance of
										// each vertex
		for (int i = 0; i < distance.length; i++)
			distance[i] = Integer.MAX_VALUE;
		distance[s.getName()] = 0;
		for (Vertex x : g.v) {
			x.distance = Integer.MAX_VALUE;
			x.parent = null;
		}
		g.getVertex(s.getName() + 1).distance = 0;
		// s.distance=0;
		for (int i = 1; i <= k; i++) {
			for (Vertex x : g.v) {
				for (Edge e : x.adj) {
					Vertex v = e.otherEnd(x);
					if ((x.distance != Integer.MAX_VALUE) && v.distance > x.distance + e.weight) {
						// Updating distance array.Will update actual vertex
						// distances after completion of whole single iteration.
						if (distance[v.getName()] > x.distance + e.weight)
							distance[v.getName()] = x.distance + e.weight;
						v.parent = x;
					}
				}
			}

			// updating distances after completion of whole iteration
			for (int j = 0; j < distance.length; j++)
				g.getVertex(j + 1).distance = distance[j];
		}
		return g.getVertex(t.getName() + 1).distance;
	}

	// Part f. Reward collection problem
	// Reward for vertices is passed as a parameter in a hash map
	// tour is empty list passed as a parameter, for output tour
	// Return total reward for tour
	public int reward(HashMap<Vertex, Integer> vertexRewardMap, List<Vertex> tour) {
		// To do

		ArrayDeque<Vertex> path = new ArrayDeque<>();
		ArrayDeque<Vertex> bestPath = new ArrayDeque<>(); // stores path with
															// best reward
		ArrayDeque<Vertex> alternatePath = new ArrayDeque<>();

		Vertex src, dst;
		src = this.s;
		int highestReward = 0, tempReward;
		Boolean res; // stores whether alternate path exists

		for (Vertex v : g.v) {
			if (!v.equals(src)) {
				// System.out.println("source "+src);
				// System.out.println("Destination "+v);
				path.removeAll(path);
				dst = v;
				dst.reward = 0;
				shortestPath(src, dst);
				Vertex tmp = dst;
				while (tmp != src) {
					path.addFirst(tmp);
					tmp = tmp.parent;
				}
				path.addFirst(src);

				tempReward = 0;
				for (Vertex s : path) {
					tempReward = tempReward + vertexRewardMap.get(s);
				}
				// System.out.println("reward is "+tempReward);

				for (Vertex w : path)
					alternatePath.add(w);
				res = checkAlternatePath(alternatePath, src, dst);

				for (Vertex p : g.v) {
					p.disabled = false;
				}

				// System.out.println("checking alternate path "+res+"\n");

				if (res) {
					if (tempReward > highestReward) {
						highestReward = tempReward;
						bestPath.removeAll(bestPath);
						for (Vertex l : path)
							bestPath.add(l);
						alternatePath.removeFirst();
						for (Vertex l : alternatePath)
							bestPath.add(l);
					}
				}

			}

		}
		// System.out.println("Highest reward "+highestReward);
		// System.out.println("path is");
		for (Vertex x : bestPath) {
			// System.out.print(" "+x);
			tour.add(x);
		}
		return highestReward;
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

	public void shortestPath(Vertex src, Vertex dst) {

		Comparator<Vertex> cmp = new Comparator<Graph.Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				// TODO Auto-generated method stub
				if (o1.distance < o2.distance)
					return -1;
				else if (o1.distance > o2.distance)
					return 1;
				else
					return 0;
			}
		};
		PriorityQueue<Vertex> pq = new PriorityQueue<>(cmp);
		g.getVertex(src.getName() + 1).distance = 0;
		for (Vertex x : g.v) {
			if (x.getName() != src.getName())
				x.distance = Integer.MAX_VALUE;
			x.sptSet = false;
			pq.add(x);
		}

		while (!pq.isEmpty()) {
			Vertex temp = pq.poll();
			temp.sptSet = true;

			if (temp.getName() == dst.getName()) // if this is destination node
				break;

			for (Edge v : temp.adj) {
				Vertex oth = v.otherEnd(temp);
				if (!oth.sptSet && temp.distance != Integer.MAX_VALUE && temp.distance + v.weight < oth.distance) {
					oth.distance = temp.distance + v.weight;
					oth.parent = temp;
					pq.remove(oth);
					pq.add(oth);
				}
			}
		}
	}

	public boolean checkPath(ArrayDeque<Vertex> path, BFS b) {
		Vertex temp = path.peekLast();
		while (temp != path.peek()) {
			// System.out.println("Parent of "+temp+" is "+b.getParent(temp));
			temp = b.getParent(temp);
			if (temp == null)
				return false;
		}
		if (temp.equals(path.peek()))
			return true;
		else
			return false;
	}

	public boolean checkAlternatePath(ArrayDeque<Vertex> path, Vertex src, Vertex dst) {

		for (Vertex v : g.v) {
			v.disabled = false;
		}

		for (Vertex x : path) {
			x.disabled = true;
		}
		src.disabled = false;
		dst.disabled = false;

		// System.out.println("Running BFS");
		BFS b = new BFS(g, dst);
		b.bfs();

		ArrayDeque<Vertex> BFSpath = new ArrayDeque<>();
		Vertex temp = src;
		while (temp != dst) {
			BFSpath.addFirst(temp);
			temp = b.getParent(temp);
			if (temp == null)
				break;
		}
		BFSpath.addFirst(dst);

		boolean res = checkPath(BFSpath, b);

		path.removeAll(path);
		for (Vertex r : BFSpath)
			path.add(r);

		return res;
	}

}
