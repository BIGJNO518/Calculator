package Evaluation;

public class CharStack {

	private char head;
	CharStack tail;
	
	public boolean isEmpty(){
		if(this == null || this.head == '\u0000') return true;
		return false;
	}
	
	public void printStack(){
		if(this.isEmpty()){
			System.out.println("");
			return;
		}
		System.out.print( this.head +"->");
		if(this.tail == null){System.out.println(""); return;}
		this.tail.printStack();			
	}
	//pushes element to the top of the stack
	public void push(char c){
		if(this.isEmpty())
		{
			this.head = c;
			this.tail = null;
			return;
		}
		CharStack temp = new CharStack();
		temp.head = this.head;
		temp.tail = this.tail;
		this.head = c;
		this.tail = temp;
		return;
	}
	//pops an element off the top of the stack
	public boolean pop(){
		
		if(this.isEmpty())
			return false;
		
		if(this.tail == null){
			this.head = '\u0000';
			return true;
		}
		this.head = this.tail.head;
		this.tail = this.tail.tail;
		return true;
	}
	
	public char topval(){
		return this.head;
	}
	
	public static void main(String args[]){
		CharStack stack = new CharStack();
		stack.isEmpty();
		stack.pop();
		stack.push('r');
		stack.push('a');
		stack.push('h');
		stack.push('c');
		stack.printStack();
		System.out.println(stack.topval());
		stack.pop();
		stack.pop();
		stack.printStack();
		stack.pop();
		stack.printStack();
		stack.push('a');
		stack.push('h');
		stack.push('c');
		stack.printStack();


	}
}
