/**  Short Project 2: Driver Program to perform operations on bounded-sized stack 
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */

package cs6301.g44;
import java.util.Scanner;

public class stackImp {

    public static void main(String []args) throws Exception{
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the stack size ");
        Stack<String> stk = new Stack(in.nextInt());
        stk.push("abc");
        stk.push("def");
        stk.push("xyz");
        stk.push("xyz2");
        stk.pop();
        System.out.print(stk);
        
    }
}
