/**
 * 
 */
package cs6301.g44.SP7;

import cs6301.g44.SP7.SP7InterviewQues;

/**
 * @author vinayaka
 *
 */
public class SP7InterviewDriver {
	public static void main(String[] args) {
		SP7InterviewQues s = new SP7InterviewQues();
		// finding the longest streak.
		int[] A = { 1, 7, 9, 4, 1, 7, 4, 8, 7, 1 };
		System.out.println("The longets streak is: "+s.longestStreak(A));

		// finding exactlyOnce
		Integer[] B = { 6, 3, 4, 5, 3, 5 };
		Comparable C[] = s.exactlyOnce(B);
		System.out.println("Numbers that appear just once are");
		for (int i = 0; i < C.length; i++)
			if (C[i] != null)
				System.out.print(C[i] + " ");

		// finding howMany
		int[] E = { 3, 3, 4, 5, 3, 5 };
		System.out.println("\nThe number of pairs that sum up to 8 are "+s.howMany(E, 8));
	}

}
