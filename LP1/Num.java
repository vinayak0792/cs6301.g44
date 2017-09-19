
/**  LP 1: Program which implements the Num class
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */
package cs6301.g44;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Num  implements Comparable<Num> {

    static long defaultBase = 10;  // This can be changed to what you want it to be.
    long base = 10;  // Change as needed
    boolean sign;  //true if number is negative
    
    LinkedList<Long> numberList=new LinkedList<Long>();
    
    Num(String s)
    {
    	LinkedList<Long> res=new LinkedList<>();
    	LinkedList<Long> ten=new LinkedList<>();
    	if(s=="")
    	{
    		
    	}
    	else
    	{  
    	if(s.charAt(0)=='-')//if its a negative number make sign true and remove first character
    	{
    		sign=true;
    		s=s.substring(1);
    	}
    	if(s.length()==1)
    	{
    		if(s.charAt(0)=='0')
    		{
    			numberList.add((long) 0);
    		}
    		else
    		{
    			if(s.charAt(0)-'0'>=base)    //if first digit is greater than base add remainder and quotient to rsult list
            	{
            		res.add((s.charAt(0)-'0')%base);
            		res.add((s.charAt(0)-'0')/base);
            	}
            	else
            		res.add((long) (s.charAt(0)-'0'));
        		numberList=res;
    		}
    		
    	}
    	
    	else
    		
    	{
    	
    		if(s.charAt(0)-'0'>=base)
        	{
        		res.add((s.charAt(0)-'0')%base);
        		res.add((s.charAt(0)-'0')/base);
        	}
        	else
        		res.add((long) (s.charAt(0)-'0'));
        	
        	if(base>10)
        		ten.add((long) 10);
        	else
        	{
        		ten.add(10%base);
        		ten.add(10/base);
        	}
        	
        	LinkedList<Long> tt=new LinkedList<>();
        	for(int i=1;i<s.length();i++)
        	{
        		tt.removeAll(tt);
        		tt.add((long) (s.charAt(i)-'0'));
        	res=Num.linkedAdd(Num.linkedProd(res, ten, new Num(5).base),tt,new Num(5).base);	
        	}
        	numberList=res;
        	
    	}
    	
    	}
    }
    

    Num(long x) {
    	long quot=x,remainder;
    	if(x==0)
    		numberList.add((long) 0);
    	else
    	while(quot!=0)
    	{
    		remainder=quot%base;
    		numberList.add(remainder);
    		quot=quot/base;
    	}
    	
    	
    }

    //returns next element if present else zero
    static long next(Iterator<Long> it)
    {
    	return it.hasNext()?it.next():0;
    }
    
    
    static long getTen(long base)  //returns number 10 in required base
    {
    	long ten=10;
    	if(base>10)
    		return 10;
    	else
    	{
    		long remainder;
    		LinkedList<Long> tenList=new LinkedList<Long>();
    		while(ten!=0)
        	{
        		remainder=ten%base;
        		tenList.add(remainder);
        		ten=ten/base;
        	}
    		String s="";
    		for(int i=tenList.size()-1;i>=0;i--)
    		{
    			s=s+tenList.get(i);
    		}
    		return Long.parseLong(s);
    	}
    }
    
    //divides the given number by two 
    static Num divideByTwo(Num a)
    {
    	
    	 if(a.toString().length()==1)
    	 {
    	  return new Num((a.toString().charAt(0)-'0')/2);	 
    	 }
    	 
    	 LinkedList<Long> result=new LinkedList<>();
    	 String newList=a.toString();
    	 long carry=0;
    	 
    	 if(a.toString().charAt(0)=='1')
    	 {
    		 Long s=Long.parseLong(a.toString().substring(0, 2));
    		 result.add(s/2);
    		 carry=s%2;
    		 newList=a.toString().substring(2);
    	 }
    	 
    	 Long temp;
    	 for(int i=0;i<newList.length();i++)
    	 {
    	 temp=(long) (newList.charAt(i)-'0')+carry*10;
    	 result.add(temp/2);
    	 carry=temp%2;
    	 }
    	 
    	 StringBuilder nu=new StringBuilder();
    	for(long x:result) 
    		nu.append(Long.toString(x));
    return new Num(nu.toString());
    }
    
    
    static Num add(Num a, Num b) {
    	
    	if(a.sign && ! b.sign)
    	{
    		Num temp=new Num(a.toString());
    		temp.sign=false;
    		return Num.subtract(b, temp);
    	}
    			
    	
    	if(!a.sign && b.sign)
    	{
    		Num temp=new Num(b.toString());
    		temp.sign=false;
    		return Num.subtract(a, temp);
    	}	
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
    	
    		Collections.reverse(additionRes);
    		StringBuilder result=new StringBuilder();
    		for(long x:additionRes)
    		{
    			result=result.append(Long.toString(x).toString());
    		}
    		Num re=new Num(result.toString());
    		
    		if(a.sign&&b.sign)  //if both are negative numbers
    			re.sign=true;
    		return re;
    	
    }
    
    //adds two linked lists
    static LinkedList<Long> linkedAdd(LinkedList<Long> a, LinkedList<Long> b, long base) {
    	long carry=0,sum,x=base;
    	LinkedList<Long> res=new LinkedList<Long>();
    	Iterator<Long> it1=a.iterator();
    	Iterator<Long> it2=b.iterator();
    	while(it1.hasNext() || it2.hasNext() || carry>0)
    	{
    		sum=next(it1)+next(it2)+carry;
    		res.add(sum%x);
    		carry=sum/x;
    	}	
    	return res;
    }
    
    
    static Num subtract(Num a, Num b) {
    	LinkedList<Long> subRes=new LinkedList<Long>();
    	Num num1=a,num2=b;
    	if(num1.compareTo(num2)==0)
    	{
    		return new Num(0);
    	}
    	
    	if(a.sign&& !b.sign) //if both are negative add them and attach negative sign
    	{
    		Num n1=new Num(a.toString());
    		Num n2=new Num(b.toString());
    		n1.sign=false;
    		n2.sign=false;
    		Num temp=Num.add(n1, n2);
    		temp.sign=true;
    		return temp;
    	}
    	
    	if(!num1.sign&&num2.sign)
    	{
    		Num n1=new Num(a.toString());
    		Num n2=new Num(b.toString());
    		n1.sign=false;
    		n2.sign=false;
    		Num temp=Num.add(n1, n2);
    		temp.sign=false;
    		return temp;
    	}
    	
    	if(a.sign&&b.sign)
    	{
    		Num n1=new Num(a.toString());
    		Num n2=new Num(b.toString());
    		n1.sign=false;
    		n2.sign=false;
    		Num temp=Num.subtract(n2, n1);
    		return temp;
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
    
    //multiplies two linkedlists in any base
    static LinkedList<Long> linkedProd(LinkedList<Long> a, LinkedList<Long> b,long yy) {
    	long carry=0;
    	int count=-1;
    	LinkedList<Long> multRes=new LinkedList<>();
		LinkedList<Long> temp=new LinkedList<>();
    	for(int i=0;i<a.size();i++)   //loop through digits of number a
		{
			count++;
			long x=a.get(i);
			temp.removeAll(temp);    //deleting all elements of temp linkedlist
			carry=0;
			for(int j=0;j<b.size();j++)  //loop through digits of number b
			{
				long y=b.get(j); 
				long sum=x*y+carry;  //finding product
				temp.add(sum%yy);
				carry=sum/yy;
			}
			if(carry!=0)
				temp.add(carry);
			for(int z=1;z<=count;z++)
				temp.addFirst((long) 0);  //adding necessary zeroes
			multRes=linkedAdd(multRes, temp, yy);//adding temp result to previous result
		}
    	return multRes;
    	
    }
    
    // Implement Karatsuba algorithm for excellence credit
    static Num product(Num a, Num b) {
    	Num temp=new Num("");
    	
    	if(a.compareTo(b)==-1)
    	{
    		temp=a;
    		a=b;
    		b=temp;
    	}
		Num ans=karatsube(a, b);
		if(a.sign&&!b.sign)
			ans.sign=true;
		if(!a.sign&&b.sign)
			ans.sign=true;
		return ans;
    	
    }
    
    //shifts the given input number by count bits
    static Num shiftBit(Num x,int count)
    {
    	for(int i=0;i<count;i++)
    	{
    		x.numberList.addFirst((long) 0);
    	}
    	return x;
    }
    
    //implemeted karatsube algorithm
    static Num karatsube(Num a, Num b){
    	
    	if(a.numberList.size()<=5 || b.numberList.size()<=5)
    	{
    		//Long re=Long.parseLong(a.toString())*Long.parseLong(b.toString());
    		//Long res=a.numberList.get(0)*b.numberList.get(0);
    		return Num.karatsubeproduct(a, b);
    		//return new Num(re);
    	}
    	
    	Long[] aArray=a.numberList.toArray(new Long[a.numberList.size()]);
    	Long[] bArray=b.numberList.toArray(new Long[b.numberList.size()]);
    	
    	int k=bArray.length/2;
    	
    	StringBuilder al=new StringBuilder();
    	StringBuilder ah=new StringBuilder();
    	StringBuilder bl=new StringBuilder();
    	StringBuilder bh=new StringBuilder();
    	
    	for(int i=k-1;i>=0;i--)
    		al.append(aArray[i]);
    	//System.out.println(al.length());
    	Num nal=new Num(al.toString());;
    	
    	for(int i=aArray.length-1;i>=k;i--)
    		ah.append(aArray[i]);
    	Num nah=new Num(ah.toString());
    
    	for(int i=k-1;i>=0;i--)
    		bl.append(bArray[i]);
    	Num nbl=new Num(bl.toString());
    	
    	for(int i=bArray.length-1;i>=k;i--)
    		bh.append(bArray[i]);
    	Num nbh=new Num(bh.toString());
    	
    	Num ahbh=Num.shiftBit(Num.karatsube(nah,nbh),2*k);
    	
    	Num step1=Num.add(nal, nah);
    	Num step2=Num.add(nbl,nbh);
    	Num step3=Num.karatsube(step1, step2);
    	Num step4=Num.karatsube(nah, nbh);
    	Num step5=Num.subtract(step3, step4);
    	Num step6=Num.karatsube(nal, nbl);
    	Num step7=Num.subtract(step5, step6);
    	Num mid=Num.shiftBit(step7, k);
    	
        Num albl=Num.karatsube(nal, nbl);
        
        Num res=Num.add(ahbh, mid);
        res=Num.add(res, albl);
        
        return res;
    }

    //nsquare multiplication for base case of karatsuba algorithm
    static Num karatsubeproduct(Num a, Num b) {
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
			multRes=Num.linkedAdd(multRes, temp,a.base);//adding temp result to previous result
		}
    	
    	
    		Collections.reverse(multRes);
		StringBuilder result=new StringBuilder();
		for(long x:multRes)
		{
			result=result.append(Long.toString(x).toString());
		}
		Num ans=new Num(result.toString());
		return ans;
    	
    }
    
    // Use divide and conquer to find power
    static Num power(Num a, long y) {
    	Num result=new Num(1);     // Initialize result
    	 
        while (y > 0)
        {
            // multiply x with result if y is odd
            if (y %2 !=0)
            {
            	//System.out.println("re");
            	result = Num.product(result, a);
            }
                
     
            // y is even now
            y = y/2; 
            a=Num.product(a, a);  // Change x to x^2
        }
        return result;
    }
   
    /* End of Level 1 */

    /* Start of Level 2 */
    static Num divide(Num a, Num b) {
    	Num n1=new Num(a.toString());
		Num n2=new Num(b.toString());
		n1.sign=false;
		n2.sign=false;
		
    	Num low=new Num(0);
        Num high=n1;
        Num res=new Num(0);
        Num h=new Num(n1.toString());
        while(low.compareTo(h)==-1 )//using binary search
        {   
        	
        	Num mid=Num.divideByTwo(Num.add(low, high));
        	Num prod=new Num(Num.product(n2, mid).toString());
        	if(prod.compareTo(n1)<=0)
        	{
        		res=mid;
        		low=mid;
        	}
        	else
        		high=mid;
        	
        	h=Num.subtract(high, new Num(1));
        	
        }
        if(a.sign&&!b.sign)
        	res.sign=true;
        if(!a.sign&&b.sign)
        	res.sign=true;
        	return res;
    }
    
    static Num mod(Num a,Num b)
    {
    	Num n1=new Num(a.toString());
		Num n2=new Num(b.toString());
		n1.sign=false;
		n2.sign=false;
    	Num res=Num.subtract(n1,Num.product(Num.divide(n1, n2),n2));
    	return res;
    }

    // Use divide and conquer
    static Num power(Num x, Num n) {
    	Num result=new Num(1);     // Initialize result
    	Num p=n,z=x;
    	
        while (p.compareTo(new Num(0)) == 1)
        {
            // multiply x with result if y is odd
            if ( Num.mod(p, new Num(2)).compareTo(new Num(0)) != 0)
                result = Num.product(result, x);
     
            // y is even now
            p = Num.divide(p,new Num(2)); 
            x = Num.product(x, x);  // Change x to x^2
        }
        
        if ( Num.mod(n, new Num(2)).compareTo(new Num(0)) != 0)//if power is odd and number is negative result must be negative
    		result.sign=z.sign;
       //System.out.println(result.numberList);
        return result;
    }
    
    static Num squareRoot(Num a) {
    	
    	if(a.compareTo(new Num(0))==0 || a.compareTo(new Num(1))==0)
    		return a;
    	Num start=new Num(1);
    	Num end=a,ans=new Num(0);
    	while(start.compareTo(end)!=1)
    	{
    		Num mid=Num.add(start, end);        //finding (start+end)/2
    		mid=Num.divide(mid, new Num(2));
    		Num prod=Num.product(mid, mid);
    		
    		if(prod.compareTo(a)==0)
    			return mid;
    		
    		if(prod.compareTo(a)==-1)
    		{
    			start=Num.add(mid, new Num(1));
    			ans=mid;
    		}
    		else
    			end=Num.subtract(mid, new Num(1));
    	}
    	
    	
	return ans;
    }
    /* End of Level 2 */


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) {
    	
    	if(this.sign&&!other.sign) //this is negative and other is positive
    		return -1;
    	
    	if(!this.sign&&other.sign)
    		return 1;
    	
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
                if(i<other.toString().length())
                {
            	if((this.toString().charAt(i))>(other.toString().charAt(i)))
        		{
        		 n=1;
        		 break;
        		}
        	 if(this.toString().charAt(i)<other.toString().charAt(i))
        	 {
        		 n=-1;
        		 break;
        	 }
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
    	if(sign)
    		System.out.print(" - ");
    	for(long x:numberList)
    	{
    		System.out.print(" "+x);
    	}
    	System.out.println();
    }
    
    // Return number to a string in base 10
    public String toString() {
    	
    	//Collections.reverse(numberList);
    	LinkedList<Long> nn=new LinkedList<>(numberList);
    	Collections.reverse(nn);
    	
    	Iterator<Long> it=nn.iterator();
    	
    	LinkedList<Long> res=new LinkedList<>();
    	res.add((long) 0);
    	LinkedList<Long> temp=new LinkedList<>();
    	
    	LinkedList<Long> ten=new LinkedList<>();
    	ten.add(base);
    	
    	while(it.hasNext())
    	{
    		temp.removeAll(temp);
    		
    		temp.add(it.next());
    		res=Num.linkedAdd(Num.linkedProd(res,ten, 10),temp,10);
			
    	}
    	Collections.reverse(res);
    	 StringBuilder s=new StringBuilder();
    	 if(sign)
    		 s.append('-');
    	 for(long x:res)
    		 s=s.append(x);
    	 return s.toString();
    }

    public long base() { return base; }
}
