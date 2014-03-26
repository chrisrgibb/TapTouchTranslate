package com.example.messageclient;

import org.json.simple.JSONObject;

import translation.TranslationHttpClient;
import android.os.AsyncTask;

public class TranslationTask extends AsyncTask<Object, Object, Object>{
	
	
	@Override
	protected JSONObject doInBackground(Object... params) {
		TranslationHttpClient client = new TranslationHttpClient();
		// TODO Auto-generated method stub

		
		return null;
	}

//	@Override
//	protected Object doInBackground(Object... params) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

}
