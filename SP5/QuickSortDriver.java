package cs6301.g44;

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
        for(int i=0; i<n ; i++)
            elements[i] = (int) (Math.random()*1000000);
        timer.start();
        obj.quickSort(elements);
        timer.end();
        System.out.println("Quick Sort by partition version : 1");
        System.out.println(timer);
        Shuffle.shuffle(elements);
        timer.start();
        obj.quickSortVer2(elements);
        timer.end();
        System.out.println("Quick Sort by Hoare's partition version : 2");
        System.out.println(timer);
        //for (int k=0; k<n; k++)
        //   System.out.print(elements[k] + " ");
    }

}

