package cs6301.g44.SP7;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements Iterable<T> {
	static class Entry<T> {
		T element;
		Entry<T> left, right;

		Entry(T x, Entry<T> left, Entry<T> right) {
			this.element = x;
			this.left = left;
			this.right = right;
		}
	}

	Entry<T> root;
	int size;
	ArrayDeque<Entry<T>> ancestors = new ArrayDeque<>();

	public BST() {
		root = null;
		size = 0;
	}

	// Find x in tree. Returns node where search ends.
	Entry<T> find(T x) {
		return find(root, x);
	}

	// Helper function that looks for an element X in the BST.
	// ancestor.peek() is parent of the root.
	Entry<T> find(Entry<T> t, T x) {
		if (t == null || t.element == x)
			return t;
		while (true) {
			if (x.compareTo(t.element) < 0) {
				if (t.left == null)
					break;
				else {
					ancestors.push(t);
					t = t.left;
				}
			} else if (t.element.compareTo(x) == 0)
				break;
			else {
				if (t.right == null)
					break;
				else {
					ancestors.push(t);
					t = t.right;
				}
			}
		}
		return t;
	}

	/**
	 * TO DO: Is x contained in tree?
	 */
	public boolean contains(T x) {
		Entry<T> t = find(x);
		return (t != null && t.element == x);
	}

	/**
	 * TO DO: Is there an element that is equal to x in the tree? Element in
	 * tree that is equal to x is returned, null otherwise.
	 */
	public T get(T x) {
		Entry<T> ele = find(root, x);
		if (ele.element.compareTo(x) == 0)
			return ele.element;
		else
			return null;
	}

	/**
	 * TO DO: Add x to tree. If tree contains a node with same key, replace
	 * element by x. Returns true if x is a new element added to tree.
	 */
	public boolean add(T x) {
		Entry<T> newElement = new Entry<T>(x, null, null);
		if (root == null) {
			root = newElement;
			size++;
		}
		Entry<T> t = find(x);
		if (t.element.compareTo(x) == 0) {
			t.element = x;
			return false;
		} else if (x.compareTo(t.element) < 0)
			t.left = newElement;
		else
			t.right = newElement;
		size++;
		return true;
	}

	/**
	 * TO DO: Remove x from tree. Return x if found, otherwise return null
	 */
	public T remove(T x) {
		ancestors.removeAll(ancestors);
		if (root == null) {
			return null;
		}

		Entry<T> t = find(x);
		if (t.element.compareTo(x) != 0) {
			return null;
		}
		T result = t.element;
		if (t.left == null || t.right == null)
			bypass(t);
		else {
			ancestors.push(t);
			Entry<T> minRight = find(t.right, t.element);
			t.element = minRight.element;
			// ancestors.push(t);
			bypass(minRight);
		}
		size--;
		return result;
	}

	void bypass(Entry<T> t) {
		Entry<T> pt = ancestors.peek();
		Entry<T> c = t.left == null ? t.right : t.left;
		if (pt == null)
			root = c;
		else if (pt.left.element.compareTo(t.element) == 0) {
			pt.left = c;
		} else
			pt.right = c;
	}

	/**
	 * TO DO: Iterate elements in sorted order of keys
	 */
	public Iterator<T> iterator() {
		return new BSTIterator();
	}

	private class BSTIterator implements Iterator<T> {
		Stack<Entry<T>> ancestors = new Stack<Entry<T>>();

		public BSTIterator() {
			min(root);
		}

		void min(Entry<T> node) {
			while (node != null) {
				ancestors.push(node);
				node = node.left;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return !ancestors.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			Entry<T> node = ancestors.pop();
			min(node.right);
			return node.element;
		}

	}

	public static void main(String[] args) {
		BST<Integer> t = new BST<>();
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int x = in.nextInt();
			if (x > 0) {
				System.out.print("Add " + x + " : ");
				t.add(x);
				t.printTree();
			} else if (x < 0) {
				System.out.print("Remove " + x + " : ");
				t.remove(-x);
				t.printTree();
			} else {
				Comparable<Integer>[] arr = t.toArray();
				System.out.print("Final: ");
				for (int i = 0; i < t.size; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
				return;
			}
		}
	}

	// TODO: Create an array with the elements using in-order traversal of tree
	public Comparable<T>[] toArray() {
		Comparable[] arr = new Comparable[size];
		Iterator<T> it = iterator();
		int i = 0;
		while (it.hasNext()) {
			arr[i++] = it.next();
		}
		return arr;
	}

	public void printTree() {
		System.out.print("[" + size + "]");
		printTree(root);
		System.out.println();
	}

	// Inorder traversal of tree
	void printTree(Entry<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.print(" " + node.element);
			printTree(node.right);
		}
	}

}
/*
 * Sample input: 1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0
 * 
 * Output: Add 1 : [1] 1 Add 3 : [2] 1 3 Add 5 : [3] 1 3 5 Add 7 : [4] 1 3 5 7
 * Add 9 : [5] 1 3 5 7 9 Add 2 : [6] 1 2 3 5 7 9 Add 4 : [7] 1 2 3 4 5 7 9 Add 6
 * : [8] 1 2 3 4 5 6 7 9 Add 8 : [9] 1 2 3 4 5 6 7 8 9 Add 10 : [10] 1 2 3 4 5 6
 * 7 8 9 10 Remove -3 : [9] 1 2 4 5 6 7 8 9 10 Remove -6 : [8] 1 2 4 5 7 8 9 10
 * Remove -3 : [8] 1 2 4 5 7 8 9 10 Final: 1 2 4 5 7 8 9 10
 * 
 */
