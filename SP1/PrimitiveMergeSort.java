/**  Short Project 1: Driver Program to perform merge sort on primitive data type 
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */

package cs6301.g44;

import java.util.Scanner;

public class PrimitiveMergeSort {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the array size");
		int size = in.nextInt();
		Timer t = new Timer();
		int arr[] = new int[size];
		//inserting random numers into the array
		for (int i = 0; i < size; i++)
			arr[i] = 0 + (int) (Math.random() * ((1000000 - 0) + 1));

		int[] aux = new int[size];

		t.start();
		Sort.mergeSort(arr, aux);
		System.out.println(t.end());

	}
}
