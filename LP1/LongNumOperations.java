/**  LP 1: Driver Program to test Level 1 and Level 2
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */
package cs6301.g44;

import cs6301.g44.Num;

public class LongNumOperations {
	public static void main(String[] args) {
	
		System.out.println("\n----------------LEVEL 1------------------\n");
		Num num1=new Num("1231886633362337");		
		Num num2=new Num("13213787333");

		//Testing Addition
		Num res=Num.add(num1,num2);
		System.out.println("Addition : "+num1+" + "+num2+" = "+res);
		
		//Testing Subtraction
		Num res1=Num.subtract(num1,num2);
			System.out.println("Subtraction : "+num1+" - "+num2+" = "+res1);
		
		//Testing Multiplication
		Num res2=Num.product(num1, num2);
		System.out.println("Multiplication : "+num1+" * "+num2+" = "+res2);
		
		//Testing power function
		Num res3=Num.power(new Num("123"), 2);
		System.out.println("123 power 2 is: "+res3);
		
		//Testing printList()
		System.out.println("Testing printList() for "+num1);
		num1.printList();
		
		
		System.out.println("\n----------------LEVEL 2------------------\n");
		//Testing Division
        	System.out.println("Division: "+num1+" / "+num2+" = "+Num.divide(num1, num2));
        	
        //Testing Modulus
        	System.out.println("Modulus: "+num1+" % "+num2+" = "+Num.mod(num1, num2));
        
		//Testing power
        	Num nu3=new Num("999");
        	Num nu4=new Num("8");
        	Num re4=Num.power(nu3, nu4);
        	System.out.println("Power: "+nu3+" ^ "+nu4+" = "+re4 );
        	
        //Testing square-root function
        	Num num5=new Num("8643534386655");
        	if(num5.sign)
        		System.out.println("number should be positive");
        	else
        		System.out.println("Squareroot: "+"root of "+num5+" = "+Num.squareRoot(num5));
        	
        	
	}
	
}

