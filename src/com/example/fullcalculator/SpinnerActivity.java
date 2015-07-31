package com.example.fullcalculator;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Toast;

public class SpinnerActivity extends Activity implements OnItemSelectedListener{

	public EditText viewToEdit;
	SpinnerActivity(EditText text){
		viewToEdit = text;
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos,
			long id) {
		
		Toast.makeText(parent.getContext(), 
				"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
				Toast.LENGTH_LONG).show();
		if(!parent.getItemAtPosition(pos).toString().equals("History"))
			this.viewToEdit.setText(parent.getItemAtPosition(pos).toString());
			}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
