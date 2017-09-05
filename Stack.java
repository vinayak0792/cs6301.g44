/**  Short Project 2: Program to implement array-based bounded-sized stack 
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */

package cs6301.g44;
import java.util.EmptyStackException;

public class Stack<T> {
    final int size;
    T[] bstack;
    int top = -1;
    
    Stack(int size){
        this.size = size;
        bstack = new T[size];
    }
    
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
    
    void pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else{
            top--;
        }
           
    }
    
    public boolean isEmpty(){
        if(top < 0)
            return true;
        else
            return false;
    }
    
    public String toString(){
        for(int i = top;i >= 0; i--)
            System.out.println((i+1)+" "+bstack[i]);
        return "end";
    }
    
}
