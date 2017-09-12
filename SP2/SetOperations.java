package cs6301.g44;

import java.util.Iterator;
import java.util.List;

/**
 * Given two linked lists implementing sorted sets, write functions for
 union, intersection, and set difference of the sets.

 @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */

//Here  lists are assumed to be sets. So no duplicate elements

public class SetOperations {
    //Utility function
    static<T> T next(Iterator<T> it){
        return (it.hasNext()) ? it.next() : null;
    }

   public <T extends Comparable<? super T>> void difference(List<T> list1, List<T> list2, List<T> outList){
        Iterator<T> it1 = list1.iterator(), it2 = list2.iterator();
        T item1 = next(it1), item2 = next(it2);
        while (item1 != null  && item2!= null){
            if (item1.compareTo(item2) < 0){    //Add elements only from list1
                outList.add(item1);
                item1 = next(it1);
            }else if (item1.compareTo(item2) > 0)
                item2 = next(it2);
             else{
                item1 = next(it1);
                item2 = next(it2);
            }
        }//Add remaining elements in list1 to output list
        while (item1 != null){
            outList.add(item1);
            item1 = next(it1);
        }
    }

    public <T extends Comparable<? super T>> void intersect(List<T> list1, List<T> list2, List<T> outlist){
        Iterator<T> it1 = list1.iterator(), it2 = list2.iterator();
        T item1 = next(it1), item2 = next(it2);
        while (item1 !=null && item2!=null){
            if (item1.compareTo(item2) < 0)
                item1 = next(it1);
            else if (item1.compareTo(item2) > 0)
                item2 = next(it2);
            else{               //Add elements that are in both lis1 & list2
                outlist.add(item1);
                item1 = next(it1);
                item2 = next(it2);
            }
        }
    }

    public <T extends Comparable<? super T>>  void union(List<T> list1, List<T> list2, List<T> outList) {
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

}
