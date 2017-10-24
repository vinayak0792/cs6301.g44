/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * Red Black Tree
 */
/** Starter code for Red-Black Tree
 */
package cs6301.g44;

import java.util.Comparator;
import java.util.Scanner;

public class RedBlackTree<T extends Comparable<? super T>> extends BST<T> {
    static class Entry<T> extends BST.Entry<T> {
        boolean isRed;

        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            isRed = true;
        }
    }

    RedBlackTree() {
        super();
    }


    public boolean add(T x) {
        Entry<T> newElement = new Entry<T>(x, null, null);
        if (super.add(newElement) && size > 1) {
            repair(x);
            return true;
        } else
            return false;
    }


    //Repair after insertion
    private void repair(T cur) {
        Entry<T> node = (Entry<T>) find(cur);
        Entry<T> parent = this.ancestors.peek() == null ? null : (Entry<T>) this.ancestors.pop();
        if (parent == this.root) {
            parent.isRed = false;
            return;
        }
        Entry<T> grandParent = this.ancestors.peek() == null ? null : (Entry<T>) this.ancestors.pop();
        Entry<T> greatGP = this.ancestors.peek() == null ? null : (Entry<T>) this.ancestors.peek();
        Entry<T> uncle = getSibling(parent, grandParent);

        //while (node.isRed) {
        if (node != null) {
            Entry<T> root = (Entry<T>) this.root;
            if (node == root || parent == root || isBlack(parent)) {
                assignColor(root, false);
                //root.isRed = false;
                return;
            }
            if (uncle != null && isRed(uncle)) {
                assignColor(parent, false);
                assignColor(uncle, false);
                assignColor(grandParent, true);
                repair(grandParent.element);

            } else if (isRed(parent) && (uncle == null || isBlack(uncle))) {
                //LL rotation
                if (node == getLeft(parent) && parent == getLeft(grandParent)) {
                    LLRotation(parent, grandParent, greatGP);
                }
                //RR rotation
                else if (node == getRight(parent) && parent == getRight(grandParent)) {
                    RRRotation(parent, grandParent, greatGP);
                }

                //LR rotation
                else if (parent == grandParent.left && node == parent.right) {
                    grandParent.left = rotateLeft(parent);
                    LLRotation(node, grandParent, greatGP);
                }
                //RL rotation
                else if (parent == grandParent.right && node == parent.left) {
                    grandParent.right = rotateRight(parent);
                    RRRotation(node, grandParent, greatGP);
                 }
            }
        }

    }

    private void LLRotation(Entry<T> parent, Entry<T> grandParent, Entry<T> greatGP) {
        if (greatGP == null)
            this.root = rotateRight(grandParent);
        else
            greatGP.right = rotateRight(grandParent);
        assignColor(parent, false);
        assignColor(grandParent, true);

    }

    private void RRRotation(Entry<T> parent, Entry<T> grandParent, Entry<T> greatGP) {
        if (greatGP == null)
            this.root = rotateLeft(grandParent);
        else greatGP.left =
                rotateLeft(grandParent);
        assignColor(parent, false);
        assignColor(grandParent, true);
    }

    private Entry<T> rotateRight(Entry<T> node) {
        Entry<T> temp = (Entry<T>) getLeft(node);
        Entry<T> temp2 = (Entry<T>) getRight(temp);
        temp.right = node;
        node.left = temp2;
        return temp;
    }

    private Entry<T> rotateLeft(Entry<T> node) {
        Entry<T> temp = (Entry<T>) getRight(node);
        Entry<T> temp2 = (Entry<T>) getLeft(temp);
        temp.left = node;
        node.right = temp2;
        return temp;
    }

    Entry<T> getEntry(BST.Entry node) {
        return (Entry<T>) node;
    }

    public T remove(T x) {
        T result = super.remove(x);
        Entry<T> bynode = getEntry(ancestors.peek());
        if (result != null){
            fix(getEntry(bynode));
            return x;
        } else
            return null;
    }

    private void updateParent(Entry<T> parent, Entry<T> oldParent) {
        Entry<T> grandParent = getEntry(ancestors.peek());
        if (grandParent == null)
            root = parent;
        else if (grandParent.left == oldParent)
            grandParent.left = parent;
        else
            grandParent.right = parent;
    }

    private Entry<T> getLeft(Entry<T> node){
        return node==null?null:(Entry<T>)node.left;
    }

    private Entry<T> getRight(Entry<T> node) {
        return node==null?null:(Entry<T>)node.right;
    }

    private boolean isRed(Entry<T> node) {
        return node != null && node .isRed;
    }

    private boolean isBlack(Entry<T> node) {
        return node != null && !node .isRed;
    }

    private void assignColor(Entry<T> node, boolean val) {
        if (node != null)
            node.isRed = val;
    }
    private Entry<T> getSibling(Entry<T> node, Entry<T> parent){
        return getLeft(parent)==node? getRight(parent):getLeft(parent);
    }

        private void fix(Entry<T> cur){
        Entry<T> node = cur!=null?(Entry<T>) find(cur.element): null;
        Entry<T> parent;
        Entry<T> sibling;
        Entry<T> rc ;
        while (node != this.root && !this.ancestors.isEmpty() && isBlack(node)){
            parent = (Entry<T>) this.ancestors.pop();
            sibling = getSibling(node, parent);
            if (node == getLeft(parent)){
                if (isRed(sibling)){
                    assignColor(sibling, false);
                    assignColor(parent, true);
                    updateParent(rotateLeft(parent), parent);
                    sibling = getEntry(parent.right);
                }
                    if (isBlack(getLeft(sibling)) && isBlack(getRight(sibling))){
                        assignColor(sibling,true);
                        node = parent;
                    }
                     else {
                        if (isBlack(getRight(sibling))){
                            assignColor(getLeft(sibling),false);
                            assignColor(sibling, true);
                            updateParent(rotateRight(sibling), sibling);
                            sibling = getRight(parent);
                        }
                        exchangeColors(sibling, parent);
                        assignColor(parent, false);
                        assignColor(getRight(sibling), false);
                        updateParent(rotateLeft(parent), parent);
                        node = (Entry<T>) this.root;
                    }

            }else {
                if (isRed(sibling)){
                    assignColor(sibling, false);
                    assignColor(parent, true);
                    updateParent(rotateRight(parent), parent);
                    sibling = getLeft(parent);
                }
                if (isBlack(getLeft(sibling)) && isBlack(getRight(sibling))){
                    assignColor(sibling,true);
                    node = parent;
                } else {
                        if (isBlack(getLeft(sibling))){
                            assignColor(getRight(sibling), false);
                            assignColor(sibling, true);
                            updateParent(rotateLeft(sibling), sibling);
                            sibling = getLeft(parent);
                        }
                        exchangeColors(sibling, parent);
                        assignColor(parent, false);
                        assignColor(getLeft(sibling), false);
                        updateParent(rotateRight(parent), parent);
                        node = (Entry<T>) this.root;
                    }
            }
        }
        assignColor(node,false);
    }

    public void exchangeColors(Entry<T> node1, Entry<T> node2){
        boolean temp = node1.isRed;
        node1.isRed = node2.isRed;
        node2.isRed = temp;
    }

    public static void main(String[] args){
        RedBlackTree<Integer> obj = new RedBlackTree<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int x = scanner.nextInt();
            if (x > 0) {
                System.out.print("Add " + x + " : ");
                obj.add(x);
                System.out.println(obj.root.element);
                obj.printTree();
            } else if (x < 0) {
                System.out.print("Remove " + x + " : ");
                obj.remove(-x);
                System.out.println(obj.root.element);
                obj.printTree();
            } else {
                Comparable<Integer>[] arr = obj.toArray();
                System.out.print("Final: ");
                for (int i = 0; i < obj.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                return;
            }
        }
    }
}

