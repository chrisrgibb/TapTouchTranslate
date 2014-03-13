package com.example.messageclient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
		int offset = getOffsetForLastDownPosition();
		System.out.println("onLongClick from MessageEditText");
		System.out.println(getLastDownPositionX() + "  " + offset );
		return false;
	}
	
	
	public TextMessage getMessageText(){
		return new TextMessage(getText().toString());
	}
	
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
			
		case (MotionEvent.ACTION_UP):
			System.out.println("UP BUtton pressed messageEditText");
		}
		System.out.println("on touch event");

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
