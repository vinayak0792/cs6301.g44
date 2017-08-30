package cs6301;

public class SortGeneric {

	public static <T extends Comparable<? super T>> void doInsertionSort(T[] input) {
		T temp;
		int j;
		for (int i = 1; i < input.length; i++) {
			temp = input[i];
			j = i - 1;
			while (j > -1 && (input[j].compareTo(temp) > 0)) {
				input[j + 1] = input[j];
				j = j - 1;

			}
			input[j + 1] = temp;
		}

		for (T x : input)
			System.out.print(x + " ");
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

	static <T extends Comparable<? super T>> void mergeSor(T[] a, T[] aux, int low, int high) {
		int lengthAux = aux.length;
		if (high <= low)
			return;
		int mid = (high + low) / 2;
		mergeSor(a, aux, low, mid);
		mergeSor(a, aux, mid + 1, high);
		merge(a, aux, low, mid, high);
	}

	static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp) {
		mergeSor(arr, tmp, 0, arr.length - 1);
	}
//	
//	static<T> void randomNumbers(T[] arr ,int n){
//		
//	}

	public static void main(String[] args) {
		Integer a[] = new Integer[15000000];
		for (int i = 0; i < a.length; i++)
			a[i] = 0 + (int) (Math.random() * ((1000000 - 0) + 1));
		int length = a.length;
		Integer[] aux = new Integer[length];
		aux[aux.length - 2] = 0;
		aux[aux.length - 1] = a.length - 1;
		System.out.println("Start");
		long startTime = System.currentTimeMillis();
		mergeSort(a, aux);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("OlogN estimated time= " + estimatedTime);
		for (int i = 0; i < a.length; i++)
			a[i] = 0 + (int) (Math.random() * ((100000 - 0) + 1));
		startTime = System.currentTimeMillis();
		doInsertionSort(a);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("N square estimated time= " + estimatedTime);

	}
}
