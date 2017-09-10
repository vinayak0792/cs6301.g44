package cs6301.g44;

import java.util.Scanner;


/**
 @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */

public class DriverReverseLL {
    public static void main(String[] args){
        ReverseLinkedList obj = new ReverseLinkedList();
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
        obj.reverseListRecursive(list, head);
        timer.end();
        list.printList();
        System.out.println(timer);
        SinglyLinkedList.createIntegerList(n, list1);
        Node head1 = list1.header.next;
        System.out.println("\nIterative");
        System.out.println("Before:");
        list1.printList();
        timer.start();
        obj.reverseListIterative(list1, head1);
        timer.end();
        System.out.println("After:");
        list1.printList();
        System.out.println(timer);
    }

}
