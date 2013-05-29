package com.jerome.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ScrollView;

class DigitalItemView extends ScrollView {
	protected int mDigital = 0;

	private ImageView mImageView;

	private DigitalItemView(Context context) {
		super(context);
	}

	private DigitalItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private DigitalItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public DigitalItemView(Context context, Bitmap bitmap) {
		super(context);
		setDigitalBitmap(bitmap);
	}

	/**
	 * @param bmpDigital
	 * @param digital
	 */
	private void setDigitalBitmap(Bitmap bitmap) {
		setVerticalScrollBarEnabled(false);
		setVerticalFadingEdgeEnabled(false);
		setHorizontalScrollBarEnabled(false);
		setHorizontalFadingEdgeEnabled(false);
		setFillViewport(true);

		mImageView = new ImageView(getContext());
		mImageView.setScaleType(ScaleType.MATRIX);
		mImageView.setImageBitmap(bitmap);
		addView(mImageView, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
	}

	/**
	 * Set the digital.
	 * 
	 * @param digital
	 *            Should be 0<= digital <=10.
	 * 
	 */
	public void setDigital(int digital) {
		if (digital >= 0 && digital <= 10 && mDigital != digital) {
			TranslateAnimation transAnim = new TranslateAnimation(
					Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0,
					Animation.RELATIVE_TO_SELF, -mDigital / 11.0f,
					Animation.RELATIVE_TO_SELF, -digital / 11.0f);
			transAnim.setDuration(300);
			transAnim.setFillBefore(true);
			transAnim.setFillAfter(true);
			mImageView.startAnimation(transAnim);
			mDigital = digital;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return false;
	}

	@Override
	public boolean onTrackballEvent(MotionEvent ev) {
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}
}
