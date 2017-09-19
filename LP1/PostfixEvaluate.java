/**  LP 1: Driver Program to test Level 3
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */
package cs6301.g44;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class PostfixEvaluate {
	
	
	static boolean isOperand(String s) 
	{
		String regex = "[0-9]+";
		if((s.charAt(0) >= 'a') && (s.charAt(0) <= 'z'))
				return true;
		if( s.matches(regex))
			return true;
		return false;
	}
	
	
	//returns final operand value popped
	static String evaluatePostorder(String input,HashMap<String,String> ope)
	{
		//Hashmap stores values of  variables
		String[] temp=input.split(" ");
		String res=temp[0];
		String regex = "[a-z]";
		String finResult="";
		
		if(temp.length==4)
		{
			ope.put(res, temp[2]);
			System.out.println(temp[2]);
		}
		else
		{
			
			ArrayDeque<String> stack = new ArrayDeque<>();
			
			for(int i = 2; i < temp.length-1; i++){
				
				
				if(isOperand(temp[i]))
				{
					stack.push(temp[i]);
				}
				else
				{
					if(temp[i].charAt(0)=='|')//for square-root just pop one number
					{
						String num1=stack.pop();
						if(num1.matches(regex))
							num1=ope.get(num1);
						stack.push(Num.squareRoot(new Num(num1)).toString());
					}
					else // for all other operations pop two numbers and perform the operation
					{
						String num1=stack.pop();
						if(num1.matches(regex))
							num1=ope.get(num1);
						Num n1=new Num(num1);
						
						String num2=stack.pop();
						if(num2.matches(regex))
							num2=ope.get(num2);
						Num n2=new Num(num2);
						
						switch(temp[i].charAt(0))
						{
							case '+':
							stack.push(Num.add(n2, n1).toString());
		                    break;
		                    
							case '-':
							stack.push(Num.subtract(n2, n1).toString());
			                break;
			                
							case '*':
							stack.push(Num.product(n2, n1).toString());
				            break;
		                 
							case '/':
							stack.push(Num.divide(n2, n1).toString());
				            break;
				            
							case '^':
							stack.push(Num.power(n2, n1).toString());
					        break;
				            
							case '%':
							stack.push(Num.mod(n2, n1).toString());
						    break;
						    
				            default:break;
		                    
						}
					}
					
					String ans=stack.pop();
					System.out.println(ans);
					ope.put(res, ans);    //putting variable and its value into hashmap for future use
					finResult=ans;
				}
			}
		}
		return finResult;
	}
	
	
public static void main(String[] args) throws FileNotFoundException {
	
	Scanner in;
	if (args.length > 0) {
		File inputFile = new File(args[0]);
		in = new Scanner(inputFile);
	} else {
		in = new Scanner(System.in);
	}
	
	String fin="";
	HashMap<String, String> ope=new HashMap<>();
	
	 while (true) {

         String input = in.nextLine();

         if (";".equals(input)) {
             //System.out.println("Exit!");
             break;
         }
          
        fin= evaluatePostorder(input,ope);
     }
	 
	 Num re=new Num(fin);
	 re.printList();
     in.close();
}
}



