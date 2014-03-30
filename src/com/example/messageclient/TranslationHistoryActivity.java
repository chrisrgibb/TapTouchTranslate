package com.example.messageclient;

import java.util.List;

import translation.TranslationData;
import translation.TranslationDbHelper;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TranslationHistoryActivity extends ListActivity {
	
	
	TranslationDbHelper db;
	SimpleCursorAdapter mAdapter;
	 List<TranslationData> tData;
	 Context context = this;
	
	
	 
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.translation_history);
	
        db = new TranslationDbHelper(this);
      
        tData = db.getAllTranslations();
        ArrayAdapter<TranslationData> adapter = 
        		new ArrayAdapter<TranslationData>(this, android.R.layout.simple_list_item_1, tData);
        setListAdapter(adapter);
        db.close();
        
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		db.close();
	}
	@Override
	protected void onStop(){
		super.onStop();
		db.close();
	}
	
	
	@Override
	protected void onListItemClick (ListView l, View v, int position, long id){
		System.out.println(tData.get(position).getFirstAvailableMeaning());
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(tData.get(position).getFirstAvailableMeaning()).setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
		
	}
	
	
}
