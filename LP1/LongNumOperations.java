package cs6301.g44;
import java.util.Iterator;
import java.util.LinkedList;

import cs6301.g44.Num;
public class LongNumOperations {
	public static void main(String[] args) {
		
		Num num1=new Num("11111123432432323");
		Num num2=new Num("11111111111111111");
		
		Num res=Num.add(num1,num2);
		System.out.println("Addition : "+num1+" + "+num2+" = "+res);
		
		Num res1=Num.subtract(num1,num2);
		if(num1.compareTo(num2)==-1)
		{
			System.out.println("Subtraction : "+num1+" - "+num2+" = "+res1);	
		}
		else if(num1.compareTo(num2)==1)
		System.out.println("Subtraction : "+num1+" - "+num2+" = "+res1);
		else
			System.out.println("Subtraction : "+num1+" - "+num2+" = "+0);
		
		Num res2=Num.product(num1, num2);
		System.out.println("Multiplication : "+num1+" * "+num2+" = "+res2);
		
		Num res3=Num.power(num1, 2);
		System.out.println(num1+" power 2 is: "+res3);
		
		System.out.println("Testing printList() for "+num1);
		num1.printList();
		
		
		
	}

}
