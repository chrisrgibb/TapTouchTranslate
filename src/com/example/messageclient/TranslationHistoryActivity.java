package com.example.messageclient;

import translation.TranslationDbHelper;
import android.app.Activity;
import android.os.Bundle;

public class TranslationHistoryActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translation_history);
	
        TranslationDbHelper db = new TranslationDbHelper(this);
        
        
//        db.addTranslation(d)
        
        
	}
	
	
	
}
