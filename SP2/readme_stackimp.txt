Bounded sized stack is implemented using array where an user will provide 
the size of the stack and items to be pushed/popped into/from the stack.

e.g.
Stack<String> stk = new Stack<>(in.nextInt()); //take the size from the user
stk.push("abc");
stk.push("def");
System.out.println("popped item "+ stk.pop());	//pop returns the item from the top of the stack
System.out.print(stk);	//display the content of the stack

 
