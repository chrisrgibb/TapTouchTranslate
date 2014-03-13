package com.example.messageclient;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WriteMessageActivity extends Activity {
	MessageEditText writeMessage;
	CharSequence wordToLookup = " f";
	int touchX;
	int touchY;
	TextMessage textmess;
	int countOnTouch = 0;
	
	public void selectWord(CharSequence word){
		wordToLookup = word;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		System.out.println("WriteMessage Activity");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_message);
		textmess = new TextMessage();
		
		
	
		writeMessage = (MessageEditText) findViewById(R.id.editTextSMS);
	
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
				
//				System.out.println("Action = " + event.getAction());
				switch(event.getAction()){
				case (MotionEvent.ACTION_DOWN):
					countOnTouch++;
					break;
				case (MotionEvent.ACTION_UP):
					countOnTouch=0;
//					System.out.println("UP Button pressed");
					break;
				}
				countOnTouch ++;
				EditText e = (EditText) v;
				String definition = e.getText().toString();

				Spannable spans = (Spannable ) e.getText();
				int index = Selection.getSelectionStart(spans);
				
				
				
				
				textmess.printMessage();
//				System.out.println("touch : " +countOnTouch);
				return false;
			}

//			private ClickableSpan getClickableSpan(final String word) {
//				System.out.println(" GET CLICKABLE SPAN" + word);
//				return new ClickableSpan() {
//					final String mWord;
//					{
//						mWord = word;
//					}
//
//					@Override
//					public void onClick(View widget) {
//						Log.d("tapped on:", mWord);
//						Toast.makeText(widget.getContext(), mWord,
//								Toast.LENGTH_SHORT).show();
//					}
//
//					public void updateDrawState(TextPaint ds) {	
//						// comment out to stop drawing links
////						super.updateDrawState(ds);  
//						
//					}
//				};
//			}
		
		});
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		TextMessage tm = writeMessage.getMessageText();
		
		String word = tm.getWordAtIndex(writeMessage.getOffsetForLastDownPosition());
		
		menu.clear();
		menu.add(0, 0, 0, "Lookup Meaning of \'" + word + "\'");
		//MenuInflater menuInflator = getMenuInflater();
		menu.setHeaderTitle("MENU FOOL");
	//	menuInflator.inflate(R.menu.floatage_menu, menu);
	}
	
	@Override
	public boolean onPrepareOptionsMenu (Menu menu) {
		System.out.println("onPrepareOptionsMenu");
	    //if (isFinalized)
	     //   menu.getItem(1).setEnabled(false);
	    return true;
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
