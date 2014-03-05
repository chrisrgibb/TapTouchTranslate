package com.example.messageclient;

import android.content.Context;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;

public class WriteMessage extends EditText {
	public static int _SelectedBackgroundColor = 0xffA6D4E1;
	public static int _SelectedTextColor = 0xff000000;
	private OnTouchListener lastOnTouch;
	protected int textOffsetStart;
	protected int textOffsetEnd;
	private OnLongClickListener lastOnLongClick;
	protected boolean longCliked;
	protected boolean isDowned;
	protected int textSelectedEnd;
	protected int textSelectedStart;
	private static SelectableTextView lastInstance;

	public WriteMessage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	private OnLongClickListener getSelectableLongClick() {
		return new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				longCliked = true;
				if (lastOnLongClick != null) {
					lastOnLongClick.onLongClick(v);
				}
				return true;
			}
		};
	}

}
