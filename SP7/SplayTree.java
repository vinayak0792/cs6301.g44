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
	
	public Entry<T> splay(Entry<T> node){
		
		while(node.parent != null) {
			
			Entry<T> p = node.parent;	//parent of the node
			Entry<T> gp = p.parent;		//grandparent of the node
			
			if(gp == null) {
				if(p.left.element.compareTo(node.element) == 0) {	//	Zig rotation
					node = rightRotate(p);
				}
				else if(p.right.element.compareTo(node.element) == 0) {
					node = leftRotate(p);
				}
			}
			else {
				if(gp.left.left.element.compareTo(node.element) == 0) {	//	Zig-Zig rotation
					node = right2Rotate(gp);
				}
				else if(gp.right.right.element.compareTo(node.element) == 0) {
					node = left2Rotate(gp);
				}
				else if(gp.left.right.element.compareTo(node.element) == 0 ) {	//	Zig-Zag rotation
					node = leftrightRotate(gp);
				}
				else if(gp.right.left.element.compareTo(node.element) == 0) {
					node = rightleftRotate(gp);
				}
			}
		}
		root = node; 
		return node;
	}
	
	public Entry<T> rightRotate(Entry<T> node) {
		Entry<T> child = getSplay(node.left);
		node.left = child.right;
		child.right = node;
		return child;
		
	}
	
	public Entry<T> leftRotate(Entry<T> node) {
		Entry<T> child = node.right;
		node.right = child.left;
		child.left = node;
		return child;
	}
	
	public Entry<T> right2Rotate(Entry<T> gt){	//right-right
		Entry<T> p = gt.left;
		gt.left = p.right;
		p.right = gt;
		Entry<T> child = p.left;
		p.left = child.right;
		child.right = p;
		return child;
	}
	
	public Entry<T> left2Rotate(Entry<T> gt){	//left-left
		Entry<T> p = gt.right;
		gt.right = p.left;
		p.left = gt;
		Entry<T> child = p.right;
		p.right = child.left;
		child.left = p;
		return child;
	}
	
	public Entry<T> rightleftRotate(Entry<T> gt){	//right-left
		Entry<T> p = gt.right;
		Entry<T> child = p.left;
		gt.right = child.left;
		p.left = child.right;
		child.left = gt;
		child.right = p;
		return child;
	}
	
	public Entry<T> leftrightRotate(Entry<T> gt){	//left-right
		Entry<T> p = gt.left;
		Entry<T> child = p.right;
		gt.left = child.right;
		p.right = child.left;
		child.left = p;
		child.right = gt;
		return child;
	}
	
	public boolean contains(T x) {
		Entry<T> t = getSplay(find(x));
		if(t != null && t.element == x) {
			splay(t);
		}
		
		return true;
	}
	
	public boolean add(T x) {	// taken from AVLTree
		Entry<T> newElement = new Entry<T>(x, null, null);

		if (root == null) {
			root = newElement;
			newElement.parent = null;
			size++;
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

		size++;
		splay(newElement);
		return true;
	}
	
	public static void main(String[] args) {
		SplayTree<Integer> st = new SplayTree<>();
		//st.add(1);
	}
}







