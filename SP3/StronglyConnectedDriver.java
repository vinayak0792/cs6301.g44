package cs6301.g44;

import java.util.Scanner;

/**Driver program to find strongly connected components
 *
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
public class StronglyConnectedDriver {
    public static void main(String[] args){
        System.out.println("Read Graph:");
        StronglyConnectComp obj = new StronglyConnectComp();
        Scanner sc = new Scanner(System.in);
        Graph g = Graph.readDirectedGraph(sc);

        System.out.println("Number of strongly connected components:");
        System.out.println(obj.stronglyConnectedComponents(g));
    }
}
