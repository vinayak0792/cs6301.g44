package cs6301.g44;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 *         Selection class consists 3 algorithms to find k largest elements from
 *         a given unsorted list of elements.
 */
public class Selection {

	// Algorithm 1: Using Priority Queue(max heap) to select k largest elements
	<T extends Comparable<? super T>> List<T> maxHeap(T[] A, int k) {

		PriorityQueue<T> pq = new PriorityQueue<>(k, Collections.reverseOrder());
		List<T> kLargeElements = new ArrayList<T>();

		for (T x : A) {
			pq.add(x);
		}

		for (int i = 0; i < k; i++) {
			kLargeElements.add(pq.remove());
		}

		return kLargeElements;

	}
	
	// Algorithm 2: Using Priority Queue(min heap) to select k largest elements
	<T extends Comparable<? super T>> PriorityQueue<T> minHeap(List<T> A, int k) {
		PriorityQueue<T> pq = new PriorityQueue<T>(k);
		Iterator<T> it = A.iterator();
		for (int i = 0; i < k; i++) {
			if (it.hasNext()) {
				pq.add(it.next());
			} else {

				return pq;
			}

		}
		while (it.hasNext()) {
			T x = it.next();
			if (x.compareTo(pq.peek()) > 0) {
				pq.remove();
				pq.add(x);
			}
		}
		return pq;
	}

	// Algorithm 3: Using partition to find k largest elements
	<T extends Comparable<? super T>> T[] select(T[] A, int k) {
		int n = A.length - 1;
		if (k <= 0)
			return null;
		if (k >= n)
			return A;
		select(A, 0, n, k);
		return A;
	}

	// Helper Function to perform insertion sort
	<T extends Comparable<? super T>> void insertionSort(T[] A, int p, int rightElement) {
		for (int i = p + 1; i < rightElement; i++) {
			T element = A[i];
			int j = i - 1;
			while (j > -1 && A[j].compareTo(element) > 0) {
				A[j + 1] = A[j];
				j = j - 1;
			}
			A[j + 1] = element;

		}
	}

	// Helper function to find the kth largest element.
	<T extends Comparable<? super T>> T select(T[] A, int p, int n, int k) {
		int rightElement = p + n - 1;
		if (n < 7) {
			insertionSort(A, p, rightElement);
			return A[p + n - k];
		} else {
			int pivot = QuickSort.getPartitionVersion1(A, p, rightElement);
			int left = pivot - p;
			int right = rightElement - pivot;
			if (right >= k) {
				return select(A, pivot + 1, right, k);
			}

			else if (right + 1 == k) {
				return A[pivot];

			} else {
				return select(A, p, left, k - (right + 1));
			}

		}

	}

}
