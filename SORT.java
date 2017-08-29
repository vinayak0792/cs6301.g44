import java.io.*;
import java.util.*;

public class SORT {
	static void merge(int[] a, int[] aux, int low, int mid, int high) {
		int k = low;
		int j = mid + 1;

		for (int i = low; i <= high; i++) {
			aux[i] = a[i];
		}

		for (int i = low; i <= high; i++) {
			if (j > high)
				a[i] = aux[k++];
			else if (k > mid)
				a[i] = aux[j++];
			else if (aux[j] > aux[k])
				a[i] = aux[k++];
			else
				a[i] = aux[j++];
		}

	}

	static void doInsertionSort(int[] input) {

		int temp;
		int j;
		for (int i = 1; i < input.length; i++) {
			temp = input[i];
			j = i - 1;
			while (j > -1 && input[j] > temp) {
				input[j + 1] = input[j];
				j = j - 1;

			}
			input[j + 1] = temp;
		}
	}

	static void mergeSort(int[] a, int[] aux) {
		int lengthAux = aux.length;
		int low = aux[lengthAux - 2];
		int high = aux[lengthAux - 1];
		if (high <= low)
			return;
		int mid = (high + low) / 2;
		aux[lengthAux - 1] = mid;
		aux[lengthAux - 2] = low;
		mergeSort(a, aux);
		aux[lengthAux - 1] = high;
		aux[lengthAux - 2] = mid + 1;
		mergeSort(a, aux);
		merge(a, aux, low, mid, high);
	}

	public static void main(String[] args) {
		int a[] = new int[150000000];
		for (int i = 0; i < a.length; i++)
			a[i] = 0 + (int) (Math.random() * ((10000000 - 0) + 1));
		int length = a.length;
		int[] aux = new int[length + 2];
		aux[aux.length - 2] = 0;
		aux[aux.length - 1] = a.length - 1;
		System.out.println("Start");
		long startTime = System.currentTimeMillis();
		mergeSort(a, aux);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("OlogN estimated time= " + estimatedTime);
		startTime = System.currentTimeMillis();
		mergeSort(a, aux);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("N square estimated time= " + estimatedTime);

	}

}