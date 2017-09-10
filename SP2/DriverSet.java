package cs6301.g44;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


/**
 @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal

 */
public class DriverSet {
    public static void main(String[] args){
        SetOperations obj = new SetOperations();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in list 1 & list 2:");
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        LinkedList<Integer> list1 = new LinkedList<>();
        for (int i=1; i<=n1; i++)
            list1.add(i);

        LinkedList<Integer> list2 = new LinkedList<>();
        for (int i=1; i<=n2; i++)
            list2.add((i+2));

        LinkedList<Integer> outList = new LinkedList<>();

        Collections.sort(list1);
        Collections.sort(list2);

        for (Integer i : list1)
            System.out.print(i +" ");
        System.out.println(" ");
        for (Integer i : list2)
            System.out.print(i +" ");

        //Union
        obj.union(list1, list2, outList);
        System.out.println("\n" + "Union:");
        if (outList.isEmpty())
            System.out.println("Union of list1 & list2  is empty");
        for (Integer i : outList)
            System.out.print(i +" ");

        outList.clear();

        //Intersection
        obj.intersect(list1, list2, outList);
        System.out.println("\n" + "Intersect:");
        if (outList.isEmpty())
            System.out.println("Intersection of list1 & list2  is empty");
        for (Integer i : outList)
            System.out.print(i +" ");

        outList.clear();

        //Difference
        obj.difference(list1, list2, outList);
        System.out.println("\n" + "Difference:");
        if (outList.isEmpty())
            System.out.println("Difference of list1 & list2  is empty");
        for (Integer i : outList)
            System.out.print(i +" ");
    }
}
