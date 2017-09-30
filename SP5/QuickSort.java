package cs6301.g44;


/**
 *  *@author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */

public class QuickSort {
    public static<T extends Comparable<? super T>> void swap(T[]elements,int index1, int index2){
        T temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    public static <T extends Comparable<? super T>>
    int getPartitionHoareVersion(T[] elements, int low, int high){
        int index = low + (int) (Math.random()*(high-low+1));
        T pivot = elements[index];
        int left = low-1, right = high+1;

        while (true){
            do {
                left++;
            }while (elements[left].compareTo(pivot) < 0);

            do {
                right--;
            }while (elements[right].compareTo(pivot) > 0);

            if (left<right)
                swap(elements, left, right);
            else
                return right;
        }
    }

    public static <T extends Comparable<? super T>>
    void quickSortVer2(T[] elements){
        quickSortVer2(elements, 0, elements.length-1);
    }

    public static <T extends Comparable<? super T>>
    void quickSortVer2(T[] elements, int low, int high){
        if (low< high) {
            int p = getPartitionHoareVersion(elements, low, high);
            quickSortVer2(elements, low, p);
            quickSortVer2(elements, p + 1, high);
        }
    }

    public static<T extends Comparable<? super T>> int getPartitionVersion1(T[] elements, int low, int high){
        int partitionIndex = low + (int)(Math.random()*(high-low+1));
        swap(elements, partitionIndex, high);
        T pivot = elements[high];
        partitionIndex = low;
        for (int cur= low; cur<=high-1; cur++){
            if (elements[cur].compareTo(pivot) < 0){
                swap(elements, cur, partitionIndex);
                partitionIndex ++;
            }
        }
        swap(elements, partitionIndex, high);
        return partitionIndex;
    }

    public static<T extends Comparable<? super T>> void quickSort(T[] elements){
        quickSort(elements, 0 , elements.length-1);
    }

    public static<T extends Comparable<? super T>> void quickSort(T[] elements, int low, int high){
        if (low <  high){
            int pIndex = getPartitionVersion1(elements, low, high);
            quickSort(elements, low, pIndex-1);
            quickSort(elements, pIndex+1, high);
        }
    }
}
