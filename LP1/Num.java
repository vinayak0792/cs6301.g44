package cs6301.g44;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Num  implements Comparable<Num> {

    static long defaultBase = 10;  // This can be changed to what you want it to be.
    long base = 10;  // Change as needed
    boolean sign;  //true if number is negative
    
    LinkedList<Long> numberList=new LinkedList<Long>();
    
    /* Start of Level 1 */
    Num(String s) {
    	if(base==10)
    	{
    		for(int i=s.length()-1;i>=0;i--)
    		{
    			numberList.add((long) Character.getNumericValue(s.charAt(i)));
    		}
    	}
    	else
    	{
    		long remainder,quot;//Quotient and remainder
        	long x=Long.parseLong(s);
        	quot=x;
        	//Converting number to required base and adding each number to LinkedList
        	while(quot!=0)
        	{
        		remainder=quot%base;
        		numberList.add(remainder);
        		quot=quot/base;
        	}
    	}
    	
    }
    
    
    

    Num(long x) {
    	long quot=x,remainder;
    	while(quot!=0)
    	{
    		remainder=quot%base;
    		numberList.add(remainder);
    		quot=quot/base;
    	}
    	
    	
    }

    
    static long next(Iterator<Long> it)
    {
    	return it.hasNext()?it.next():0;
    }
    
    
    static Num add(Num a, Num b) {
    	long carry=0,sum;
    	LinkedList<Long> additionRes=new LinkedList<Long>();
    	Iterator<Long> it1=a.numberList.iterator();
    	Iterator<Long> it2=b.numberList.iterator();
    	while(it1.hasNext() || it2.hasNext() || carry>0)
    	{
    		sum=next(it1)+next(it2)+carry;
    		additionRes.add(sum%a.base);
    		carry=sum/a.base;
    	}
    	
    	if(a.base==10)
    	{
    		Collections.reverse(additionRes);
    		StringBuilder result=new StringBuilder();
    		for(long x:additionRes)
    		{
    			result=result.append(Long.toString(x).toString());
    		}
    		return new Num(result.toString());
    	}
    	else
    	{
    		//converting result to base 10
        	int size=additionRes.size();
        	int n=0;
        	Iterator<Long> it=additionRes.iterator();
        	long res1=0;
        	while(n<size)
        	{
        		res1=res1+ (long) Math.pow(a.base, n++)*it.next();
        	}
        	
        	return new Num(res1);
    	}
    	
    }
    
    static LinkedList<Long> linkedAdd(LinkedList<Long> a, LinkedList<Long> b, Num x) {
    	long carry=0,sum;
    	LinkedList<Long> res=new LinkedList<Long>();
    	Iterator<Long> it1=a.iterator();
    	Iterator<Long> it2=b.iterator();
    	while(it1.hasNext() || it2.hasNext() || carry>0)
    	{
    		sum=next(it1)+next(it2)+carry;
    		res.add(sum%x.base);
    		carry=sum/x.base;
    	}	
    	return res;
    }
    
    
    
    static Num subtract(Num a, Num b) {
    	LinkedList<Long> subRes=new LinkedList<Long>();
    	Num num1=a,num2=b,z;
    	if(num1.compareTo(num2)==0)
    	{
    		return new Num(0);
    	}
    	if(num1.compareTo(num2)==-1)
    	{
    		num1=b;
    		num2=a;
    	}
    		
    		Iterator<Long> it1=num1.numberList.iterator();
    		Iterator<Long> it2=num2.numberList.iterator();
    		long carry=0,sub=0;
    		while(it2.hasNext())
    		{
    			sub=it1.next()-it2.next()-carry;
    			if(sub<0)
    			{
    				sub=sub+a.base;
    				carry=1;
    			}
    			else
    				carry=0;
    			subRes.add(sub);
    		}
    		while(it1.hasNext())
    		{
    			long x=it1.next();
    			if(x==0 && carry==1)
    			{
    				subRes.add((long)a.base-1);
    				continue;
    			}
    			sub=x-carry;
    			if(it1.hasNext() || sub>0)
    			{
    				subRes.add(sub);
    				carry=0;
    			}
    		}
    		
    	//Removing leading zeroes
    	while(subRes.getLast()==0)
    	{
    		subRes.removeLast();
    	}
    		
    		if(a.base==10)
        	{
        		Collections.reverse(subRes);
        		StringBuilder result=new StringBuilder();
        		for(long x:subRes)
        		{
        			result=result.append(Long.toString(x).toString());
        		}
        		Num ans=new Num(result.toString());
        		if(a.compareTo(b)==-1)
        			ans.sign=true;
        		return ans;
        	}
        	else
        	{
        		//converting result to base 10
        		int size=subRes.size();
        		int n=0;
        		Iterator<Long> it=subRes.iterator();
        		long res1=0;
        		while(n<size)
        		{
        			res1=res1+ (long) Math.pow(a.base, n++)*it.next();
        		}
        		Num ans=new Num(res1);
        		if(a.compareTo(b)==-1)
        			ans.sign=true;
        		return ans;
        	}
    }
    
    
    // Implement Karatsuba algorithm for excellence credit
    static Num product(Num a, Num b) {
    	long carry=0;
    	int count=-1;
    	LinkedList<Long> multRes=new LinkedList<Long>();
		LinkedList<Long> temp=new LinkedList<>();
    	for(int i=0;i<a.numberList.size();i++)   //loop through digits of number a
		{
			count++;
			long x=a.numberList.get(i);
			temp.removeAll(temp);    //deleting all elements of temp linkedlist
			carry=0;
			for(int j=0;j<b.numberList.size();j++)  //loop through digits of number b
			{
				long y=b.numberList.get(j); 
				long sum=x*y+carry;  //finding product
				temp.add(sum%a.base);
				carry=sum/a.base;
			}
			if(carry!=0)
				temp.add(carry);
			for(int z=1;z<=count;z++)
				temp.addFirst((long) 0);  //adding necessary zeroes
			multRes=Num.linkedAdd(multRes, temp,a);//adding temp result to previous result
		}
    	
    	if(a.base==10)
    	{
    		Collections.reverse(multRes);
    		StringBuilder result=new StringBuilder();
    		for(long x:multRes)
    		{
    			result=result.append(Long.toString(x).toString());
    		}
    		return new Num(result.toString());
    	}
    	else
    	{
    		//converting result to base 10
        	int size=multRes.size();
        	int n=0;
        	Iterator<Long> it=multRes.iterator();
        	long res1=0;
        	while(n<size)
        	{
        		res1=res1+ (long) Math.pow(a.base, n++)*it.next();
        	}
        	
        	return new Num(res1);
    	}
    	
    }

    // Use divide and conquer
    static Num power(Num a, long y) {
    	Num result=new Num(1);     // Initialize result
    	 
        while (y > 0)
        {
            // multiply x with result if y is odd
            if (y %2 !=0)
                result = Num.product(result, a);
     
            // y is even now
            y = y/2; 
            a=Num.product(a, a);  // Change x to x^2
        }
        return result;
    }
   
    /* End of Level 1 */

    /* Start of Level 2 */
    static Num divide(Num a, Num b) {
    	if(a.compareTo(b)==-1)
    	{
    		return new Num(0);
    	}
    	Num N=a;
		Num D=b;
		int count=0;
		while(N.compareTo(D) >= 0)
		{
			count++;
			N=Num.subtract(N, D);
		}
    	
    	
	return new Num(count);
    }

    static Num mod(Num a, Num b) {

    	if(a.compareTo(b)==-1)
    	{
    		return a;
    	}
    	Num N=a;
		Num D=b;
		int count=0;
		while(N.compareTo(D) >= 0)
		{
			count++;
			N=Num.subtract(N, D);
		}
    	
    	
	return N;
    }

    // Use divide and conquer
    static Num power(Num x, Num n) {
    	Num result=new Num(1);     // Initialize result
    	 
        while (n.compareTo(new Num(0)) == 1)
        {
            // multiply x with result if y is odd
            if ( Num.mod(n, new Num(2)).compareTo(new Num(0)) != 0)
                result = Num.product(result, x);
     
            // y is even now
            n = Num.divide(n,new Num(2)); 
            x = Num.product(x, x);  // Change x to x^2
        }
        return result;
    }

    static Num squareRoot(Num a) {
	return null;
    }
    /* End of Level 2 */


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) {
    	if(this.sign)
    	{
    		if(other.sign==false)
    			return -1;
    	}
    	if(this.numberList.size()>other.numberList.size())
    	{
    		return 1;
    	}
    	else if(this.numberList.size()<other.numberList.size())
    	{
    		return -1;
    	}
    	else
    	{
    		
    		int n=0;
            for(int i=0;i<this.toString().length();i++)
            {

            	if(Character.getNumericValue(this.toString().charAt(i))>Character.getNumericValue(other.toString().charAt(i)))
        		{
        		 n=1;
        		 break;
        		}
        	 if(Character.getNumericValue(this.toString().charAt(i))<Character.getNumericValue(other.toString().charAt(i)))
        	 {
        		 n=-1;
        		 break;
        	 }
        	 
            }
            return n;
    	}
	
    }
    
    // Output using the format "base: elements of list ..."
    // For example, if base=100, and the number stored corresponds to 10965,
    // then the output is "100: 65 9 1"
    void printList() {
    	System.out.print(base+":");
    	for(long x:numberList)
    	{
    		System.out.print(" "+x);
    	}
    	System.out.println();
    }
    
    // Return number to a string in base 10
    public String toString() {
    	int size=numberList.size();
    	int n=0;
    	Iterator<Long> it=numberList.iterator();
    	long res=0;
    	while(n<size)
    	{
    		res=res+ (long) Math.pow(base, n++)*it.next();
    	}
	return Long.toString(res);
    }

    public long base() { return base; }
}
