package com.example.fullcalculator;


public enum KeypadButton {
	ZERO("0",ButtonCategory.Number,Function.append),
	ONE("1",ButtonCategory.Number,Function.append),
	TWO("2",ButtonCategory.Number,Function.append),
	THREE("3",ButtonCategory.Number,Function.append),
	FOUR("4",ButtonCategory.Number,Function.append),
	FIVE("5",ButtonCategory.Number,Function.append),
	SIX("6",ButtonCategory.Number,Function.append),
	SEVEN("7",ButtonCategory.Number,Function.append),
	EIGHT("8",ButtonCategory.Number,Function.append),
	NINE("9",ButtonCategory.Number,Function.append),
	EQUAL("=",ButtonCategory.Evaluator,Function.eval),
	DEL("<=",ButtonCategory.Delete,Function.delete),
	PLUS("+",ButtonCategory.Operator,Function.append),
	MINUS("-",ButtonCategory.Operator,Function.append),
	TIMES("*",ButtonCategory.Operator,Function.append),
	DIVIDE("/",ButtonCategory.Operator,Function.append),
	CLEAR("clr",ButtonCategory.Clear,Function.clear),
	LPAR("(",ButtonCategory.Punctuation,Function.append),
	RPAR(")",ButtonCategory.Punctuation,Function.append),
	SINE("sin(",ButtonCategory.Procedure,Function.append),
	COS("cos(",ButtonCategory.Procedure,Function.append),
	TAN("tan(",ButtonCategory.Procedure,Function.append),
	SQRT("sqrt(",ButtonCategory.Procedure,Function.append),
	NEG("neg(",ButtonCategory.Procedure,Function.append),
	PER(".",ButtonCategory.Punctuation,Function.append),
	PWR("^",ButtonCategory.Operator,Function.append);
	
	CharSequence text;
	ButtonCategory category;
	Function pfunc;
	
	KeypadButton(CharSequence c, ButtonCategory bc,Function func){
		this.text = c;
		this.category = bc;
		this.pfunc = func;
	}
	
	public CharSequence getText(){
		return this.text;
	}
	

}
