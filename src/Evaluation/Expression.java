package Evaluation;

/*this class will model basic mathematical expressions*/
public class Expression {
	private String express;
	private EvalTree tree;
	private Double total;
	
	public Expression(String expr){
		this.express = expr;
	}
	public String getExpress(){
		return this.express;
	}
	public void buildTree(){
		this.tree = EvalTree.buildAddTree(this.express, 0);
		return;
	}
	
	public void eval(){
		buildTree();
		this.total = this.tree.evaluate();
	}
	
	public Double getTotal(){
		return this.total;
	}
	
	public boolean isEmpty(){
		if(this.express.isEmpty())
			return true;
		return false;
	}
	
	public static void main(String[] args){
		Expression expr = new Expression("1+2*3*6+2");
		expr.eval();
		System.out.println(expr.getTotal().toString());
	}

	
}
