package com.chrisrgibb.taptouchtranslate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//import com.example.messageclient.R;
import com.chrisrgibb.taptouchtranslate.R;

public class MainActivity extends Activity {
	Button button;
	Button button2;
	Button settingsButton;
	String from = "eng";
	String dest = "cmn";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
		button2 = (Button) findViewById(R.id.settings_button);
		button2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TranslationHistoryActivity.class);
				startActivity(i);	
			}		
		});	 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		System.out.println("I'M an Options menu");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch( item.getItemId()){
			case R.id.action_settings:
				Intent i = new Intent(this, SettingsActivity.class);
				startActivity(i);
				break;
		}
		return false;
	}
	
}
