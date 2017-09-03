/**  Short Project 1: Driver Program to perform n square sort on generic data type  
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */

package cs6301.g44;

import java.util.Scanner;

public class NSquareSort {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the array size");
		int size = in.nextInt();
		Timer t = new Timer();
		Integer arr[] = new Integer[size];
                
		//Inserting random numbers into the input array
		for (int i = 0; i < size; i++)
			arr[i] = 0 + (int) (Math.random() * ((1000000 - 0) + 1));

		t.start();
		Sort.nSquareSort(arr);
		System.out.println(t.end()); //printing the time taken to run the algorithm

	}
}
