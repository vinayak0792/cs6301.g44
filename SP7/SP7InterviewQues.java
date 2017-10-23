package cs6301.g44.SP7;

/**
 * This program includes the code for SP7 Q6, Q7, Q8
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class SP7InterviewQues {

	// Function to compute elements that occur exactly once in the given array.
	static <T extends Comparable<? super T>> T[] exactlyOnce(T[] A) {
		Map<T, Integer> nums = new TreeMap<>();
		int length = A.length;
		for (T x : A) {
			int value = 1;
			if (nums.containsKey(x)) {
				value = nums.get(x) + 1;
				--length;
			}
			nums.put(x, value);
		}
		T[] B = (T[]) new Comparable[length];
		int i = 0;
		for (T x : A) {
			if (!(nums.get(x) > 1)) {
				B[i++] = x;
				// B.add(x);
			}

		}
		return B;

	}

	// Function to compute all the pairs in the given array that sum up to the
	// given value X.
	static int howMany(int[] A, int X) {
		Map<Integer, Integer> elements = new TreeMap<>();
		if (A.length < 2)
			return 0;

		for (int i = 0; i < A.length; i++) {
			int value = 1;
			if (elements.get(A[i]) != null) {
				value = elements.get(A[i]) + 1;
			}
			elements.put(A[i], value);

		}
		int howMany = 0;
		for (int i = 0; i < A.length; i++) {
			int key = X - A[i];
			if (elements.get(key) != null)
				howMany += elements.get(key);

			if (key == A[i])
				howMany--;

		}
		return howMany / 2;
	}

	// Function that computes the longest streak on consecutive numbers in a
	// given array.
	static int longestStreak(int[] A) {
		Arrays.sort(A);
		int longestStreak = 1, currentStreak = 1;
		for (int i = 1; i < A.length; i++) {
			if (A[i] != A[i - 1] + 1) {
				currentStreak = 1;
			} else {
				currentStreak++;
				longestStreak = Math.max(currentStreak, longestStreak);
			}
		}
		return longestStreak;
	}

}
