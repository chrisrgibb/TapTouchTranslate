package com.example.messageclient;

import translation.CountryCodes;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {

	public static String FROM = CountryCodes.ENGLISH;
	public static String DEST = CountryCodes.CHINESE_MANDARIN;
	
	   @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    addPreferencesFromResource(R.xml.preference);
	    System.out.println();
	  }
	
}
