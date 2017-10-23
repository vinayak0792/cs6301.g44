package cs6301.g44.SP7;
/**
 * This program implements the AVL Tree
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
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

	// Helper function that performs Right Rotation.
	private Entry<T> rightRotate(Entry<T> node) {
		Entry<T> temp1 = getAVLEntry(node.left);
		Entry<T> temp2 = getAVLEntry(temp1.right);

		temp1.right = node;
		node.left = temp2;

		// Updating the height of the subtree after rotation is completed.
		node.height = Math.max(getHeight(getAVLEntry(node.left)), getHeight(getAVLEntry(node.right))) + 1;
		temp1.height = Math.max(getHeight(getAVLEntry(temp1.left)), getHeight(getAVLEntry(temp1.right))) + 1;

		return temp1;
	}

	private Entry<T> leftRotate(Entry<T> node) {
		Entry<T> temp1 = getAVLEntry(node.right);
		Entry<T> temp2 = getAVLEntry(temp1.left);

		temp1.left = node;
		node.right = temp2;

		// Updating the height of the subtree after rotation is completed.
		node.height = Math.max(getHeight(getAVLEntry(node.left)), getHeight(getAVLEntry(node.right))) + 1;
		temp1.height = Math.max(getHeight(getAVLEntry(temp1.left)), getHeight(getAVLEntry(temp1.right))) + 1;
		return temp1;

	}

	// Helper function that returns the height of the tree.
	private int getHeight(Entry<T> h) {
		if (h == null)
			return -1;

		return h.height;
	}

	// Helper function that calculates the balance factor of the subtree
	private int getBalance(Entry<T> parent) {
		if (parent == null)
			return 0;

		return getHeight(getLeftChild(parent)) - getHeight(getRightChild(parent));
	}

	// Helper function that returns the equivalent AVLTree.Entry for the
	// BST.Entry
	Entry<T> getAVLEntry(BST.Entry<T> t) {
		return (Entry<T>) t;
	}

	// Helper function that returns the AVLTree.Entry type left child for
	// BST.Entry type
	Entry<T> getLeftChild(Entry<T> parent) {
		return getAVLEntry(parent.left);
	}

	// Helper function that returns the AVLTree.Entry type right child for
	// BST.Entry type
	Entry<T> getRightChild(Entry<T> parent) {
		return getAVLEntry(parent.right);
	}

	// Helper function that points the grandParent of the node after the
	// respective rotation
	private void updateParent(Entry<T> parent, T element) {
		Entry<T> grandParent = getAVLEntry(ancestors.peek());
		if (grandParent == null)
			root = parent;
		else if (grandParent.left.element == element)
			grandParent.left = parent;
		else
			grandParent.right = parent;
	}

	// Helper function that performs rotation at the lowest node after deleting
	// an entry from the tree.
	private void repairDel(T x) {
		while (ancestors.peek() != null && !ancestors.isEmpty()) {
			Entry<T> parent = getAVLEntry(ancestors.pop());
			Entry<T> leftChild = getLeftChild(parent);
			Entry<T> rightChild = getRightChild(parent);
			int balance = getBalance(parent);

			T element = parent.element;

			// Left left Case
			if (balance > 1 && getBalance(leftChild) >= 0) {
				parent = rightRotate(getAVLEntry(parent));
				updateParent(parent, element);
			}
			// Right Right Case
			else if (balance < -1 && getBalance(rightChild) <= 0) {
				parent = leftRotate(getAVLEntry(parent));
				updateParent(parent, element);

			}

			// Left Right Case
			else if (balance > 1 && getBalance(leftChild) < 0) {
				parent.left = leftRotate(getAVLEntry(parent.left));
				parent = rightRotate(getAVLEntry(parent));
				updateParent(parent, element);
			}

			// Right Left Case
			else if (balance < -1 && getBalance(rightChild) > 0) {
				parent.right = rightRotate(getAVLEntry(parent.right));
				parent = leftRotate(getAVLEntry(parent));
				updateParent(parent, element);
			}
			parent.height = 1 + Math.max(getHeight(getAVLEntry(parent.left)), getHeight(getAVLEntry(parent.right)));

		}

	}

	// Helper function that performs rotation at the lowest node after adding an
	// entry from the tree.
	private void repairAdd(T x) {
		while (ancestors.peek() != null && !ancestors.isEmpty()) {
			Entry<T> parent = getAVLEntry(ancestors.pop());

			int balance = getBalance(parent);

			T element = parent.element;
			// Left left Case
			if (balance > 1 && x.compareTo(parent.left.element) < 0) {
				parent = rightRotate(getAVLEntry(parent));
				updateParent(parent, element);
			}
			// Right Right Case
			else if (balance < -1 && x.compareTo(parent.right.element) > 0) {
				parent = leftRotate(getAVLEntry(parent));
				updateParent(parent, element);

			}

			// Left Right Case
			else if (balance > 1 && x.compareTo(parent.left.element) > 0) {
				parent.left = leftRotate(getAVLEntry(parent.left));
				parent = rightRotate(getAVLEntry(parent));
				updateParent(parent, element);
			}

			// Right Left Case
			else if (balance < -1 && x.compareTo(parent.right.element) < 0) {
				parent.right = rightRotate(getAVLEntry(parent.right));
				parent = leftRotate(getAVLEntry(parent));
				updateParent(parent, element);
			}
			parent.height = 1 + Math.max(getHeight(getAVLEntry(parent.left)), getHeight(getAVLEntry(parent.right)));

		}

	}

	// Function to add a new element to the tree.
	public boolean add(T x) {
		Entry<T> newElement = new Entry<T>(x, null, null);
		if (super.add(newElement)) {
			if (size > 1)
				repairAdd(x);
			return true;
		} else
			return false;
	}

	// Function to delete a new element to the tree.
	public T remove(T x) {
		T result = super.remove(x);
		if (result != null) {
			if (ancestors.peek() != null)
				repairDel(ancestors.peek().element);
			return result;
		} else
			return null;
	}

	public static void main(String[] args) {
		AVLTree<Integer> t = new AVLTree<>();
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int x = in.nextInt();
			if (x > 0) {
				System.out.print("Add " + x + " : ");
				t.add(x);
				t.printTree();
				System.out.println("Is the tree balanced ? " + t.isBalanced(t.getAVLEntry(t.root)));
			} else if (x < 0) {
				System.out.print("Remove " + x + " : ");
				t.remove(-x);
				t.printTree();
				System.out.println("Is the tree balanced ? " + t.isBalanced(t.getAVLEntry(t.root)));
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

	// Function that checks if the tree is balanced post addition/deletion of an
	// element.
	private boolean isBalanced(Entry<T> root) {
		int leftHeight;
		int rightHeight;
		if (root == null)
			return true;
		leftHeight = getHeight(getAVLEntry(root.left));
		rightHeight = getHeight(getAVLEntry(root.right));

		if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(getAVLEntry(root.left))
				&& isBalanced(getAVLEntry(root.right))) {
			return true;
		} else
			return false;

	}
}
