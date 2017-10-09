package cs6301.g44;

import java.lang.Math;
import java.util.PriorityQueue;
import java.util.Scanner;
public class PerfectPower {

	public class Num implements Comparable<Num>{
		int base=0,power=0;
		
		public Num(){
			
		}
		
		public Num(int base,int power){
			this.base=base;
			this.power=power;
		}

		public int compareTo(Num nu) {
			if(this.getVal()>nu.getVal())
				return 1;
			else if(this.getVal()<nu.getVal())
				return -1;
			else
			return 0;
		}
		
		public int getVal(){
			return (int) Math.pow(base, power);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Enter the range n:");
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		PriorityQueue<Num> PQ=new PriorityQueue<>();
		PerfectPower pf=new PerfectPower();
		Num temp=pf.new Num();
		PQ.add(pf.new Num(2,2));
		System.out.println("Perfect powers are: ");
		while(!PQ.isEmpty()){
			temp=PQ.poll();
			System.out.print(" "+temp.getVal());
				//adding element pow(a,b+1)
				Num temp1=pf.new Num(temp.base,temp.power+1);
				if(temp1.getVal()<n)
					PQ.add(temp1);
				
				//adding element pow(a+1,b)	
				Num temp2=pf.new Num(temp.base+1,temp.power);
				if(temp2.getVal()<n)
					PQ.add(temp2);
		}
	}
}
