package cs6301.g44;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;
/**

 */
public class SetOperations {

    static<T> T next(Iterator<T> it){
        return (it.hasNext()) ? it.next() : null;
    }

    static<T extends Comparable<? super T>> void difference(List<T> list1, List<T> list2, List<T> outList){
        Iterator<T> it1 = list1.iterator(), it2 = list2.iterator();
        T item1 = next(it1), item2 = next(it2);
        while (item1 != null  && item2!= null){
            if (item1.compareTo(item2) < 0){
                outList.add(item1);
                item1 = next(it1);
            }else if (item1.compareTo(item2) > 0)
                item2 = next(it2);
             else{
                item1 = next(it1);
                item2 = next(it2);
            }
        }
        while (item1 != null){
            outList.add(item1);
            item1 = next(it1);
        }
    }

    private static<T extends Comparable<? super T>> void intersect(List<T> list1, List<T> list2, List<T> outlist){
        Iterator<T> it1 = list1.iterator(), it2 = list2.iterator();
        T item1 = next(it1), item2 = next(it2);
        while (item1 !=null && item2!=null){
            if (item1.compareTo(item2) < 0)
                item1 = next(it1);
            else if (item1.compareTo(item2) > 0)
                item2 = next(it2);
            else{
                outlist.add(item1);
                item1 = next(it1);
                item2 = next(it2);
            }
        }
    }

    private static<T extends Comparable<? super T>>  void union(List<T> list1, List<T> list2, List<T> outList) {
        Iterator<T> it1 = list1.iterator(), it2 = list2.iterator();
        T item1 = next(it1) , item2 = next(it2);
        while ((item1 != null) && (item2 != null)){
            if (item1.compareTo(item2) < 0) {
                outList.add(item1);
                item1 = next(it1);
            }
            else if (item1.compareTo(item2) > 0){
                outList.add(item2);
                item2 = next(it2);
            }
            else{
                outList.add(item1);
                item1 = next(it1); item2 = next(it2);
            }
        }
        //Remaining elements in list1
        while (item1 != null){
            outList.add(item1);
            item1 = next(it1);
        }

        //Remaining elements in list2
        while (item2 != null){
            outList.add(item2);
            item2 = next(it2);
        }
    }

    public static void main(String[] args){
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
        union(list1, list2, outList);
        System.out.println("\n" + "Union:");
        if (outList.isEmpty())
            System.out.println("Union of list1 & list2  is empty");
        for (Integer i : outList)
            System.out.print(i +" ");

        outList.clear();

        //Intersection
        intersect(list1, list2, outList);
        System.out.println("\n" + "Intersect:");
        if (outList.isEmpty())
            System.out.println("Intersection of list1 & list2  is empty");
        for (Integer i : outList)
            System.out.print(i +" ");

        outList.clear();

        //Difference
        difference(list1, list2, outList);
        System.out.println("\n" + "Difference:");
        if (outList.isEmpty())
            System.out.println("Difference of list1 & list2  is empty");
        for (Integer i : outList)
            System.out.print(i +" ");
    }
}
