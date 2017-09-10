package cs6301.g44;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ShuntingYard {

	// Map to maintain the operators precedence values.
	Map<Character, Integer> precedence = new HashMap<Character, Integer>();
	Stack<Character> operators = new Stack<Character>();
	char tokens;

	// Adding precedence values for operators.
	public ShuntingYard() {
		precedence.put('!', 4);
		precedence.put('*', 2);
		precedence.put('/', 2);
		precedence.put('+', 1);
		precedence.put('-', 1);
		precedence.put('^', 3);
	}

	// Helper function to check if the operand is valid or not.
	private boolean validOperand(char token) {
		return ((token >= 'A') && (token <= 'Z')) || ((token >= 'a') && (token <= 'z'))
				|| ((token >= '0') && (token <= '9'));
	}

	// Helper function to check the precedence of the operator being added
	private boolean checkPrecedence(char token, char topElement) {
		if (precedence.containsKey(topElement)) {
			return precedence.get(topElement) >= precedence.get(token);
		} else
			return false;

	}

	public StringBuilder shuntingUtil(String infixNotation) {
		StringBuilder outputQueue = new StringBuilder();

		for (int i = 0; i < infixNotation.length(); i++) {
			tokens = infixNotation.charAt(i);
			if (tokens == ' ')
				continue;
			if (precedence.containsKey(tokens)) {
				while (!operators.isEmpty() && checkPrecedence(tokens, operators.peek())) {
					// outputQueue.offer(operators.pop());
					outputQueue.append(operators.pop());
				}
				operators.push(tokens);
			} else if (tokens == '(') {
				operators.push(tokens);
			} else if (tokens == ')') {
				while (operators.peek() != '(') {
					// outputQueue.offer(operators.pop());
					outputQueue.append(operators.pop());

				}
				operators.pop();
			} else if (validOperand(tokens)) {
				outputQueue.append(tokens);
			} else {
				System.out.println("Invalid operands");
				System.exit(0);
			}
		}

		while (!operators.isEmpty()) {
			// outputQueue.offer(operators.pop());
			outputQueue.append(operators.pop());

		}

		return outputQueue;
	}

}
