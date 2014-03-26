package com.example.messageclient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.PopupWindow;

public class MessageEditText extends EditText implements OnLongClickListener {
	private float lastDownPositionX;
	private float lastDownPositionY;
	PopupWindow popup;

	public MessageEditText(Context context) {
		super(context);
		init(context);
	}

	public MessageEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public MessageEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
		init(context);
	}


	private void init(Context context) {
		this.setOnLongClickListener(this);
	}

	@Override
	public boolean onLongClick(View v) {
		System.out.println("onLongClick from MessageEditText");
		setSelectionOfWord();
		//System.out.println(getLastDownPositionX() + "  " + offset );
		return false;
	}
	
	
	public TextMessage getMessageText(){
		return new TextMessage(getText().toString());
	}
	
	public String getSelectedWord(){
		TextMessage tm = getMessageText();
		System.out.println(this.getSelectionStart() + " > " +this.getSelectionEnd() );
		
		return tm.getWordAtIndex(getOffsetForLastDownPosition());
	}
	
	private void setSelectionOfWord(){
		int index = getOffsetForLastDownPosition();
		TextMessage tm = getMessageText();
		int start = tm.getWordStartOffset(index);
		int end = tm.getWordEndOffset(index);
		
		if(start > -1 && end > -1){
			this.setSelection(start, end);
		}
	
//		this.setSelection(tm.getWordStartOffset(index),	tm.getWordEndOffset(index));
		// THis is going to select the word on long press
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// fired everytime you press button
		switch (event.getAction()) {

		case (MotionEvent.ACTION_UP):
			System.out.println(" on touch UP BUtton pressed messageEditText");
			break;
		case (MotionEvent.ACTION_DOWN):
			System.out.println(" on touch DOWN BUtton pressed messageEditText");
			break;
		}

		final int action = event.getActionMasked();
		if (action == MotionEvent.ACTION_DOWN) {
			lastDownPositionX = event.getX();
			lastDownPositionY = event.getY();
		}
		return super.onTouchEvent(event);

	}

	public float getLastDownPositionX() {
		return lastDownPositionX;
	}

	public float getLastDownPositionY() {
		return lastDownPositionY;
	}

	@SuppressLint("NewApi")
	public int getOffsetForLastDownPosition() {

		if (Build.VERSION.SDK_INT > 13) {
			// as of SDK 14 the getOffsetForPosition was added to TextView
			return getOffsetForPosition(lastDownPositionX, lastDownPositionY);
		} else {
			return getOffsetForPositionOlderSdk();
		}
	}

	public int getOffsetForPositionOlderSdk() {
		if (getLayout() == null)
			return -1;
		final int line = getLineAtCoordinateOlderSDK(lastDownPositionY);
		final int offset = getOffsetAtCoordinateOlderSDK(line,
				lastDownPositionX);
		return offset;
	}

	public int getLineAtCoordinateOlderSDK(float y) {
		y -= getTotalPaddingTop();
		// Clamp the position to inside of the view.
		y = Math.max(0.0f, y);
		y = Math.min(getHeight() - getTotalPaddingBottom() - 1, y);
		y += getScrollY();
		return getLayout().getLineForVertical((int) y);
	}

	protected int getOffsetAtCoordinateOlderSDK(int line, float x) {
		x = convertToLocalHorizontalCoordinateOlderSDK(x);
		return getLayout().getOffsetForHorizontal(line, x);
	}

	protected float convertToLocalHorizontalCoordinateOlderSDK(float x) {
		x -= getTotalPaddingLeft();
		// Clamp the position to inside of the view.
		x = Math.max(0.0f, x);
		x = Math.min(getWidth() - getTotalPaddingRight() - 1, x);
		x += getScrollX();
		return x;
	}
	
	

}
