/**  Short Project 1: Contains all the sort methods 
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */

package cs6301.g44;

public class Sort {

	//merge function for integer mergesort
	static void merge(int[] arr, int[] tmp, int low, int mid, int high) {
		int k = low;
		int j = mid + 1;
		   
		for (int i = low; i <= high; i++) {
			tmp[i] = arr[i];
		}

		for (int i = low; i <= high; i++) {
			if (j > high)
				arr[i] = tmp[k++];
			else if (k > mid)
				arr[i] = tmp[j++];
			else if (tmp[j] > tmp[k])
				arr[i] = tmp[k++];
			else
				arr[i] = tmp[j++];
		}

	}
        
	//insertion sort
	static void nSquareSort(int[] arr) {
		int temp;
		int j;
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];
			j = i - 1;
			while (j > -1 && arr[j] > temp) {
				arr[j + 1] = arr[j];
				j = j - 1;

			}
			arr[j + 1] = temp;
		}
	}
         
	//generic insertion sort
	static <T extends Comparable<? super T>> void nSquareSort(T[] arr) {
		T temp;
		int j;
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];
			j = i - 1;
			while (j > -1 && (arr[j].compareTo(temp) > 0)) {
				arr[j + 1] = arr[j];
				j = j - 1;

			}
			arr[j + 1] = temp;
		}

	}

	static void mergeUtil(int[] arr, int[] tmp, int low, int high) {

		if (high <= low)
			return;
		int mid = (high + low) / 2;
		mergeUtil(arr, tmp, low, mid);
		mergeUtil(arr, tmp, mid + 1, high);
		merge(arr, tmp, low, mid, high);
	}
	
	static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int low, int mid, int high) {
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
			else if (aux[j].compareTo(aux[k]) > 0)
				a[i] = aux[k++];
			else
				a[i] = aux[j++];
		}

	}

	static <T extends Comparable<? super T>> void mergeUtil(T[] arr, T[] tmp, int low, int high) {
		if (high <= low)
			return;
		int mid = (high + low) / 2;
		mergeUtil(arr, tmp, low, mid);
		mergeUtil(arr, tmp, mid + 1, high);
		merge(arr, tmp, low, mid, high);
	}

	static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp) {
		mergeUtil(arr, tmp, 0, arr.length - 1);
	}


	static void mergeSort(int[] arr, int[] tmp) {
		//System.out.println("int merge sort");
		mergeUtil(arr, tmp, 0, arr.length - 1);
	}

}
