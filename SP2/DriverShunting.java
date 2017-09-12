package cs6301.g44;

import java.util.Scanner;

public class DriverShunting {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ShuntingYard s = new ShuntingYard();
		String infixExpression = input.nextLine();
		System.out.println(s.shuntingUtil(infixExpression));
		input.close();

	}
}
