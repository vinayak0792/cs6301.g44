package cs6301.g44;

import java.util.Scanner;

/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
public class MergeSortVerDriver {

    public static void main(String[] args){
        MergeSortVersions obj = new MergeSortVersions();
        System.out.println("Enter no. of elements:");
        Scanner input = new Scanner(System.in);
        Timer timer = new Timer();
        int n = input.nextInt();
        int[] elements = new int[n];
        for (int i=0; i<n; i++)
            elements[i] = (int) (Math.random() * 1000000);
        //elements[i] = input.nextInt();
        //Version 1
        System.out.println("MergeSort Version:1");
        timer.start();
        obj.mergeSortV1(elements);
        timer.end();
        System.out.println(timer);

        Shuffle.shuffle(elements);
        //Version 2
        System.out.println("MergeSort Version:2");
        timer.start();
        obj.mergeSortV2(elements);
        timer.end();
        System.out.println(timer);

        //Version 3
        Shuffle.shuffle(elements);

        System.out.println("MergeSort Version:3");
        timer.start();
        obj.mergeSortV3(elements);
        timer.end();
        System.out.println(timer);

        //for (int ele: elements)
        //   System.out.print(ele+" ");
    }
}

