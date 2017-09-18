package cs6301.g44;

import java.util.Iterator;

/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
public class EulerianGraph {
    boolean testStronglyConnected(Graph g) {            //StronglyConnectComp is required to run this program
        StronglyConnectComp obj = new StronglyConnectComp();
        if (obj.stronglyConnectedComponents(g)== 1)         //If count is one, then graph is strongly connected
            return true;
        return false;
    }

    boolean testEulerian(Graph graph){
        if (!testStronglyConnected(graph))      //Check if graph is strongly connected
            return false;
        Iterator<Graph.Vertex> vertexIterator = graph.iterator();
        while (vertexIterator.hasNext()){
            Graph.Vertex ver = vertexIterator.next();
                                                        //Check for each vertex in-degree is equal to out-degree
            if (ver.adj.size() != ver.revAdj.size() )
                return false;
        }
        return true;
    }
}
