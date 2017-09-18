StronglyConnectedDriver.java : Driver program to find no. of strongly connected components
StronglyConnectComp.java: Includes program that returns # of strongly connected components

EulerianGraph.java : Checks if graph is strongly connected and for each vertex in-degree is equal to out-degree
DriverEularian.java : Driver program to check whether the graph is eulerian or not

BridgeCutVertices.java : Driver program that checks for Bridges and Cut Vertices.
DFSBridges.java : Includes program that returns the bridges in a given graph.

1. Topological ordering of a DAG
TopologicalOrder.java - It has two functions toplogicalOrder1 and toplogicalOrder2
It uses Graph.java class

Sample input:
8
7
1 2 1
2 5 1
2 3 1
3 4 1
3 6 1
6 7 1
6 8 1

Output:
Topological Order 1
 1 2 5 3 4 6 7 8
Topological Order 2
 1 2 3 6 8 7 4 5
