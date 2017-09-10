package cs6301.g44;

import cs6301.g44.Queue;

public class DriverQueue {
	public static void main(String[] args) {
		Queue<String> q = new Queue<>();
		
		//Adding 65 elements to the Queue and check if the size of the queue has increased.
		for (int i = 0; i < 65; i++)
			q.offer("1");
		System.out.println(q.size());
		
		//Removing elements from the queue and checking if the queue size has decreased.
		for (int i = 0; i < 65; i++)
			q.poll();
		System.out.println(q.size());

	}
}
