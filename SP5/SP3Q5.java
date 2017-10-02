package cs6301.g44;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 *
 */
public class SP3Q5 {
	public static void main(String[] args) throws FileNotFoundException {

		Timer timer = new Timer();
		Scanner in = new Scanner(System.in);

		Selection s = new Selection();
		System.out.println("Enter the no. of elements:");
		int n = in.nextInt();
		System.out.println("Enter the k values");
		int k = in.nextInt();
		Random r = new Random();
		Integer[] elements = new Integer[n];

		for (int i = 0; i < n; i++)
			elements[i] = (int) (Math.random() * n) + 1;

		timer.start();
		List<Integer> kLargeElement = s.maxHeap(elements, k);
		timer.end();
		for (int i = 0; i < k; i++)
			System.out.print(kLargeElement.get(i)+" ");
		System.out.println("\n"+timer);

		Shuffle.shuffle(elements);
		timer.start();
		s.select(elements, k);
		timer.end();
		for (int i = elements.length - k - 1; i < elements.length - 1; i++)
			System.out.print(elements[i]+" ");
		System.out.println("\n"+timer);

		Shuffle.shuffle(elements);
		List<Integer> element = new ArrayList<>();
		for (int i = 0; i < n; i++)
			element.add(r.nextInt(elements.length));
		timer.start();
		PriorityQueue<Integer> kLargeElements = s.minHeap(element, k);
		timer.end();
		for (int x : kLargeElements)
			System.out.print(x+" ");
		System.out.println("\n"+timer);
		in.close();
	}
}
