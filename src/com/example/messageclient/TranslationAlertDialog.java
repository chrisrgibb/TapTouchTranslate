package com.example.messageclient;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class TranslationAlertDialog extends AlertDialog {
	String translation;

	public TranslationAlertDialog(Context context, String text) {
		super(context);
		this.translation = text;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		System.out.println("super on Create");
		setContentView(R.layout.translation_dialog2);
		
		
//		TextView et = (TextView) findViewById(R.id.translation_dialog_textview1);
		TextView et = (TextView) findViewById(R.id.textView1);

		et.setText(translation);
		
		TextView textview2 = (TextView) findViewById(R.id.textView2);
//		TextView textview2 = (TextView) findViewById(R.id.translation_dialog_textview2);
		textview2.setText("Good god yall");
		//setTitle(translation);
		
		setCancelable(true);
		
		
		
		
	}
	
	

}
