package com.example.messageclient;

import translation.TranslationData;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class TranslationAlertDialog extends AlertDialog {
	String translation;
	private TranslationData data;

	public TranslationAlertDialog(Context context, TranslationData result) {
		super(context);
		this.data = result;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		System.out.println("super on Create");
		
		setContentView(R.layout.translation_dialog2);
		
		
		translation = data.getFirstAvailablePhrase();
		String Meaning = data.getFirstAvailableMeaning();

		TextView textview1 = (TextView) findViewById(R.id.textView1);
		textview1.setText(translation);
		
		TextView textview2 = (TextView) findViewById(R.id.textView2);
		textview2.setText(Meaning);
		
		setCancelable(true);
		
		
		
		
	}
	
	

}
