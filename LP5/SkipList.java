package cs6301.g44;

import java.util.Scanner;
import java.util.Random;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SkipList<T extends Comparable<? super T>> implements Iterable<T> {
	
	
	public class Entry<Z> {

		Z element;
		Entry<Z>[] next;
		int[] span;
		int level;
		Entry(Z data, int level) {
			element = data;
			this.level = level;
			next = (Entry<Z>[]) new Entry[level];
			span = new int[level];
			for(int i = 0; i < level; i++) {
				span[i] = 0;
				next[i] = null;
			}
		}
	}
	
	Entry<T> head, tail;
	int size;
	int maxLevel;
	int currMax=0;//current max level in skipList

	public SkipList() {
		head = new Entry<>(null, 31);
		tail=null;
		this.size = 0;
		this.maxLevel = 31;
	}

	SkipList(int level) {
		head = new Entry<>(null, level);
		tail=null;
		this.maxLevel = level;
	}
	
	public int chooseLevel(){
		int mask=(1<<currMax)-1;
		Random random=new Random();
		int level=Integer.numberOfTrailingZeros(random.nextInt()&mask);
		if(level>currMax)
			return currMax+1;
		else
			return level+1;
	}

	//Helper function find()
	//Returns the array of nodes 
	Entry<T>[] find(T data) {
		Entry<T>[] prev = (Entry<T>[]) new Entry[this.maxLevel];
		Entry<T> cursor = this.head;
		for(int i = maxLevel - 1; i >= 0; i--) {
			while((cursor.next[i] != null) && (cursor.next[i].element.compareTo(data) < 0))
				cursor = cursor.next[i];
			prev[i] = cursor;
		}
		return prev;	
	}	

	//Function to get the index of the given data 

	int getIndex(T data) {

		if(data == null)
			return -1;
		if(head.next[0].element.compareTo(data) == 0)
			return 0;
		int index = 0;
		int temp = 0;
		Entry<T> cursor = head;
		for(int i = maxLevel - 1; i >= 0; i--) {
			while((cursor.next[i] != null) && (cursor.next[i].element.compareTo(data) < 0)) {
				index = index + cursor.span[i] + temp;
				cursor = cursor.next[i];
				temp = 1;
			}
			if((cursor.next[i] != null) && (cursor.next[i].element.compareTo(data) == 0)) {
				index = index + cursor.span[i] + temp;
				break;
			}
		}
		return index;
	}

	// Add x to list. If x already exists, replace it. Returns true if new node is added to list
	public boolean add(T x) {
		int level = chooseLevel();
		if(level>maxLevel){
			level=maxLevel;
		}
		else
			level=++currMax;
		if(this.size == 0) {
			Entry<T> node = new Entry<>(x, level);
			for(int i = 0; i < level; i++)
				head.next[i] = node;
			for(int i = level ; i < maxLevel ; i++)
				head.span[i]++;
		}
		else {
			Entry<T>[] prev = find(x);
			if((prev[0].next[0] != null) && (prev[0].next[0].element.compareTo(x) == 0)) {
				prev[0].next[0].element = x;
				return false;
			}
			else {
				Entry<T> node = new Entry<T>(x, level);
				int nodeIndex = getIndex(prev[0].element) + 1;
				int index, initial;
				for(int i = 0; i < level; i++) {
					node.next[i] = prev[i].next[i];
					prev[i].next[i] = node;
					index = getIndex(prev[i].element);
					initial = prev[i].span[i];
					prev[i].span[i] = nodeIndex - index - 1;
					node.span[i] = initial - prev[i].span[i];
				}
				for(int i = level; i < maxLevel; i++)
					prev[i].span[i]++;
			}
		}
		this.size++;
		return true;
	}
	
	// Find smallest element that is greater or equal to x
	public T ceiling(T x) {
		Entry<T>[] prev = find(x);
		return prev[0].next[0].element;
	}
	
	// Does list contain x?
	public boolean contains(T x) {
		Entry<T>[] prev = find(x);
		return prev[0].next[0].element.compareTo(x) == 0;
	}

	// Return first element of list
	public T first() {
		return this.head.next[0].element;
	}
	
	// Find largest element that is less than or equal to x
	public T floor(T data) {
		Entry<T>[] prev = find(data);
		return prev[0].next[0].element.compareTo(data) == 0 ? prev[0].next[0].element : prev[0].element;
	}

	// Return element at index n of list.  First element is at index 0.
	public T get(int n) {
		int index = 0;
		Entry<T> cursor = head;
		int temp = 0;
		for(int i = maxLevel - 1 ; i >= 0; i--) {
			while((cursor.next[i] != null) && (index + cursor.span[i] + temp < n)) {
				index = index + cursor.span[i] + temp;
				cursor = cursor.next[i];
				temp = 1;
			}
			if(index + cursor.span[i] + temp == n)
				return cursor.next[i].element;
		}
		return null;
	}
	
	// Is the list empty?
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	// Iterate through the elements of list in sorted order
	public Iterator<T> iterator() {
		return new SkipListIterator<>(head);
	}

	private class SkipListIterator<E> implements Iterator<E> {
		Entry<E> cursor, prev;
		boolean ready;
		SkipListIterator(Entry<E> header) {
			cursor = header;
			prev = null;
			ready = false;
		}

		public boolean hasNext() {
			return cursor.next[0] != null;
		}

		public E next() {
			prev = cursor;
			cursor = cursor.next[0];
			ready = true;
			return cursor.element;
		}

		public void remove() {
			if(!ready) 
				throw new NoSuchElementException();
			prev.next[0] = cursor.next[0];
			cursor = cursor.next[0];
			ready = false;
			size--;
		}
	}
	
	// Return last element of list
	public T last() {
		return get(this.size - 1);
	}
	
	// Reorganize the elements of the list into a perfect skip list
	public void rebuild() {
		Entry<T>[] arr = (Entry<T>[]) new Entry[this.size];
		rebuild(arr, 0, this.size - 1, this.maxLevel + 1);
		int i = 0;
		SkipList<T> list = new SkipList<>(maxLevel + 1);
		Entry<T> cursor = this.head;
		for(T x : this) {
			arr[i].element = x;
			Entry<T>[] prev = list.find(x);
			for(int j = 0; j < arr[i].level; j++) {
				prev[j].next[j] = arr[i];
				arr[i].span[j] = ((int) Math.pow(2,j)) - 1;
			}
			i++;
		}
		this.head = list.head;
	}
	
	void rebuild(Entry<T>[] arr, int p, int r, int level) {
		if(p <= r) {
			if(level == 1) {
				for(int i = p; i <= r; i++)
					arr[i] = new Entry<T>(null, 1);
			}
			else {
				int mid = (p + r) >>> 1;
				arr[mid] = new Entry<>(null, level);
				rebuild(arr, p, mid - 1, level - 1);
				rebuild(arr, mid + 1, r, level - 1);
			}
		}
	}

	// Remove x from list.  Removed element is returned. Return null if x not in list
	public T remove(T x) {
		Entry<T>[] prev = find(x);
		if(prev[0].next[0].element.compareTo(x) == 0) {
			Entry<T> node = prev[0].next[0];
			for(int i = 0; i < node.level; i++) {
				prev[i].next[i] = node.next[i];
				prev[i].span[i] = prev[i].span[i] + node.span[i];
			}
			for(int i = node.level; i < maxLevel; i++)
				prev[i].span[i]--;
			this.size--;
			return x;
		}
		else
			return null;
	}

	// Return the number of elements in the list
	public int size() {
		return this.size;
	}

	void printList() {
		for(T x : this) {
			System.out.print(x + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SkipList<Integer> list = new SkipList<>();
		Scanner input = new Scanner(System.in);
		int size = 20;
		for(int i = 10; i < size; i++)
			list.add(i);
		System.out.println("Intial list");
		list.printList();
		list.remove(12);
		System.out.println("After removing element 12");
		list.printList();
		
		System.out.println("Does list contain 15? "+list.contains(15));
		System.out.println("List size: "+list.size());
		list.rebuild();
		System.out.println("List after rebuilding");
		list.printList();
	}
}
