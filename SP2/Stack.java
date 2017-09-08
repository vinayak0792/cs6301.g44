/**  Short Project 2: Program to implement array-based bounded-sized stack 
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */

package cs6301.g44;
import java.util.EmptyStackException;

/* Stack class of generic type implemented using an array*/
public class Stack<T> {
    final int size;     
    T[] bstack;
    int top = -1;
    
    /* Stack constructor takes size from the user */
    Stack(int size){    
        this.size = size;
         bstack = (T[])new Object[size];
    }
       
    /* push an item into the stack if array size not reached*/
    void push(T item) throws Exception{
        int temp = top+1;
        if(temp >= size){
            throw new Exception("StackOverflow");
        }
        else{
            bstack[temp] = item;
            top++;
        }
    
    }
    
    /* pop an item from the stack if the stack is not empty */
    T pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else{
            top--;
        }
        return bstack[top+1];
           
    }
    
    /* checks if the stack is empty */
    public boolean isEmpty(){
        return (top < 0);
           
    }
    
    /* overloading the toString to display the stack top to bottom*/
    public String toString(){
        for(int i = top;i >= 0; i--)
            System.out.println(bstack[i]);
        return "--end--";
    }
    
}
