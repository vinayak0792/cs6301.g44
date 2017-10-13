package cs6301.g44.SP7;

import java.util.Arrays;
import java.util.HashMap;

public class SP7InterviewQues {

	static int howMany(int[] A, int X) {
		HashMap<Integer, Integer> elements = new HashMap<>();
		if (A.length < 2)
			return 0;

		for (int i = 0; i < A.length; i++) {
			int value = 0;
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
