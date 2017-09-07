/** @author rbk
 *  Singly linked list: for instructional purposes only
 *  Ver 1.0: 2017/08/08
 *  Ver 1.1: 2017/08/30: Fixed error: If last element of list is removed,
 *  "tail" is no longer a valid value.  Subsequently, if items are added
 *  to the list, code would do the wrong thing.
 */

package cs6301.g44;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SinglyLinkedList<T> implements Iterable<T> {
    // Dummy header is used.  tail stores reference of tail element of list
    Node<T> header, tail;
    int size;

    public SinglyLinkedList() {
        header = new Node<>(null, null);
        tail = header;
        size = 0;
    }

    public Iterator<T> iterator() { return new SLLIterator<>(this); }

    private class SLLIterator<E> implements Iterator<E> {
        SinglyLinkedList<E> list;
        Node<E> cursor, prev;
        boolean ready;  // is item ready to be removed?

        SLLIterator(SinglyLinkedList<E> list) {
            this.list = list;
            cursor = list.header;
            prev = null;
            ready = false;
        }

        public boolean hasNext() {
            return cursor.next != null;
        }

        public E next() {
            prev = cursor;
            cursor = cursor.next;
            ready = true;
            return cursor.data;
        }

        // Removes the current element (retrieved by the most recent next())
        // Remove can be called only if next has been called and the element has not been removed
        public void remove() {
            if(!ready) {
                throw new NoSuchElementException();
            }
            prev.next = cursor.next;
            // Handle case when tail of a list is deleted
            if(cursor == list.tail) {
                list.tail = prev;
            }
            cursor = prev;
            ready = false;  // Calling remove again without calling next will result in exception thrown
            size--;
        }
    }

    // Add new elements to the end of the list
    public void add(T x) {
        tail.next = new Node<>(x, null);
        tail = tail.next;
        size++;
    }

    static void createIntegerList(int n, SinglyLinkedList<Integer> list){
        for (int i=1; i<=n; i++){
            list.add((int)(Math.random() * 10000));
        }
    }

    public void printList() {
	/* Code without using implicit iterator in for each loop:

        Node<T> x = header.next;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
	*/

        System.out.print(this.size + ": ");
        for(T item: this) {
            System.out.print(item + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) throws NoSuchElementException {
        System.out.println("Enter the number of elements:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
        createIntegerList(n, lst);
        lst.printList();

        Iterator<Integer> it = lst.iterator();
        Scanner in = new Scanner(System.in);
        whileloop:
        while(in.hasNext()) {
            int com = in.nextInt();
            switch(com) {
                case 1:  // Move to next element and print it
                    if (it.hasNext()) {
                        System.out.println(it.next());
                    } else {
                        break whileloop;
                    }
                    break;
                case 2:  // Remove element
                    it.remove();
                    lst.printList();
                    break;
                default:  // Exit loop
                    break whileloop;
            }
        }
        lst.printList();
    }
}

