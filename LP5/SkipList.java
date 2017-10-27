
// Change this to your group number
package cs6301.g44.LP5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

// Skeleton for skip list implementation.

public class SkipList<T extends Comparable<? super T>> {

	static class Entry<T> {
		T element;
		List<Entry<T>> next;

		/**
		 * Constructor for the Entry
		 * 
		 * @param element
		 *            : T - data of the Entry.
		 * @param next
		 *            : pointer - link to the next Entry.
		 */
		Entry(T element, int level) {
			this.element = element;
			next = new ArrayList<>(level);
		}
	}

	Entry<T> head;
	Entry<T> tail;
	int maxLevel;
	int size;

	/**
	 * Constructor for the Entry
	 * 
	 * @param element
	 *            : T - data of the Entry.
	 * @param next
	 *            : pointer - link to the next Entry.
	 */
	public SkipList() {
		head = new Entry(null, 32);
		tail = new Entry(null, 32);
		maxLevel = 0;
		size = 0;

	}

	private List<Entry<T>> find(T x) {
		List<Entry<T>> perv = new ArrayList<>();
		Entry<T> p = head;
		for (int i = maxLevel; i >= 0; i--) {
			while (p.next.get(i).element.compareTo(x) < 0) {
				p = p.next.get(i);
			}
			perv.add(i, p);
		}
		return perv;
	}

	private int chooseLevel() {
		Random r = new Random();
		int mask = (1 << maxLevel) - 1;
		int level = Integer.numberOfTrailingZeros(r.nextInt() & mask);
		if (level > maxLevel)
			return maxLevel++;
		else
			return level;
	}

	// Add x to list. If x already exists, replace it. Returns true if new node
	// is added to list
	public boolean add(T x) {

		List<Entry<T>> perv = find(x);

		if (perv.get(0).next.get(0).element.compareTo(x) == 0) {
			perv.get(0).next.get(0).element = x;
			return false;
		} else {
			int level = chooseLevel();
			Entry<T> newElement = new Entry<T>(x, level);
			for (int i = 0; i < level; i++) {
				newElement.next.get(i).next = perv.get(i).next.get(i).next;
				//perv.get(i).next.get(i).next = newElement;

			}
		}

		return true;
	}

	// Find smallest element that is greater or equal to x
	public T ceiling(T x) {
		return null;
	}

	// Does list contain x?
	public boolean contains(T x) {
		return false;
	}

	// Return first element of list
	public T first() {
		return null;
	}

	// Find largest element that is less than or equal to x
	public T floor(T x) {
		return null;
	}

	// Return element at index n of list. First element is at index 0.
	public T get(int n) {
		return null;
	}

	// Is the list empty?
	public boolean isEmpty() {
		return false;
	}

	// Iterate through the elements of list in sorted order
	public Iterator<T> iterator() {
		return null;
	}

	// Return last element of list
	public T last() {
		return null;
	}

	// Reorganize the elements of the list into a perfect skip list
	public void rebuild() {

	}

	// Remove x from list. Removed element is returned. Return null if x not in
	// list
	public T remove(T x) {
		return null;
	}

	// Return the number of elements in the list
	public int size() {
		return 0;
	}

	public static void main(String[] args) {
		SkipList<Integer> s = new SkipList<Integer>();
		for (int i = 0; i < 100; i++)
			System.out.println(s.chooseLevel());
	}
}
