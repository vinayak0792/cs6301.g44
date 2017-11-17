
package cs6301.g44;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.TreeMap;

public class MDS {

	public MDS() {
	}

	public static class Pair {
		long id;
		int price;

		public Pair(long id, int price) {
			this.id = id;
			this.price = price;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;
			if (!(o instanceof Pair))
				return false;
			Pair p = (Pair) o;
			return id == p.id;
		}

	}

	HashMap<Long, HashSet<Long>> items = new HashMap<Long, HashSet<Long>>();
	HashMap<Long, Float> supplier = new HashMap<>();

	HashMap<Long, HashSet<Pair>> supplierItem = new HashMap<>();
	TreeMap<Float, HashSet<Long>> reputationSupplier = new TreeMap<>();

	HashMap<Long, HashSet<Long>> itemSupplier = new HashMap<>();
	HashMap<Long, HashSet<Long>> descriptionItemId = new HashMap<>();

	private void updateDesc(Long id, Long[] desc) {
		for (Long des : desc) {
			HashSet<Long> temp = new HashSet<>();
			if (descriptionItemId.containsKey(des)) {
				temp = descriptionItemId.get(des);
				temp.add(id);
			} else {
				temp.add(id);
			}
			descriptionItemId.put(des, temp);
		}
	}

	/*
	 * add a new item. If an entry with the same id already exists, the new
	 * description is merged with the existing description of the item. Returns
	 * true if the item is new, and false otherwise.
	 */
	public boolean add(Long id, Long[] description) {
		if (items.containsKey(id)) {
			HashSet<Long> temp = items.get(id);
			for (Long des : description)
				temp.add(des);
			updateDesc(id, description);
			return false;
		} else {
			HashSet<Long> temp = new HashSet<>();
			for (Long des : description)
				temp.add(des);
			items.put(id, temp);
			updateDesc(id, description);
		}
		return true;
	}

	/*
	 * add a new supplier (Long) and their reputation (float in [0.0-5.0],
	 * single decimal place). If the supplier exists, their reputation is
	 * replaced by the new value. Return true if the supplier is new, and false
	 * otherwise.
	 */
	public boolean add(Long supplier, float reputation) {
		return true;
	}

	/*
	 * add products and their prices at which the supplier sells the product. If
	 * there is an entry for the price of an id by the same supplier, then the
	 * price is replaced by the new price. Returns the number of new entries
	 * created.
	 */
	public int add(Long supplier, Pair[] idPrice) {
		return 0;
	}

	/*
	 * return an array with the description of id. Return null if there is no
	 * item with this id.
	 */
	public Long[] description(Long id) {
		return null;
	}

	/*
	 * given an array of Longs, return an array of items whose description
	 * contains one or more elements of the array, sorted by the number of
	 * elements of the array that are in the item's description (non-increasing
	 * order).
	 */
	public Long[] findItem(Long[] arr) {
		return null;
	}

	/*
	 * given a Long n, return an array of items whose description contains n,
	 * which have one or more suppliers whose reputation meets or exceeds the
	 * given minimum reputation, that sell that item at a price that falls
	 * within the price range [minPrice, maxPrice] given. Items should be sorted
	 * in order of their minimum price charged by a supplier for that item
	 * (non-decreasing order).
	 */
	public Long[] findItem(Long n, int minPrice, int maxPrice, float minReputation) {
		return null;
	}

	/*
	 * given an id, return an array of suppliers who sell that item, ordered by
	 * the price at which they sell the item (non-decreasing order).
	 */
	public Long[] findSupplier(Long id) {
		return null;
	}

	/*
	 * given an id and a minimum reputation, return an array of suppliers who
	 * sell that item, whose reputation meets or exceeds the given reputation.
	 * The array should be ordered by the price at which they sell the item
	 * (non-decreasing order).
	 */
	public Long[] findSupplier(Long id, float minReputation) {
		return null;
	}

	/*
	 * find suppliers selling 5 or more products, who have the same identical
	 * profile as another supplier: same reputation, and, sell the same set of
	 * products, at identical prices. This is a rare operation, so do not do
	 * additional work in the other operations so that this operation is fast.
	 * Creative solutions that are elegant and efficient will be awarded
	 * excellence credit. Return array of suppliers satisfying above condition.
	 * Make sure that each supplier appears only once in the returned array.
	 */
	public Long[] identical() {
		return null;
	}

	/*
	 * given an array of ids, find the total price of those items, if those
	 * items were purchased at the lowest prices, but only from sellers meeting
	 * or exceeding the given minimum reputation. Each item can be purchased
	 * from a different seller.
	 */
	public int invoice(Long[] arr, float minReputation) {
		return 0;
	}

	/*
	 * remove all items, all of whose suppliers have a reputation that is equal
	 * or lower than the given maximum reputation. Returns an array with the
	 * items removed.
	 */
	public Long[] purge(float maxReputation) {
		return null;
	}

	/*
	 * remove item from storage. Returns the sum of the Longs that are in the
	 * description of the item deleted (or 0, if such an id did not exist).
	 */
	public Long remove(Long id) {
		return 0L;
	}

	/*
	 * remove from the given id's description those elements that are in the
	 * given array. It is possible that some elements of the array are not part
	 * of the item's description. Return the number of elements that were
	 * actually removed from the description.
	 */
	public int remove(Long id, Long[] arr) {
		return 0;
	}

	/*
	 * remove the elements of the array from the description of all items.
	 * Return the number of items that lost one or more terms from their
	 * descriptions.
	 */
	public int removeAll(Long[] arr) {
		return 0;
	}
}
