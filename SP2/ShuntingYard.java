package cs6301.g44;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ShuntingYard {

<<<<<<< HEAD
	Map<Character, Integer> percedence = new HashMap<Character, Integer>();
	Stack<Character> operators = new Stack<Character>();
	char tokens;
	

	public ShuntingYard() {
		percedence.put('!', 4);
		percedence.put('*', 2);
		percedence.put('/', 2);
		percedence.put('+', 1);
		percedence.put('-', 1);
		percedence.put('^', 3);
	}
	
	private boolean validOperand(char token){
		return ((token >= 'A') && (token <= 'Z')) || ((token >= 'a') && (token <= 'z')) || ((token >= '0') && (token <= '9')) ;
	}

	private boolean checkPrecedence(char token, char topElement) {
		if (percedence.containsKey(topElement)) {
			return percedence.get(topElement) >= percedence.get(token) && percedence.get(token) < 10;
=======
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
>>>>>>> 78fde4a667e14b5c2151929731fd457775925b8c
		} else
			return false;

	}

	public StringBuilder shuntingUtil(String infixNotation) {
		StringBuilder outputQueue = new StringBuilder();

		for (int i = 0; i < infixNotation.length(); i++) {
			tokens = infixNotation.charAt(i);
			if (tokens == ' ')
				continue;
<<<<<<< HEAD
			if (percedence.containsKey(tokens)) {
=======
			if (precedence.containsKey(tokens)) {
>>>>>>> 78fde4a667e14b5c2151929731fd457775925b8c
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
<<<<<<< HEAD
			} else if(validOperand(tokens)) {
				outputQueue.append(tokens);
			}
			else
			{	
=======
			} else if (validOperand(tokens)) {
				outputQueue.append(tokens);
			} else {
>>>>>>> 78fde4a667e14b5c2151929731fd457775925b8c
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

<<<<<<< HEAD
	public static void main(String[] args) {
		ShuntingYard sf = new ShuntingYard();
		System.out.println(sf.shuntingUtil("( ; + b ) * ( c + d )"));
		// 3 4 2 × 1 5 − 2 3 ^ ^ ÷ +
	}

=======
>>>>>>> 78fde4a667e14b5c2151929731fd457775925b8c
}
