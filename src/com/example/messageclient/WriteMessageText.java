package com.example.messageclient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class WriteMessageText extends EditText implements OnLongClickListener {
	private float lastDownPositionX;
	private float lastDownPositionY;
	PopupWindow popup;

	public WriteMessageText(Context context) {
		super(context);

		init(context);
		// TODO Auto-generated constructor stub
	}

	public WriteMessageText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public WriteMessageText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
		init(context);
	}

//	@SuppressLint("NewApi")
	public void init(Context context) {
		this.setOnLongClickListener(this);
		popup = new PopupWindow(context);
		popup.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT);
		TextView tv = new TextView(context);
		tv.setText("Helllo popup");
		LinearLayout ll = new LinearLayout(context);
//		ll.setOrientation(1);
		ll.addView(tv);
		//popup.setContentView(ll);
		//this.setTextIsSelectable(true);

	}

	@Override
	public boolean onLongClick(View v) {
		int offset = getOffsetForLastDownPosition();
		System.out.println("from WriteMessageTextgg");
		System.out.println(getLastDownPositionX() + "  " +getOffsetForLastDownPosition() );

		popup.showAtLocation(v, 0, 20, 20);
	
		return false;
	}
	
	
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
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
	

	// public OnLongClickListener getSelectableLongClick() {
	// return new OnLongClickListener() {
	//
	// @Override
	// public boolean onLongClick(View v) {
	// System.out.println("from WriteMessageText");
	// // longCliked = true;
	// // if (lastOnLongClick != null) {
	// // lastOnLongClick.onLongClick(v);
	// // }
	// return false;
	// }
	// };
	// }

}
