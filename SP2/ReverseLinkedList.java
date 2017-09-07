package cs6301.g44;

import java.util.Scanner;

public class ReverseLinkedList {

    private static Node reverseListIterative(Node head){
        Node cur = head, prev = null, temp;
        while (cur != null){
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    private static<T> void reverseListRecursive(SinglyLinkedList<T> list, Node cur){
        if (cur.next == null){
            list.header.next = cur;
            return;
        }
        reverseListRecursive(list, cur.next);
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
        System.out.println("Iterative");
        System.out.println("Before:");
        list1.printList();
        timer.start();
        list1.header.next = reverseListIterative(head1);
        timer.end();
        System.out.println("After:");
        list1.printList();
        System.out.println(timer);
    }
}
