/**  Short Project 4: Program to compute nth Fibonacci using O(n) and O(logn) method
 *   @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 * 
 */
package cs6301.g44;

import java.math.BigInteger;

public class Fibonacci {
    
    // using O(n) dynamic programming to solve Fibonacci -> F[n] = F[n-1]+F[n-2]
    static BigInteger linearFibonacci(int n)
    {
        BigInteger []res = new BigInteger[n+1];
        res[0] = BigInteger.ZERO;
        res[1] = BigInteger.ONE;
        if(n == 0)
            return res[0];
        if(n == 1)
            return res[1];
        for(int i = 2; i <= n; i++)
            res[i] = res[i-1].add(res[i-2]);
            
        return res[n];
    }
    
    //using O(logn) optimized matrix method 
    static BigInteger logFibonacci(int n)
    {
        BigInteger [][]matrix = new BigInteger[2][2];
        matrix[0][0] = BigInteger.ONE;
        matrix[0][1] = BigInteger.ONE;
        matrix[1][0] = BigInteger.ONE;
        matrix[1][1] = BigInteger.ZERO;
        if(n == 0)
            return BigInteger.ZERO;
//        if(n == 1)
//            return BigInteger.ONE;
        power(matrix,n-1);
        
        return matrix[0][0];
    }
    
    //calculate the power of the matrix through recursion
    static void power(BigInteger [][]m, int n)
    {
     
    	if(n==0 || n==1)
    		return;
    	BigInteger [][]matrix = new BigInteger[2][2];
        matrix[0][0] = BigInteger.ONE;
        matrix[0][1] = BigInteger.ONE;
        matrix[1][0] = BigInteger.ONE;
        matrix[1][1] = BigInteger.ZERO;
        power(m,n/2);
        matMul(m,m);
        
        if(n%2 != 0)
        	matMul(m,matrix);
    }
    
    //calculate the matrix Multiplication
    static void matMul(BigInteger [][]a, BigInteger [][]b)
    {
        
        BigInteger x = (a[0][0].multiply(b[0][0])).add(a[0][1].multiply(b[1][0]));
        BigInteger y = (a[0][0].multiply(b[0][1])).add(a[0][1].multiply(b[1][1]));
        BigInteger z = (a[1][0].multiply(b[0][0])).add(a[1][1].multiply(b[1][0]));
        BigInteger w = (a[1][0].multiply(b[0][1])).add(a[1][1].multiply(b[1][1]));
        a[0][0] = x;
        a[0][1] = y;
        a[1][0] = z;
        a[1][1] = w;
    }
    
    public static void main(String []args)
    {
        Timer t = new Timer();
        t.start();
        BigInteger x = linearFibonacci(2000);
        System.out.println("Linear method");
        System.out.println(x);
        System.out.println(t.end());
        Timer t1 = new Timer();
        BigInteger y = logFibonacci(2000);
        System.out.println("Log method");
        System.out.println(y);
        System.out.println(t1.end());

    }
    
}