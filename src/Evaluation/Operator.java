package Evaluation;

public enum Operator {
	Plus("+",2)
,	Minus("-",2)
,	Mult("*",2)
,	Div("/",2)
,	Pwr("^",2)
,	Neg("~",1)
,	SQRT("sqrt",1)
,	nthRoot("nroot",2)
,	exp("exp",1)
,	log("log",1)
,	ln("ln",1)
,	sin("sin",1)
,	cos("cos",1)
,	tan("tan",1);

	private String op;
	private int numops;
	
	Operator(String symbol,int numOperands){
		op = symbol;
		numops = numOperands;
	}
	
	public String getOp(){
		return this.op;
	}
	public int getNumOps(){
		return this.numops;
	}
}
