package cs6301.g44;

public class HuffTree implements Comparable<HuffTree>{
double freq;
char ch; //char in the node
HuffTree left,right;
String code = "";

public HuffTree() {
	
}

//merge two nodes and create a new node with added frquencies
public static HuffTree merge(HuffTree t1,HuffTree t2)
{
	HuffTree root=new HuffTree();
	root.left=t1;
	root.right=t2;
	root.freq=t1.freq+t2.freq;
	return root;
}

public HuffTree(double freq,char ele)
{
this.freq=freq;
this.ch=ele;
}

public int compareTo(HuffTree t)
{
	if(this.freq>t.freq)
		return 1;
	else if(this.freq<t.freq)
		return -1;
	else
		return 0;
}

//assign codes and print the code for each character
public static void assignCode(HuffTree root) {
    if (root.left != null) {
      root.left.code = root.code + "0";
      assignCode(root.left);
      root.right.code = root.code + "1";
      assignCode(root.right);
    }
    else {
        System.out.println("Code for "+root.ch+" is: "+root.code);
      }
   
  }

}
