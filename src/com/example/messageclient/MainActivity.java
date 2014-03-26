package com.example.messageclient;

import java.util.ArrayList;
import java.util.List;

import translation.CountryCodes;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity {
	Button button;
	Button button2;
	Spinner spinner;
	String from = CountryCodes.ENGLISH;
	String dest = CountryCodes.CHINESE_MANDARIN;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("=====================MAIN ACTIVITY ON CREATE=====================");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button_write_message);
		button.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, WriteMessageActivity.class);
				i.putExtra("FROM", from);
				i.putExtra("DEST", dest);
				startActivity(i);			
			}	
		});	
		createSpinner();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void createSpinner(){
		spinner = (Spinner) findViewById(R.id.spinner1);
		List<String> countryCodes = new ArrayList<String>();
		countryCodes.add("Chinese");
		countryCodes.add("French");
		countryCodes.add("Italian");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryCodes);
		spinner.setAdapter(dataAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				// TODO Auto-generated method stub
				switch(pos){
				
					case(0):
						dest = CountryCodes.CHINESE_MANDARIN;
					
						break;
					case(1):
						dest = CountryCodes.FRENCH;
						break;
					case(2):
						dest = CountryCodes.ITALIAN;
						break;
				}	
				System.out.println("DEST = " + dest);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
	}

}
