
/** @author 
 *  Binary search tree map (starter code)
 *  Implement this class using one of the BST implementations: BST, AVLTree, RedBlackTree, or, SplayTree.
 *  Do not use TreeMap or any of Java's maps.
 **/

package cs6301.g00;

import java.util.Comparator;
import java.util.Iterator;

public class BSTMap<K extends Comparable<? super K>, V> implements Iterable<K> {
    BSTMap() {
    }

    public V get(K key) {
	return null;
    }

    public boolean put(K key, V value) {
	return false;
    }

    // Iterate over the keys stored in the map, in order
    public Iterator<K> iterator() {
	return null;
    }
}
