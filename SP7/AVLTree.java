package cs6301.g44;

import java.util.Scanner;

import cs6301.g44.BST.Entry;

public class AVLTree<T extends Comparable<? super T>> extends BST<T> {

	static class Entry<T> extends BST.Entry<T> {
		int height;

		Entry(T x, Entry<T> left, Entry<T> right) {
			super(x, left, right);
			height = 1;
		}
	}

	int height(Entry<T> h) {
		if (h == null)
			return 0;

		return h.height;
	}

	Entry<T> getAVLEntry(BST.Entry<T> node) {
		return (Entry<T>) node;
	}

	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public Entry<T> rightRotate(Entry<T> node) {
		Entry<T> temp1 = getAVLEntry(node.left);
		Entry<T> temp2 = getAVLEntry(temp1.right);

		// rotation
		temp1.right = node;
		node.left = temp2;

		// updating height
		node.height = max(height(getAVLEntry(node.left)), height(getAVLEntry(node.right))) + 1;
		temp1.height = max(height(getAVLEntry(temp1.left)), height(getAVLEntry(temp1.right))) + 1;

		return temp1;
	}

	public Entry<T> leftRotate(Entry<T> node) {
		Entry<T> temp1 = getAVLEntry(node.right);
		Entry<T> temp2 = getAVLEntry(temp1.left);

		// rotation
		temp1.left = node;
		node.right = temp2;

		// updating height
		node.height = max(height(getAVLEntry(node.left)), height(getAVLEntry(node.right))) + 1;
		temp1.height = max(height(getAVLEntry(temp1.left)), height(getAVLEntry(temp1.right))) + 1;
		return temp1;

	}

	// Get Balance factor of node N
	public int getBalance(Entry<T> N) {
		if (N == null)
			return 0;
		System.out.println("Balance "+ (height(getAVLEntry(N.left)) - height(getAVLEntry(N.right))) );
		return height(getAVLEntry(N.left)) - height(getAVLEntry(N.right));
	}

	
	void updateHeight() {
		Entry<T> parent=getAVLEntry(ancestors.peek());
		
		if(parent.left == null || parent.right == null){
			for (BST.Entry<T> t : ancestors) {
				getAVLEntry(t).height++;
			}
		}
	}
	
	@Override
	public boolean add(T x) {
		ancestors.clear();
		Entry<T> newElement = new Entry<T>(x, null, null);

		if (root == null) {
			root = newElement;
			size++;
		}

		Entry<T> t = getAVLEntry(find(x));
		if (t.element.compareTo(x) == 0) {
			t.element = x;
			return false;
		} else if (x.compareTo(t.element) < 0){
			t.left = newElement;
			
		}
		else{
			t.right = newElement;
		}
			
		size++;
		System.out.println("\nroot height Before "+getAVLEntry(root).height);
		updateHeight();
		
		System.out.println("root height after "+getAVLEntry(root).height);
		int balance = getBalance(getAVLEntry(root));

		if (balance > 1 && x.compareTo(root.left.element) < 0)
			root=rightRotate(getAVLEntry(root));

		// Right Right Case
		if (balance < -1 && x.compareTo(root.right.element) > 0)
			root=leftRotate(getAVLEntry(root));

		// Left Right Case
		if (balance > 1 && x.compareTo(root.left.element) > 0) {
			root.left=leftRotate(getAVLEntry(root.left));
			root=rightRotate(getAVLEntry(root));
		}

		// Right Left Case
		if (balance < -1 && x.compareTo(root.right.element) < 0) {
			root.right=rightRotate(getAVLEntry(root.right));
			root=leftRotate(getAVLEntry(root));
		}
		
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
				System.out.println("Root Element "+t.root.element);
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

}
