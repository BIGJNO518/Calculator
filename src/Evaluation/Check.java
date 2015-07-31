package Evaluation;

public class Check {

	public static int balancedParens(String evalstring){
		CharStack stack = new CharStack();
		for( int i = 0; i < evalstring.length(); i++){
			
			if(evalstring.charAt(i) == '('){
				stack.push('(');
			}else if( evalstring.charAt(i) == ')' && stack.topval() == '('){
				stack.pop();
			}else if(evalstring.charAt(i) == ')' && stack.topval() != '('){
				return i; //if there is ever a point were there is not a matching left 
				//to a right signal error
			}
			else{
				
			}
		}
		if(stack.isEmpty())
			return -1; //-1 indicates no problem
		else
			return 0;
	}
	
	public static void main(String[] args){
		String test = "(())(())((()))((()))()())";
		System.out.println(Check.balancedParens(test));
	}
	
}
