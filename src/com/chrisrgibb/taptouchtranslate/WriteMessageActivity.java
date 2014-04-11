package com.chrisrgibb.taptouchtranslate;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


@SuppressLint("InlinedApi")
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
	private Spinner contactSpinner;
	private Cursor mContactCursor;
	
	
	
	public void selectWord(CharSequence word){
		wordToLookup = word;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("ONCREATE WRITE ACTIVITY");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_message);
		
		setSpinner();
		
		
		from = this.getIntent().getStringExtra("FROM");
		dest = this.getIntent().getStringExtra("DEST");
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
		//  Click to send message
		sendButton.setOnClickListener(new OnClickListener(){		
			@Override
			public void onClick(View v){
//				String phoneNo = phoneNumber.getText().toString();
				String phoneNo = getPhoneNumber();
				if(phoneNo.equals("")){
					// THROW ERRORRR
					return;
				}
				String message = writeMessage.getText().toString();
				smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNo, null, message, null, null);
				Toast.makeText(getApplicationContext(), "SMS Sent!",
						Toast.LENGTH_LONG).show();	
			}		
		});
	}
	
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
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
	
	
	/*
	 * Private Methods
	 */
	
	/**
	 * Creates a new Async Task to get translation from http request
	 * @param phrase
	 */
	private void getTranslate(String phrase){
		new TranslationTask(this).execute(phrase);
	}
	
	/**
	 * Initializes the contacts list.
	 */
	private void setSpinner(){
		ContentResolver cr =getContentResolver();
		mContactCursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		// could make a new contactclass ?
		List<String> contacts = new ArrayList<String>();
		if(mContactCursor.getCount() > 0){
			while(mContactCursor.moveToNext()) {
				String id   = mContactCursor.getString(mContactCursor.getColumnIndex(ContactsContract.Contacts._ID));
				String name = mContactCursor.getString(mContactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME )); 
				if(Integer.parseInt(mContactCursor.getString(mContactCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0){
					Cursor pcur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
							new String[]{id}, null);
					while(pcur.moveToNext()) {
						//String phoneNo = pcur.getString(pcur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)); 
						contacts.add(name);
					}
					pcur.close();
				}
			}
		}
		contactSpinner = (Spinner) findViewById(R.id.contactSpinner);
		ArrayAdapter<String> contactAdapter = new ArrayAdapter<String>(WriteMessageActivity.this, android.R.layout.simple_spinner_dropdown_item, contacts);
		contactSpinner.setAdapter(contactAdapter);
	
	}
	
	private String getPhoneNumber(){
		String name = contactSpinner.getSelectedItem().toString();
		ContentResolver cr = getContentResolver();
		mContactCursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		if(mContactCursor.getCount() > 0){
			while(mContactCursor.moveToNext()) {
				String contactName = mContactCursor.getString(mContactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME )); 
				if(name.equals(contactName)){
					String id   = mContactCursor.getString(mContactCursor.getColumnIndex(ContactsContract.Contacts._ID));
					if(Integer.parseInt(mContactCursor.getString(mContactCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0){
						Cursor pcur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
								ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
								new String[]{id}, null);
						while(pcur.moveToNext()) {
							String phoneNo = pcur.getString(pcur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
							mContactCursor.close();
							return phoneNo;
						}
					}
					
					
				}
			}
		}
		mContactCursor.close();
		return "";
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
