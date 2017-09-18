package cs6301.g44;

import java.util.Scanner;

/**
* @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
public class DriverEularian {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Graph graph = Graph.readDirectedGraph(scanner);
        EulerianGraph obj = new EulerianGraph();
        System.out.println("Is Graph Eulerian: "+ obj.testEulerian(graph));
    }
}
