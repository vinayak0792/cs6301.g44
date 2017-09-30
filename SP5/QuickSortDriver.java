package cs6301.g44;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


/**
 *@author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
public class QuickSortDriver {

    public static void main(String[] args){
        QuickSort obj = new QuickSort();
        Scanner sc = new Scanner(System.in);
        Timer timer = new Timer();
        System.out.println("Enter the no. of elements:");
        int n = sc.nextInt();
        Integer[] elements = new Integer[n];
        for(int i=0; i< n ; i++)
            elements[i] = (int) (Math.random()* n)+1;
        System.out.println("----------------------Quick Sort Versions on Random Shuffle:------------------------");
        Shuffle.shuffle(elements);
        System.out.println("-----Quick Sort by partition version: 1-----");
        timer.start();
        obj.quickSort(elements);
        timer.end();
        System.out.println(timer);
        Shuffle.shuffle(elements);
        System.out.println("----Quick Sort by Hoare's partition version : 2----");
        timer.start();
        obj.quickSortVer2(elements);
        timer.end();
        System.out.println(timer);

        System.out.println("------------------Quick Sort Versions on descending ordered elements:-------------------");
        Arrays.sort(elements, Collections.reverseOrder());
        System.out.println("-----Quick Sort by partition version: 1-----");
        timer.start();
        obj.quickSort(elements);
        timer.end();
        System.out.println(timer);

        System.out.println("----Quick Sort by Hoare's partition version: 2----");
        Arrays.sort(elements, Collections.reverseOrder());
        timer.start();
        obj.quickSortVer2(elements);
        timer.end();
        System.out.println(timer);
    }
}

