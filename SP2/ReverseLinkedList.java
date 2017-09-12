package cs6301.g44;
/**
 * Write recursive and nonrecursive functions for the following tasks:
 (i) reverse the order of elements of the SinglyLinkedList class,
 (ii) print the elements of the SinglyLinkedList class, in reverse order.

 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */


public class ReverseLinkedList {

    public <T> void reverseListIterative(SinglyLinkedList<T> list, Node head){
        Node cur = head, prev = null, temp;
        while (cur != null){
            temp = cur.next;        //For each node, save next node and point cur of next to previous
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
         list.header.next = prev; //Make last node as header
    }

    public <T> void reverseListRecursive(SinglyLinkedList<T> list, Node cur){
        if (cur.next == null){          //Base condition : If last node, make it header
            list.header.next = cur;
            return;
        }
        reverseListRecursive(list, cur.next); // Call recursion for every next node
        cur.next.next = cur;
        cur.next = null;
    }
}
