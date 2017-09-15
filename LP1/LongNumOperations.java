package cs6301.g44;
import java.util.Iterator;
import java.util.LinkedList;

import cs6301.g44.Num;
public class LongNumOperations {
	public static void main(String[] args) {
		
		Num num1=new Num("1000434");
		Num num2=new Num("414343");
		
		Num res=Num.add(num1,num2);
		//Testing Addition
		System.out.println("Addition : "+num1+" + "+num2+" = "+res);
		
		//Testing Subtraction
		Num res1=Num.subtract(num1,num2);
			System.out.println("Subtraction : "+num1+" - "+num2+" = "+res1);
		
		//Testing Multiplication
		Num res2=Num.product(num1, num2);
		System.out.println("Multiplication : "+num1+" * "+num2+" = "+res2);
		
		//Testing power function
		Num res3=Num.power(num1, 2);
		System.out.println(num1+" power 2 is: "+res3);
		
		//Testing printList()
		System.out.println("Testing printList() for "+num1);
		num1.printList();
		
		//Testing Division
        	System.out.println("Division: "+num1+" / "+num2+" = "+Num.divide(num1, num2));
        
        //Testing Modulus
        	System.out.println("Modulus: "+num1+" % "+num2+" = "+Num.mod(num1, num2));
		
		//Testing power
        	Num num3=new Num("-8");
        	Num res4=Num.power(num3, num2);
        	System.out.println("Power: "+num3+" ^ "+num2+" = "+res4 );
        	
        //Testing squareroot function
        	System.out.println("Squareroot: "+"root of "+num1+" = "+Num.squareRoot(num1));
        	
        	
        	
	}
	
}
