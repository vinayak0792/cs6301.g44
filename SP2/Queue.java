package cs6301.g44;

import java.util.Arrays;

public class Queue<T> {

	T[] queueElements;
	int currentIndex;
	int currentSize;

	public Queue() {
		queueElements = (T[]) new Object[16];
		currentIndex = 0;
		currentSize = 16;
	}

	public void offer(T input) {
		if (currentIndex < currentSize) {
			resize();
			queueElements[++currentIndex] = input;

		}
	}

	public T poll() {
		if (isEmpty()) {
			return null;
		} else {
			T element = queueElements[currentIndex];
			currentIndex--;
			resize();
			return element;
		}
	}

	public int size() {
		return currentSize;
	}

	public T peek() {
		return queueElements[currentIndex];
	}

	public boolean isEmpty() {
		return currentIndex == 0;
	}

	public void resize() {
		if (currentIndex <= 0.25 * currentSize) {
			currentSize = currentSize / 2;
		} else if (currentIndex >= 0.9 * currentSize) {
			currentSize = currentSize * 2;
		}
		if (currentSize < 16)
			currentSize = 16;
		queueElements = Arrays.copyOf(queueElements, currentSize);

	}

}
