/*	Short Project 7: Implementation of bottom-up Splay Tree
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */
package cs6301.g44;

import java.util.Comparator;

public class SplayTree<T extends Comparable<? super T>> extends BST<T> {
	
	SplayTree(){
		super();
		
	}
	
	public Entry<T> splay(Entry<T> node){
		Entry<T> gt, pt;
		Entry<T> tmp = root;
		while(node != root) {
			
			Entry<T> n = find(node.element);
//			pt = ancestors.pop();
//			gt = ancestors.pop();
			
			if(root.left.element.compareTo(node.element) == 0) {	//	Zig rotation
				node = rightRotate(root);
			}
			else if(root.right.element.compareTo(node.element) == 0) {
				node = leftRotate(root);
			}
			else if(tmp.left.left.element.compareTo(node.element) == 0) {	//	Zig-Zig rotation
				node = right2Rotate(tmp);
			}
			else if(tmp.right.right.element.compareTo(node.element) == 0) {
				node = left2Rotate(tmp);
			}
			else if(tmp.left.right.element.compareTo(node.element) == 0 ) {	//	Zig-Zag rotation
				node = leftrightRotate(tmp);
			}
			else if(tmp.right.left.element.compareTo(node.element) == 0) {
				node = rightleftRotate(tmp);
			}
		}
		 
		return node;
	}
	
	public boolean contains(T x) {
		Entry<T> t = find(x);
		Entry<T> node;
		if(t != null && t.element == x) {
			node = splay(t);
		}
		
		return true;
	}
	
	public Entry<T> rightRotate(Entry<T> node) {
		Entry<T> child = node.left;
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
}







