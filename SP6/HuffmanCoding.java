package cs6301.g44;

import java.util.PriorityQueue;

public class HuffmanCoding {

	public static void main(String[] args) {
		PriorityQueue<HuffTree> PQ =new PriorityQueue<>();
		
		//adding nodes to priority queue
		PQ.add(new HuffTree(0.5, 'A'));
		PQ.add(new HuffTree(0.25, 'C'));
		PQ.add(new HuffTree(0.1, 'G'));
		PQ.add(new HuffTree(0.15, 'T'));
		
		while(PQ.size()>1){
			HuffTree t1=PQ.remove();
			HuffTree t2=PQ.remove();
			HuffTree node=HuffTree.merge(t1, t2);
			PQ.add(node);
		}
		
		//getting root node
		HuffTree res=PQ.remove();
		
		//assigning codes and printing the result in function
		HuffTree.assignCode(res);
	}
	
}
