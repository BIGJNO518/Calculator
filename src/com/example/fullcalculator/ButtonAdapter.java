package com.example.fullcalculator;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class ButtonAdapter extends BaseAdapter {

	private Context mContext;
	private OnClickListener ClickListener;
	
	public ButtonAdapter(Context c){
		this.mContext = c;
	}
	public void setOnButtonClickListener(OnClickListener listener){
		this.ClickListener = listener;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.kbuttons.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Button b;
		if(convertView == null){
			b = new Button(this.mContext);
			KeypadButton kb = kbuttons[position];
			b.setTag(kb);
			b.setOnClickListener(ClickListener);
		}
		else{
			b = (Button) convertView;
		}
		b.setText(kbuttons[position].getText());
		
		
		return b;
	}
	
	KeypadButton[] kbuttons = {
			//
			KeypadButton.SINE,
			KeypadButton.COS,
			KeypadButton.TAN,
			KeypadButton.LPAR,
			KeypadButton.RPAR,
			KeypadButton.ONE,
			KeypadButton.TWO,
			KeypadButton.THREE,
			KeypadButton.DEL,
			KeypadButton.CLEAR,
			KeypadButton.FOUR,
			KeypadButton.FIVE,
			KeypadButton.SIX,
			KeypadButton.PLUS,
			KeypadButton.MINUS,
			KeypadButton.SEVEN,
			KeypadButton.EIGHT,
			KeypadButton.NINE,
			KeypadButton.TIMES,
			KeypadButton.DIVIDE,
			KeypadButton.ZERO,
			KeypadButton.PER,
			KeypadButton.EQUAL,
			KeypadButton.NEG,
			KeypadButton.SQRT,
			KeypadButton.PWR
	};

}
