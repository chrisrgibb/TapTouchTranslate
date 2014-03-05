package com.example.messageclient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener;
import android.view.textservice.SuggestionsInfo;

@SuppressLint("NewApi")
public class SpellCheckerActivity extends Activity implements SpellCheckerSessionListener {

	@Override
	public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] results) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetSuggestions(SuggestionsInfo[] results) {
		// TODO Auto-generated method stub
		
	}

}
