package Evaluation;
/* 1-2 tree for evaluating mathematical expression yielding to OP precedence*/
import java.lang.Math;
public class EvalTree {
	private EvalTree lChild;
	private EvalTree rChild;
	private double num;
	private String op;
	
	public boolean isEmpty(){
		if(this == null) return true;
		
		return false;
	}
	
	public double getData(){
		return this.num;
	}
	public void setData(Double c){
		this.num = c;
	}
	public String getOp(){
		return this.op;
	}
	public void setOp(String o){
		this.op = o;
	}
	
	public EvalTree getLeft(){
		return this.lChild;
	}
	public void setLeft(EvalTree lc){
		this.lChild = lc;
	}
	public EvalTree getRight(){
		return this.rChild;
	}
	public void setRight(EvalTree rc){
		this.rChild = rc;
	}
	
	/* To build Parse tree we also parse the string separating the string into 
	 * divisible sections and then build subtrees from those substrings
	 */
	
	public static EvalTree buildAddTree(String expr,int index){
		EvalTree tree = null;
		
		int firstplus = expr.indexOf('+',index);
		int firstminus = expr.indexOf('-',index);
		int[] pindecies = EvalTree.outerParenPair(expr);
		int firstlparen = pindecies[0];
		int firstrparen = pindecies[1];
		if(expr.isEmpty()){//if nothing in string nothing to add to tree
			return null;
		}
		//if no additive symbols skip over to multiplicative symbols
		if(firstplus==-1 && firstminus==-1){
			return EvalTree.buildMultTree(expr, 0);
		}
			
		
		/*if the additive op was inside parenthesis skip and find next*/
		if(firstplus != -1 && firstplus < firstminus || firstminus == -1 ){
			if(firstplus > firstlparen && firstplus < firstrparen){
				if((firstminus > firstlparen && firstminus < firstrparen) || firstminus == -1)
					return EvalTree.buildMultTree(expr, firstplus + 1);
				else {
					tree = new EvalTree();
					tree.setOp("-");
					tree.lChild = EvalTree.buildMultTree(expr.substring(0,firstminus), 0);
					tree.rChild = EvalTree.buildAddTree(expr.substring(firstminus+1),0);
				}
			}else{
				tree = new EvalTree();
				tree.setOp("+");
				tree.lChild = EvalTree.buildMultTree(expr.substring(0,firstplus), 0);
				tree.rChild = EvalTree.buildAddTree(expr.substring(firstplus+1),0);
			}			
		}
		else if(firstminus !=-1 && firstminus < firstplus || firstplus == -1){
			if(firstminus > firstlparen && firstminus < firstrparen ){
				if((firstplus> firstlparen && firstplus < firstrparen) || firstplus == -1)
					return EvalTree.buildMultTree(expr, firstminus + 1 );
				else{
					tree = new EvalTree();
					tree.setOp("+");
					tree.lChild = EvalTree.buildMultTree(expr.substring(0,firstplus), 0);
					tree.rChild = EvalTree.buildAddTree(expr.substring(firstplus+1),0);
				}
			}else{
				tree = new EvalTree();
				tree.setOp("-");
				tree.lChild = EvalTree.buildMultTree(expr.substring(0,firstminus), 0);
				tree.rChild = EvalTree.buildAddTree(expr.substring(firstminus+1),0);
			}
		}
		
		return tree;
	}
	
	public static EvalTree buildMultTree(String expr,int index){
		EvalTree tree = null;
		if(expr.isEmpty()){//if empty then nothing to do
			return null;
		}
		int firstmul = expr.indexOf('*',index);
		int firstdiv = expr.indexOf('/',index);
		int[] pindecies = EvalTree.outerParenPair(expr);
		int firstlparen = pindecies[0];
		int firstrparen = pindecies[1];
		
		//if no multiplicative symbols found look for exponential ones
		if(firstmul==-1 && firstdiv ==-1){
			return EvalTree.buildExpTree(expr, 0);
		}
		
		
		if(firstmul != -1 && firstmul < firstdiv || firstdiv == -1 ){
			if(firstmul > firstlparen && firstmul < firstrparen ){
				if((firstdiv > firstlparen && firstdiv < firstrparen) || firstdiv == -1)
					return EvalTree.buildMultTree(expr, firstmul + 1);
				else {
					tree = new EvalTree();
					tree.setOp("/");
					tree.lChild = EvalTree.buildExpTree(expr.substring(0,firstdiv), 0);
					tree.rChild = EvalTree.buildMultTree(expr.substring(firstdiv+1),0);
				}
			}else{
				tree = new EvalTree();
				tree.setOp("*");
				tree.lChild = EvalTree.buildExpTree(expr.substring(0,firstmul), 0);
				tree.rChild = EvalTree.buildMultTree(expr.substring(firstmul+1),0);
			}			
		}
		else if(firstdiv !=-1 && firstdiv < firstmul || firstmul == -1){
			if(firstdiv > firstlparen && firstdiv < firstrparen){
				if((firstmul> firstlparen && firstmul < firstrparen) || firstmul == -1)
					return EvalTree.buildMultTree(expr, firstdiv + 1 );
				else{
					tree = new EvalTree();
					tree.setOp( "*");
					tree.lChild = EvalTree.buildExpTree(expr.substring(0,firstmul), 0);
					tree.rChild = EvalTree.buildMultTree(expr.substring(firstmul+1),0);
				}
			}else{
				tree = new EvalTree();
				tree.setOp("/");
				tree.lChild = EvalTree.buildExpTree(expr.substring(0,firstdiv), 0);
				tree.rChild = EvalTree.buildMultTree(expr.substring(firstdiv+1),0);
			}
		}
		return tree;
	}
	
