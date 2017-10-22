/*	Short Project 7: Implementation of bottom-up Splay Tree
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */
package cs6301.g44;

import java.util.Comparator;

public class SplayTree<T extends Comparable<? super T>> extends BST<T> {
	
	static class Entry<T> extends BST.Entry<T>{
		Entry<T> parent;	//to keep the info of parent of particular node
		Entry(T x, Entry<T> left, Entry<T> right){
			super(x,left,right);
			parent = null;
		}
	}
	
	SplayTree(){
		super();
	}
	
	Entry<T> getSplay(BST.Entry<T> t){
		return (Entry<T>) t;
	}
	
	public void splay(Entry<T> node){
		
		while(node.parent != null) {
			
			Entry<T> p = node.parent;	//parent of the node
			Entry<T> gp = p.parent;		//grandparent of the node
			
			if(gp == null) {
				if(p.left.element.compareTo(node.element) == 0) {	//	Zig rotation
					rightRotate(node,p);
				}
				else if(p.right.element.compareTo(node.element) == 0) {
					leftRotate(node,p);
				}
			}
			else {
				if(gp.left.left.element.compareTo(node.element) == 0) {	//	Zig-Zig rotation
					rightRotate(p,gp);
					rightRotate(node,p);
				}
				else if(gp.right.right.element.compareTo(node.element) == 0) {
					leftRotate(p,gp);
					leftRotate(node,p);
				}
				else if(gp.left.right.element.compareTo(node.element) == 0 ) {	//	Zig-Zag rotation
					leftRotate(p,gp);
					rightRotate(node,p);
				}
				else if(gp.right.left.element.compareTo(node.element) == 0) {
					rightRotate(p,gp);
					leftRotate(node,p);
				}
			}
		}
		root = node; 
		
	}
	
	public void rightRotate(Entry<T> child, Entry<T> p) {
		//Entry<T> child = getSplay(p.left);
		if(p.parent != null) {
			if(p == p.parent.left)
				p.parent.left = child;
			else
				p.parent.right = child;
		}
		if(child.right != null)
			getSplay(child.right).parent = p;
		
		child.parent = p.parent;
		p.parent = child;
		p.left = child.right;
		child.right = p;
				
	}
	
	public void leftRotate(Entry<T> child, Entry<T> p) {
		//Entry<T> child = getSplay(p.right);
		if(p.parent != null) {
			if(p == p.parent.left)
				p.parent.left = child;
			else
				p.parent.right = child;
		}
		if(child.left != null)
			getSplay(child.left).parent = p;
		
		child.parent = p.parent;
		p.parent = child;
		p.right = child.left;
		child.left = p;
	}
	
//	public Entry<T> right2Rotate(Entry<T> gt){	//right-right
//		Entry<T> p = gt.left;
//		gt.left = p.right;
//		p.right = gt;
//		Entry<T> child = p.left;
//		p.left = child.right;
//		child.right = p;
//		return child;
//	}
//	
//	public Entry<T> left2Rotate(Entry<T> gt){	//left-left
//		Entry<T> p = gt.right;
//		gt.right = p.left;
//		p.left = gt;
//		Entry<T> child = p.right;
//		p.right = child.left;
//		child.left = p;
//		return child;
//	}
//	
//	public Entry<T> rightleftRotate(Entry<T> gt){	//right-left
//		Entry<T> p = gt.right;
//		Entry<T> child = p.left;
//		gt.right = child.left;
//		p.left = child.right;
//		child.left = gt;
//		child.right = p;
//		return child;
//	}
//	
//	public Entry<T> leftrightRotate(Entry<T> gt){	//left-right
//		Entry<T> p = gt.left;
//		Entry<T> child = p.right;
//		gt.left = child.right;
//		p.right = child.left;
//		child.left = p;
//		child.right = gt;
//		return child;
//	}
	
	public boolean contains(T x) {
		Entry<T> t = getSplay(find(x));
		if(t != null && t.element == x) {
			splay(t);
			return true;
		}
		else
			return false;
	}
	
	public boolean add(T x) {	// taken from AVLTree
		Entry<T> newElement = new Entry<T>(x, null, null);

		if (root == null) {
			root = newElement;
			newElement.parent = null;
			//size++;
		}

		Entry<T> t = getSplay(find(x));
		if (t.element.compareTo(x) == 0) {
			t.element = x;
			return false;
		} else if (x.compareTo(t.element) < 0) {
			t.left = newElement;
			newElement.parent = t;

		} else {
			t.right = newElement;
			newElement.parent = t;
		}

		//size++;
		splay(newElement);
		return true;
	}
	
	public T remove(T x) {
		Entry<T> node = getSplay(find(x));
		T result;
		result = node.element;
		if(node == null)
			return null;
		splay(node);
		if(node.left != null && node.right != null) {
			Entry<T> tmp = getSplay(node.left);
			while(tmp.right != null)
				tmp = getSplay(tmp.right);
			
			tmp.right = node.right;
			getSplay(node.right).parent = tmp;
			getSplay(node.left).parent = null;
			root = node.right;
			 
		}
		else if(node.right != null) {
			getSplay(node.right).parent = null;
			root = node.right;
		}
		else if(node.left != null) {
			getSplay(node.left).parent = null;
			root = node.left;
		}
		else {
			root = null;
		}
		node.parent = null;
        node.left = null;
        node.right = null;
        node = null;
        return result;
	}
	
	public static void main(String[] args) {
		SplayTree<Integer> t = new SplayTree<>();
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
}







