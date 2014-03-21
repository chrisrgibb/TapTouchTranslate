package com.example.messageclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import translation.TranslationData;
import translation.TranslationHttpClient;
import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;


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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_message);
		
		
		textmess = new TextMessage();
		
		writeMessage = (MessageEditText) findViewById(R.id.editTextSMS);
	
		registerForContextMenu(this.writeMessage);
		writeMessage.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch(event.getAction()){
				case (MotionEvent.ACTION_DOWN):
					countOnTouch++; // for debuggin
					break;
				case (MotionEvent.ACTION_UP):
					countOnTouch=0;
					break;
				}
				
			
//				EditText e = (EditText) v;
//				String definition = e.getText().toString();
//
//				Spannable spans = (Spannable ) e.getText();
//				int index = Selection.getSelectionStart(spans);
				
			
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
		System.out.println("onCreateContextMenu");
		TextMessage tm = writeMessage.getMessageText();
		
		String word = writeMessage.getSelectedWord();

		menu.clear(); // remove every item on contextMenu
		menu.add(0, 0, 0, "Lookup \'" + word + "\'");
		menu.setHeaderTitle("MENU FOOL");
		
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		if(item.getItemId()==0){
			System.out.println("first item");
			//getTranslation(writeMessage.getSelectedWord());
			getTranslate();
		}
		writeMessage.setSelection(0);		
		
		return false;
	}
	
	private void getTranslate(){
		TranslationHttpClient client = new TranslationHttpClient();

	//	System.out.println(client.getTranslation());

	}
	
	private void getTranslation(final String word){
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://twitter.com/statuses/user_timeline/vogella.json");
		try {
		      HttpResponse response = client.execute(httpGet);
		      StatusLine statusLine = response.getStatusLine();
		      int statusCode = statusLine.getStatusCode();
		      System.out.println(" Status Code = " +statusCode);
		      if (statusCode == 200) {
		        HttpEntity entity = response.getEntity();
		        InputStream content = entity.getContent();
		        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		        String line;
		        while ((line = reader.readLine()) != null) {
		          builder.append(line);
		        }
		      } else {
		        Log.e( "Failed to download file", word);
		      
		      
		      }
		}
		      catch (ClientProtocolException e) {
		          e.printStackTrace();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
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
