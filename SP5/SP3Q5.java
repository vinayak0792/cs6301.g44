/**
 * 
 */
package cs6301.g44;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs6301.g44.Timer;
/**
 * @author vinayaka
 *
 */
public class SP3Q5 {
	public static void main(String[] args) throws FileNotFoundException {
		
		Timer timer = new Timer();
		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		Selection s = new Selection();
		System.out.println("Enter the no. of elements:");
		int n = in.nextInt();
		System.out.println("Enter the k values");
		int k = in.nextInt();
		Integer[] kLargeElements = new Integer[k];
		Integer[] elements = new Integer[n];
		
		for (int i = 0; i < n; i++)
			elements[i] = (int) (Math.random() * n) + 1;
		
		timer.start();
		List<Integer>kLargeElement = s.maxHeap(elements, k);
		timer.end();
		for (int i = 0; i < k ; i++)
			System.out.println(kLargeElement.get(i));
		System.out.println(timer);
		
		Shuffle.shuffle(elements);
		timer.start();
		s.select(elements, k);
		timer.end();
		for (int i = elements.length - k - 1; i < elements.length - 1; i++)
			System.out.println(elements[i]);
		System.out.println(timer);
		
		List<Integer> element = new ArrayList<>();
		for (int i = 0; i < n; i++)
			element.add((int) (Math.random() * n) + 1);
		timer.start();
		kLargeElements = (Integer[])s.minHeap(element, k);
		timer.end();
		for(int x : kLargeElements)
			System.out.println(x);
		System.out.println(timer);
		
		
	}
}
