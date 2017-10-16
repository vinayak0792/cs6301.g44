
/** Starter code for AVL Tree
 */
package cs6301.g44.SP7;

import java.util.Scanner;

public class AVLTree<T extends Comparable<? super T>> extends BST<T> {

	static class Entry<T> extends BST.Entry<T> {
		int height;

		Entry(T x, Entry<T> left, Entry<T> right) {
			super(x, left, right);
			height = 0;
		}
	}

	AVLTree() {
		super();
	}

	int height(Entry<T> h) {
		if (h == null)
			return 0;

		return h.height;
	}

	public Entry<T> rightRotate(Entry<T> node) {
		Entry<T> temp1 = getAVLEntry(node.left);
		Entry<T> temp2 = getAVLEntry(temp1.right);

		// rotation
		temp1.right = node;
		node.left = temp2;

		// updating height
		node.height = Math.max(height(getAVLEntry(node.left)), height(getAVLEntry(node.right))) + 1;
		temp1.height = Math.max(height(getAVLEntry(temp1.left)), height(getAVLEntry(temp1.right))) + 1;

		return temp1;
	}

	public Entry<T> leftRotate(Entry<T> node) {
		Entry<T> temp1 = getAVLEntry(node.right);
		Entry<T> temp2 = getAVLEntry(temp1.left);

		// rotation
		temp1.left = node;
		node.right = temp2;

		// updating height
		node.height = Math.max(height(getAVLEntry(node.left)), height(getAVLEntry(node.right))) + 1;
		temp1.height = Math.max(height(getAVLEntry(temp1.left)), height(getAVLEntry(temp1.right))) + 1;
		return temp1;

	}

	// Get Balance factor of node N
	public int getBalance(Entry<T> N) {
		if (N == null)
			return 0;
		return height(getAVLEntry(N.left)) - height(getAVLEntry(N.right));
	}

	void updateHeight(int i) {
		Entry<T> parent = getAVLEntry(ancestors.peek());
		if (parent == null) {
			return;
		} else if (parent.left == null || parent.right == null) {
			for (BST.Entry<T> t : ancestors) {
				if(t != null)
					getAVLEntry(t).height += i;
			}
		}
	}

	Entry<T> getAVLEntry(BST.Entry<T> t) {
		return (Entry<T>) t;
	}

	void repair(int i, T x) {
		// System.out.println("\nroot height Before " +
		// getAVLEntry(root).height);
		updateHeight(i);

		// System.out.println("root height after " + getAVLEntry(root).height);
		int balance = getBalance(getAVLEntry(root));

		if (balance > 1 && x.compareTo(root.left.element) < 0)
			root = rightRotate(getAVLEntry(root));

		// Right Right Case
		if (balance < -1 && x.compareTo(root.right.element) > 0)
			root = leftRotate(getAVLEntry(root));

		// Left Right Case
		if (balance > 1 && x.compareTo(root.left.element) > 0) {
			root.left = leftRotate(getAVLEntry(root.left));
			root = rightRotate(getAVLEntry(root));
		}

		// Right Left Case
		if (balance < -1 && x.compareTo(root.right.element) < 0) {
			root.right = rightRotate(getAVLEntry(root.right));
			root = leftRotate(getAVLEntry(root));
		}
	}

	public boolean add(T x) {
		Entry<T> newElement = new Entry<T>(x, null, null);

		if (root == null) {
			root = newElement;
			size++;
		}

		Entry<T> t = getAVLEntry(find(x));
		if (t.element.compareTo(x) == 0) {
			t.element = x;
			return false;
		} else if (x.compareTo(t.element) < 0) {
			t.left = newElement;

		} else {
			t.right = newElement;
		}

		size++;
		//repair(1, x);
		return true;
	}

	public static void main(String[] args) {
		AVLTree<Integer> t = new AVLTree<>();
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int x = in.nextInt();
			if (x > 0) {
				System.out.print("Add " + x + " : ");
				t.add(x);
				t.repair(1, x);
				System.out.println(t.root.element);
				t.printTree();
			} else if (x < 0) {
				System.out.print("Remove " + x + " : ");
				t.remove(-x);
				t.repair(-1, x);
				System.out.println(t.root.element);
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
}
