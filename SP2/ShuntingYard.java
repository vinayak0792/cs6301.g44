package cs6301.g44;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ShuntingYard {

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
		} else
			return false;

	}

	public StringBuilder shuntingUtil(String infixNotation) {
		StringBuilder outputQueue = new StringBuilder();

		for (int i = 0; i < infixNotation.length(); i++) {
			tokens = infixNotation.charAt(i);
			if (tokens == ' ')
				continue;
			if (percedence.containsKey(tokens)) {
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
			} else if(validOperand(tokens)) {
				outputQueue.append(tokens);
			}
			else
			{	
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

	public static void main(String[] args) {
		ShuntingYard sf = new ShuntingYard();
		System.out.println(sf.shuntingUtil("( ; + b ) * ( c + d )"));
		// 3 4 2 × 1 5 − 2 3 ^ ^ ÷ +
	}

}
