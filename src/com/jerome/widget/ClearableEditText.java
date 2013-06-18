package com.jerome.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.jerome.lib_song.R;

/**
 * 
 * ClassName:ClearableEditText <br>
 * Function: 可一键清空的EditText <br>
 * 
 * @author Jerry
 * @version
 * @Date 2013 2013-6-18 下午4:08:43
 * 
 * @see
 */
public class ClearableEditText extends EditText {

	public String defaultValue = "";
	final Drawable clearImg = getResources().getDrawable(
			R.drawable.edittext_clear);

	public ClearableEditText(Context context) {
		super(context);
		init();
	}

	public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ClearableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	private void init() {

		clearImg.setBounds(0, 0, clearImg.getIntrinsicWidth(),
				clearImg.getIntrinsicHeight());
		manageClearButton();
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				ClearableEditText et = ClearableEditText.this;
				if (et.getCompoundDrawables()[2] == null)
					return false;
				if (event.getAction() != MotionEvent.ACTION_UP)
					return false;
				if (event.getX() > et.getWidth() - et.getPaddingRight()
						- clearImg.getIntrinsicWidth()) {
					et.setText("");
					ClearableEditText.this.removeClearButton();
				}
				return false;
			}
		});

		this.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				ClearableEditText.this.manageClearButton();
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
	}

	private void manageClearButton() {
		if (this.getText().toString().equals(""))
			removeClearButton();
		else
			addClearButton();
	}

	private void addClearButton() {
		this.setCompoundDrawables(this.getCompoundDrawables()[0],
				this.getCompoundDrawables()[1], clearImg,
				this.getCompoundDrawables()[3]);
	}

	private void removeClearButton() {
		this.setCompoundDrawables(this.getCompoundDrawables()[0],
				this.getCompoundDrawables()[1], null,
				this.getCompoundDrawables()[3]);
	}

}