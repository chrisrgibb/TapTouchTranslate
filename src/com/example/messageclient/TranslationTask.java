package com.example.messageclient;

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
		
	}
	
	@Override
	protected TranslationData doInBackground(String... params) {
		// int count = params.length;
		WriteMessageActivity activity =(		WriteMessageActivity) context;
		
		String from = activity.from;
		String dest = activity.dest;
		
		TranslationHttpClient client = new TranslationHttpClient();
		
		return client.translateAFew(from, dest, params[0]);
	}
	
	protected void onPostExecute(TranslationData result){
		//String ss = MainActivity.th3is.dest;
		
		TranslationAlertDialog dlog = new TranslationAlertDialog(context,result);
		Window window = dlog.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
		window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // get rid of greyed out background
        window.setGravity(Gravity.TOP); 
        int w = window.getAttributes().width;
        System.out.println("Window width = " + w);
		dlog.show();
		
	}


}
