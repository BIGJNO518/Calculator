package com.example.fullcalculator;

import Evaluation.Check;
import Evaluation.Expression;
import android.widget.EditText;


public interface Function{
	void process(String str, EditText box);


	public static final Function append = new Function(){
		public void process(String str, EditText box){
			int stPos = Math.max(box.getSelectionStart(),0);
			int endPos= Math.max(box.getSelectionEnd(), 0);
			
			box.getText().replace(Math.min(stPos,endPos), Math.max(stPos,endPos), str);		
		}
	};
	public static final Function delete = new Function(){
		public void process(String str, EditText box){
			String oldstr = box.getText().toString();
			int stPos = Math.max(box.getSelectionStart(), 0);
			int endPos = Math.max(box.getSelectionEnd(), 0);
			
			
			if(oldstr.length() > 0)
				box.setText(oldstr.substring(0,Math.max(stPos-1,0)).concat(oldstr.substring(Math.max(endPos,stPos),oldstr.length())));
			box.setSelection(Math.max(stPos-1,0));
		}
	};
	public static final Function clear = new Function(){
		public void process(String str, EditText box){
			box.setText("");
		}
	};
	public static final Function eval = new Function(){
		public void process(String str, EditText box){
			if(!str.equals("=")){
				
			}
			else{
				String evaltext = box.getText().toString();
				if( Check.balancedParens(evaltext) == 0){
					box.setText("Unbalanced Parentheses");
					return;
				}
				try{
				Expression expr = new Expression(evaltext);
				expr.eval();
				box.setText(expr.getTotal().toString());
				}catch (Exception e){
					box.setText("Error in Expression");
				}
			}
		}
	};
}
