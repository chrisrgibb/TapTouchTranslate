package com.example.messageclient;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WriteMessageActivity extends Activity {
	
	EditText phoneNumber;
	MessageEditText writeMessage;
	Button sendButton;
	CharSequence wordToLookup = " f";
	int touchX;
	int touchY;
	TextMessage textmess;
	int countOnTouch = 0;
	SmsManager smsManager;
	boolean testing = true;
	String from;
	String dest;
	
	
	
	public void selectWord(CharSequence word){
		wordToLookup = word;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("ONCREATE WRITE ACTIVITY");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_message);
		
		from = this.getIntent().getStringExtra("FROM");
		dest = this.getIntent().getStringExtra("DEST");
		System.out.println(from);
		System.out.println(dest);
		
		textmess = new TextMessage();
		
		phoneNumber = (EditText) findViewById(R.id.editTextPhoneNo);
		writeMessage = (MessageEditText) findViewById(R.id.editTextSMS);
		sendButton = (Button) findViewById(R.id.buttonSend);
		
		registerForContextMenu(this.writeMessage);
		writeMessage.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case (MotionEvent.ACTION_DOWN):
					countOnTouch++; // for debuggin
					break;
				case (MotionEvent.ACTION_UP):
					countOnTouch = 0;
					break;
				}

				return false;
			}

		});
		
		sendButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				String phoneNo = phoneNumber.getText().toString();
				String message = writeMessage.getText().toString();
				
			
				smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNo, null, message, null, null);
				Toast.makeText(getApplicationContext(), "SMS Sent!",
						Toast.LENGTH_LONG).show();
				
			}
					
		});
		
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		String word = writeMessage.getSelectedWord();
		if(word.length() == 0){
			// word not selected, just selected empty space
			
		}

		menu.clear(); // remove every item on contextMenu
		menu.add(0, 0, 0, "Lookup \'" + word + "\'");
		menu.setHeaderTitle("MENU FOOL");
		
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		if(item.getItemId()==0){
			
			getTranslate(writeMessage.getSelectedWord());
			
		}
		writeMessage.setSelection(0);		
		
		return false;
	}
	
	private void getTranslate(String phrase){
		
		new TranslationTask(this).execute(phrase);
		
		
	}
	
//	private void getTranslate(String phrase){
//		String translateString= "test";
//		if(testing){
//			translateString = "In test Mode";
//			
//		}else{
//			
//		
//			String from = CountryCodes.ENGLISH;
//			//String dest = CountryCodes.CHINESE_MANDARIN;
//			String dest = CountryCodes.FRENCH;
//		
//			TranslationHttpClient client = new TranslationHttpClient();
//			
//			TranslationData translation = client.translateAFew(from, dest, phrase);
//			//String translateString = translation.getTranslations().get(0).getPhrase().toString();
//			translateString = translation.getFirstAvailablePhrase();
//		}
//		
//		StringBuilder builder = new StringBuilder();
//		builder.append(translateString);
//		builder.append("\n");
//			
//		
////		AlertDialog dialog = new AlertDialog.Builder(this)
////						.setMessage(builder.toString())
////						.setCancelable(true).create();
////		dialog.show();
//		TranslationAlertDialog dlog = new TranslationAlertDialog(this, builder.toString());
//		Window window = dlog.getWindow();
//		window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
//		window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // get rid of greyed out background
//        window.setGravity(Gravity.TOP); 
//        int w = window.getAttributes().width;
//        System.out.println("Window width = " + w);
//		dlog.show();
//
//	}
	
	
	
	
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
