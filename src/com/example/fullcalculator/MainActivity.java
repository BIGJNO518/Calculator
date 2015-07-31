package com.example.fullcalculator;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {

	public static ArrayList<String> history = new ArrayList<String>();
	public static Spinner spinner;
	public static ArrayAdapter<String> spAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // paints the base page defined in the xml file
		
		//Create The Buttons
		GridView gridview = (GridView) findViewById(R.id.keys); //retrieves the GridView Object
		ButtonAdapter adapter = new ButtonAdapter(this); //creates a button adapter with context of this activity
		gridview.setAdapter(adapter); //sets the Gridview's adapter to the one just created
		history.add("History");
		//Set Button Behavior
		
		
		/*Create History Dropdown Menu */
		System.out.println("hello");
		EditText text = (EditText) findViewById(R.id.input_box);
		spinner = (Spinner) findViewById(R.id.history); //Retrieves a dropdown view
		spinner.setOnItemSelectedListener(new SpinnerActivity(text));
		spAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,history );
		//sets the list of the dropdown menu with this activity as context, a frame of simple_spinner, and the list that populates it 
		spAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line );
		spAdapter.setNotifyOnChange(true);
		spinner.setAdapter(spAdapter);
		//sets the adapter for the spinner so spinner will display the list
		
		adapter.setOnButtonClickListener(new OnClickListener(){ //define a button listener for the buttons in the gridview
			public void onClick(View v){
				EditText text = (EditText) findViewById(R.id.input_box);
				Button b = (Button) v;//get the button that was pressed
				KeypadButton kbutton = (KeypadButton) b.getTag();//get its tag value
				String textvalue = text.getText().toString();
				if(kbutton.getText().toString().equals("="))
					MainActivity.spAdapter.add(text.getText().toString());
				kbutton.pfunc.process(kbutton.getText().toString(),text); //processes the button that was pressed
				
			}
		});
		
	}
	public void refreshSpinner(){
		ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,history);
		spAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spinner.setAdapter(spAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void setConsole(View view){
		EditText text = (EditText) findViewById(R.id.input_box);
		String oldtext = text.getText().toString();
		text.setText(oldtext.concat("World"));
	}

}
