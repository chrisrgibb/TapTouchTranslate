package com.example.messageclient;

import java.text.BreakIterator;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class WriteMessageActivity extends Activity {
	EditText writeMessage;
	CharSequence wordToLookup = " f";
	int touchX;
	int touchY;
	
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
					  touchX = (int) event.getX();
					  touchY = (int) event.getY();
					
				}
				
				//System.out.println("long touch x:" + touchX + " y : " + touchY);
				// String definition = "Clickable words in text view ".trim();
			
				EditText e = (EditText) v;
				String definition = e.getText().toString();
//				e.setMovementMethod(LinkMovementMethod.getInstance());
//			    LinkMovementMethod.getInstance();
				Spannable spans = (Spannable ) e.getText();
				int index = Selection.getSelectionStart(spans);
			
				BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
				iterator.setText(definition);
				int start = iterator.first();
				System.out.println(BreakIterator.DONE);
				for(int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()){
					String possibleWord = definition.substring(start, end);
					if( Character.isLetterOrDigit(possibleWord.charAt(0))){
						
						ClickableSpan clickspan = getClickableSpan(possibleWord);
						
						System.out.println(start + "  to "  +end  + " index " + index );
						
						if( index > start && index < end){
							clickspan.onClick(e);
							spans.setSpan(clickspan, start, end,  Spannable.SPAN_INTERMEDIATE);
						}
					}
				}
				return false;
			}
			
			//private void isWordClickedOn(){
				
				
				
				
		//	}
			private ClickableSpan getClickableSpan(final String word) {
				System.out.println(" GET CLICKABLE SPAN");
				return new ClickableSpan() {
					final String mWord;
					{
						mWord = word;
					}

					@Override
					public void onClick(View widget) {
						Log.d("tapped on:", mWord);
						Toast.makeText(widget.getContext(), mWord,
								Toast.LENGTH_SHORT).show();
					}

					public void updateDrawState(TextPaint ds) {				
						super.updateDrawState(ds);
					}

				};
			}
		});

		
	
		writeMessage.setOnLongClickListener(new OnLongClickListener(){

			@Override
			public boolean onLongClick(View v) {
				
				EditText e = (EditText) v;
				e.setSelection(3);
				System.out.println("touch x =" + WriteMessageActivity.this.touchX);
				//Toast.makeText(this, "Hello" , Toast.LENGTH_SHORT);
				
			
				// TODO Auto-generated method stub
				return false;
			}
			
			private ClickableSpan getClickableSpan(final String word) {
				return new ClickableSpan() {
					final String mWord;
					{
						mWord = word;
					}

					@Override
					public void onClick(View widget) {
						Log.d("tapped on:", mWord);
						Toast.makeText(widget.getContext(), mWord,
								Toast.LENGTH_SHORT).show();
					}

					public void updateDrawState(TextPaint ds) {
						super.updateDrawState(ds);
					}

				};
			}
			
			
		});
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, 
					ContextMenu.ContextMenuInfo menuInfo){
		//super.onCreateContextMenu(menu, view, menuInfo);
		System.out.println("Calling context menu");
		if(view.getId()==R.id.editTextSMS){

			menu.setHeaderTitle("My TITLE");

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