	public static EvalTree buildExpTree(String expr, int index){
		EvalTree tree = null;
		if(expr.isEmpty()){
			return null;
		}
		
		int firstexp = expr.indexOf('^',index);
		int[] pindecies = EvalTree.outerParenPair(expr);
		int firstlparen = pindecies[0];
		int firstrparen = pindecies[1];
		if(firstexp == -1){
			return EvalTree.buildParenTree(expr,0);
		}
		
		//find the next occurrence if nested in parens
		if(firstexp > firstlparen && firstexp < firstrparen){
			return EvalTree.buildExpTree(expr, firstexp + 1);
		}
		
		tree = new EvalTree();
		tree.op = "^";
		tree.lChild = EvalTree.buildParenTree(expr.substring(0,firstexp), 0);
		tree.rChild = EvalTree.buildExpTree(expr.substring(firstexp+1), 0);
		
		
		return tree;
	}
	
	public static EvalTree buildParenTree(String expr,int index){
		EvalTree tree = null;
		int firstlparen = expr.indexOf('(');
		if(firstlparen == -1){//if there are no parenthesis then must be number
			tree = EvalTree.numNode(expr); //get the number and return;
			return tree;
		}
		
		int[] pindecies = EvalTree.outerParenPair(expr);
		if(pindecies[0] > 0){//Here if there is a function name we retrieve it from the adjoining paren
			if(Character.isLetter(expr.charAt(pindecies[0]-1))){//does a letter precede paren?
				int i = pindecies[0]-1;
				while( i>0 && Character.isLetter(expr.charAt(i)) )//then backstep till beginning or something else
					i--;
				 //store that string
				//node with its operation being the funciton (unary)
				tree = new EvalTree();
				tree.setOp(expr.substring(i,pindecies[0]));
				tree.lChild = null;
				tree.rChild = EvalTree.buildAddTree(expr.substring(pindecies[0] +1, pindecies[1]), 0);
				return tree;
			}
		}
		//then we repeat the entire process on the interior of this parenthesis
		return EvalTree.buildAddTree(expr.substring(pindecies[0] +1, pindecies[1]), 0);
		
	
	}
	
	public static EvalTree numNode(String numberText){
		EvalTree node = new EvalTree();
		node.lChild = null;
		node.rChild = null;
		node.op = null;
		
		try{
			node.num = Double.valueOf(numberText.trim());
		}catch (Exception e){
			System.out.println("Value is not a number");
			
			return null;
		}
		
		return node;	
	}
	public static final int NUMPARENS = 2;
	public static int[] outerParenPair(String expr){
		int[] rets = new int[NUMPARENS];
		int firstlparen = expr.indexOf('(');
		rets[0] = -1;//init values
		rets[1] = -1;
		if(firstlparen == -1){
			return rets;
		}
		rets[0] = firstlparen;
		int index = firstlparen;
		CharStack stack = new CharStack();
		stack.push('(');
		while( !stack.isEmpty()){
			index++;
			if(expr.charAt(index)== ')'){
				stack.pop();
			}else if(expr.charAt(index) == '('){
				stack.push('(');
			}
		}
		rets[1] = index; //loop index contains index of matching paren
		return rets;
	}
	
	public Double evaluate(){
		if(this.isEmpty())
			return 0.0;
		
		//if no children then it is a leaf node and thus a constant
		if(this.lChild == null && this.rChild == null){
			return this.num;
		}
		else if(this.op.equals("+")){//return sum of left and right children
			return this.lChild.evaluate() + this.rChild.evaluate();
		}
		else if(this.op.equals("-")){//return the difference
			return this.lChild.evaluate() - this.rChild.evaluate();
		}
		else if(this.op.equals("*")){//return the product
			return this.lChild.evaluate() * this.rChild.evaluate();
		}
		else if(this.op.equals("/")){//return the quotient (Double precision)
			try{
				return this.lChild.evaluate() / this.rChild.evaluate();
			}catch (Exception e){
				System.out.println("NaN");
				
				return 0.0;
			}
		}
		else if(this.op.equals("^")){//raise the base(left) to power(right)
			return Math.pow(this.lChild.evaluate(),this.rChild.evaluate() );
		}////////////////////////////all functions have empty left children
		else if(this.op.equals("sqrt")){//return sqrt of right children
			return Math.sqrt(this.rChild.evaluate());
		}
		else if(this.op.equals("neg")){//return the opposite of the right child
			return 0 - this.rChild.evaluate();
		}
		else if(this.op.equals("exp")){
			return Math.exp(this.rChild.evaluate());
		}
		else if(this.op.equals("log")){
			try{
				return Math.log10(this.rChild.evaluate());
			}catch(Exception e){
				System.out.println("NaN");
				
				return 0.0;
			}
		}
		else if(this.op.equals("ln")){
			try{
				return Math.log(this.rChild.evaluate());
			}catch(Exception e){
				System.out.println("NaN");
				
				return 0.0;
			}
		}
		else if(this.op.equals("sin")){
			return Math.sin(this.rChild.evaluate());
		}
		else if(this.op.equals("cos")){
			return Math.cos(this.rChild.evaluate());
		}
		else if(this.op.equals("tan")){
			try{
				return Math.tan(this.rChild.evaluate());
			} catch (Exception e){
				System.out.println("NaN");
				
				return 0.0;
			}
		}
		else{
			System.out.println("Error Operator not Recognized");
			System.exit(1);
			return 0.0;
			
		}
	}
	
	
	public static void main(String[] args){

		EvalTree tree = EvalTree.buildAddTree("log(0)",0);
		System.out.println(tree.evaluate());
	
	}
	
	
}


