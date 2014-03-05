package com.example.messageclient;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;

public class WriteMessageActivity extends Activity {
	EditText writeMessage;
	CharSequence wordToLookup = " f";
	float touchX;
	float touchY;
	
	
	public void selectWord(CharSequence word){
		wordToLookup = word;
	}
	
	public void printWord(){
		if(wordToLookup!=null){
			System.out.println("HI "  + wordToLookup);
		}
	}
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_message);
		
		
		
		writeMessage = (EditText) findViewById(R.id.editTextSMS);
		registerForContextMenu(this.writeMessage);
		
		writeMessage.setOnTouchListener(new View.OnTouchListener() {
			
			
			public String getStringFromText(CharSequence text, int start){
				if(start > 0 && start < text.length()-1){
					int current = start-1;
				}
				return null;
			}
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					touchX = event.getX();
					touchY = event.getY();
				}
				EditText e = (EditText) v;
				
				
				int start = e.getSelectionStart();
				int end = e.getSelectionEnd();
				CharSequence finalstring = e.getText().toString().substring(start, end);
				CharSequence otherstring = e.getText().subSequence(start, end);
				String myString = getStringFromText(e.getText(), start);
				e.performClick();
				e.performClick();
							
				
				return false;
			}
		});

	
		writeMessage.setOnLongClickListener(new OnLongClickListener(){

			@Override
			public boolean onLongClick(View v) {
				System.out.println("long touch x:" + touchX + " y : " + touchY);
				
				WriteMessageActivity.this.printWord();
				EditText e = (EditText) v;
				e.moveCursorToVisibleOffset();
			
				int start = e.getSelectionStart();
				int end = e.getSelectionEnd();
				e.setSelection(start);
				e.requestFocus();
				
				CharSequence finalstring = e.getText().toString().substring(start, end);
				System.out.println("Write Message " + finalstring);
				WriteMessageActivity.this.selectWord(finalstring);
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		
//		writeMessage.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				System.out.println("on click");
//				EditText e = (EditText) v;
//				
////				int start = e.getSelectionStart();
////				int end = e.getSelectionEnd();
////				CharSequence finalstring = e.getText().toString().subSequence(start, end);
////				System.out.println(e.getText());
////				System.out.println("start = " + start + " end = " + end) ;
////				
//			
//				int start = e.getSelectionStart();
//				int end = e.getSelectionEnd();
//				CharSequence finalstring = e.getText().toString().substring(start, end);
//				CharSequence otherstring = e.getText().subSequence(start, end);
//				System.out.println("start = " + start + " end = " + end) ;
//				System.out.println(otherstring);
//				System.out.println("final String = " + finalstring);
//				
//				
//			}
		
			
	
			
			
//		});
//		writeMessage.setOnFocusChangeListener(new OnFocusChangeListener(){
//
////			@SuppressLint("NewApi")
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				System.out.println("Focus changed");
//				// TODO Auto-generated method stub
//				if(hasFocus){
//					 
//					EditText e = (EditText) v;
//					System.out.println(e);
//	              //  int sSelection = ((EditText)v).getText.ToString().getSelectionStart(); 
//	               // int eSelection = ((EditText)v)getText.ToString().getSelectionEnd(); 
//	               // String sString = string.substring(sSelection, eSelection);
//			//		e.getText().g
////					e.setTextIsSelectable(true); // might not work on this api needs min api 11 to work
////				System.out.println(	e.getSelectionStart() + "start" ) ;
//				}
//			}
//			
//		});
	
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, 
					ContextMenu.ContextMenuInfo menuInfo){
		//super.onCreateContextMenu(menu, view, menuInfo);
		System.out.println("Calling context menu");
		if(view.getId()==R.id.editTextSMS){
//			getor
			menu.setHeaderTitle("My TITLE");
//			menu.
//			menu.
			MenuItem item = menu.getItem(0);
			int id = item.getGroupId();
			menu.removeGroup(id);
			System.out.println(this.wordToLookup);
			menu.add(0,view.getId(), 0, R.string.app_name);
		} 
//		else{
//	          super.onCreateContextMenu(menu, view, menuInfo);
//		}
		
	}
	
	
	
	int getOffset(MotionEvent event, EditText ed) {
		Layout layout = ed.getLayout();
		if (layout == null)
			return Integer.MIN_VALUE;
		float x = event.getX() + ed.getScrollX();
		float y = event.getY() + ed.getScrollY();
		int line = layout.getLineForVertical((int) y);
		int offset = layout.getOffsetForHorizontal(line, x);
		return offset;
	}
	
}
