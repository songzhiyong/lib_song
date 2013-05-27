/**
 * RoundCornerImageView.java
 * com.jerome.widget
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-5-25 		Jerome Song
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */

package com.jerome.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jerome.utils.media.BitmapUtils;

/**
 * ClassName:RoundCornerImageView<br>
 * 圆角正方形ImageView CenterCrop
 * 
 * @author Jerome Song
 * @version
 * @Date 2013-5-25 下午10:10:54
 * 
 * @see
 */
public class RoundCornerImageView extends ImageView {

	public RoundCornerImageView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);

	}

	public RoundCornerImageView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public RoundCornerImageView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		BitmapDrawable drawable = (BitmapDrawable) getDrawable();

		if (drawable == null) {
			return;
		}

		if (getWidth() == 0 || getHeight() == 0) {
			return;
		}

		Bitmap fullSizeBitmap = drawable.getBitmap();
		Bitmap roundBitmap = fullSizeBitmap;
		try {
			roundBitmap = BitmapUtils.scaleCenterCrop(fullSizeBitmap,
					Math.min(getWidth(), getHeight()),
					Math.min(getWidth(), getHeight()));
			roundBitmap = BitmapUtils.createRoundCornerBmp(roundBitmap, 8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		canvas.drawBitmap(roundBitmap, 0, 0, null);
		fullSizeBitmap.recycle();
	}
}
