// Starter code for LP8
package cs6301.g44;
import cs6301.g44.Graph.*;
import java.util.HashMap;
import java.util.Map;

public class MinCostFlow {
    private XGraph residual_graph;
    private Vertex source, sink;
    private HashMap<Edge, Integer> cap;
    public MinCostFlow(Graph g, Vertex s, Vertex t, HashMap<Edge, Integer> capacity, HashMap<Edge, Integer> cost) {
        residual_graph = new XGraph(g);
        source = s;
        sink = t;
        cap = capacity;
    }

    int find_augemented_path(Vertex u, int flow){
        if (u==sink) return flow;
        XGraph.XVertex xu = residual_graph.getVertex(u);
        xu.seen = true;

        for (Edge edge: xu){
            Vertex v = edge.otherEnd(xu);
            XGraph.XVertex xv = residual_graph.getVertex(v);
            XGraph.XEdge xEdge = residual_graph.getEdge(edge);
            XGraph.XEdge xrevEdge = new XGraph.XEdge(xv,xu,edge.weight); //= residual_graph.getReverseEdge(u,v);
            if ( xEdge.capacity >0 && !xv.seen ){
                int block_flow = find_augemented_path(v, Math.min(flow, capacity(xEdge)));
                if (block_flow >0){
                    xEdge.flow -= block_flow;
                    xu.xrevAdj.add(xrevEdge);
                    xrevEdge.flow += block_flow;
                }
            }
        }
        return 0;
    }

    int find_max_flow(){
        int flow = 0, total_flow=0;
        while (true) {
            flow = find_augemented_path(source, Integer.MAX_VALUE);
            if (flow ==0) break;
            total_flow += flow;
        }
        return total_flow;
    }

    // Return cost of d units of flow found by cycle cancellation algorithm
    int cycleCancellingMinCostFlow(int d) {
        int max_flow = find_max_flow();
        return 0;
    }

    // Return cost of d units of flow found by successive shortest paths
    int successiveSPMinCostFlow(int d) {
	return 0;
    }

    // Return cost of d units of flow found by cost scaling algorithm
    int costScalingMinCostFlow(int d) {
	return 0;
    }

    // flow going through edge e
    public int flow(Edge e) {
	return 0;
    }

    // capacity of edge e
    public int capacity(XGraph.XEdge e) {
        if(cap.containsKey(e))
            return cap.get(e);
        return 0;
    }

    // cost of edge e
    public int cost(Edge e) {
	return 0;
    }
}