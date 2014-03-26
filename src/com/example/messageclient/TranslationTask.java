package com.example.messageclient;

import translation.CountryCodes;
import translation.TranslationData;
import translation.TranslationHttpClient;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class TranslationTask extends AsyncTask<String, String, TranslationData>{
	
	TranslationAlertDialog dlog;
	private Context context;
	
	public TranslationTask(Context context){
		this.context = context;
	}
	
	protected void onPreExecute() {
		// dlog = new TranslationAlertDialog(WriteMessageActivity.this, builder.toString());
		
	}
	
	@Override
	protected TranslationData doInBackground(String... params) {
		int count = params.length;

		String from = CountryCodes.ENGLISH;
		String dest = CountryCodes.FRENCH;
		TranslationHttpClient client = new TranslationHttpClient();
//		client.translateAFew(from, dest, params[0]);
		// TODO Auto-generated method stub
		
		return client.translateAFew(from, dest, params[0]);
	}
	
	protected void onPostExecute(TranslationData result){
		String translateString = result.getFirstAvailablePhrase();
		StringBuilder builder = new StringBuilder();
		builder.append(translateString);
		builder.append("\n");
		TranslationAlertDialog dlog = new TranslationAlertDialog(context, builder.toString());
		Window window = dlog.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
		window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // get rid of greyed out background
        window.setGravity(Gravity.TOP); 
        int w = window.getAttributes().width;
        System.out.println("Window width = " + w);
		dlog.show();
		
	}

//	@Override
//	protected Object doInBackground(Object... params) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

}
