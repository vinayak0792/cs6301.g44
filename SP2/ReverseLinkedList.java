package cs6301.g44;
/**
 * Write recursive and nonrecursive functions for the following tasks:
 (i) reverse the order of elements of the SinglyLinkedList class,
 (ii) print the elements of the SinglyLinkedList class, in reverse order.

 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */

import java.util.Scanner;

public class ReverseLinkedList {

    private static<T> void reverseListIterative(SinglyLinkedList<T> list, Node head){
        Node cur = head, prev = null, temp;
        while (cur != null){
            temp = cur.next;        //For each node, save next node and point cur of next to previous
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
         list.header.next = prev; //Make last node as header
    }

    private static<T> void reverseListRecursive(SinglyLinkedList<T> list, Node cur){
        if (cur.next == null){          //Base condition : If last node, make it header
            list.header.next = cur;
            return;
        }
        reverseListRecursive(list, cur.next); // Call recursion for every next node
        cur.next.next = cur;
        cur.next = null;
    }

    public static void main(String[] args){
        Timer timer = new Timer();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of nodes");
        int n = sc.nextInt();

        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        SinglyLinkedList.createIntegerList(n,list);

        Node head = list.header.next;
        System.out.println("Recursive");
        System.out.println("Before:");
        list.printList();
        System.out.println("After:");
        timer.start();
        reverseListRecursive(list, head);
        timer.end();
        list.printList();
        System.out.println(timer);
        SinglyLinkedList.createIntegerList(n, list1);
        Node head1 = list1.header.next;
        System.out.println("\nIterative");
        System.out.println("Before:");
        list1.printList();
        timer.start();
        reverseListIterative(list1, head1);
        timer.end();
        System.out.println("After:");
        list1.printList();
        System.out.println(timer);
    }
}
