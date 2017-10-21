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
			
			if(root.left.element == node.element || root.right == node) {	//	Zig rotation
				if(root.left == node)
					node = rightRotate(root);
				else
					node = leftRotate(root);
				
			}
			else if()
			(tmp.left.left == node || tmp.right.right == node) {	//	Zig-Zig rotation
				
			}
			else if(tmp.left.right == node || tmp.right.left == node) {	//	Zig-Zag rotation
				
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
	
	public Entry<T> rightleftRotate(Entry<T> node){
		Entry<T> child = node.left;
		return child;
	}
	
	public Entry<T> leftrightRotate(Entry<T> node){
		Entry<T> child = node.left;
		return child;
	}
}








