package com.jerome.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jerome.base.Log;

public class HKImageView extends ImageView {

	public HKImageView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public HKImageView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Log.d("lg", "onDraw");
		super.onDraw(canvas);

		// 画边框
		Rect rect1 = getRect(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.GRAY);
		paint.setStyle(Paint.Style.STROKE);

		// 画边框
		canvas.drawRect(rect1, paint);

		paint.setColor(Color.LTGRAY);

		// 画一条竖线,模拟右边的阴影
		canvas.drawLine(rect1.right + 1, rect1.top + 2, rect1.right + 1,
				rect1.bottom + 2, paint);
		// 画一条横线,模拟下边的阴影
		canvas.drawLine(rect1.left + 2, rect1.bottom + 1, rect1.right + 2,
				rect1.bottom + 1, paint);

		// 画一条竖线,模拟右边的阴影
		canvas.drawLine(rect1.right + 2, rect1.top + 3, rect1.right + 2,
				rect1.bottom + 3, paint);
		// 画一条横线,模拟下边的阴影
		canvas.drawLine(rect1.left + 3, rect1.bottom + 2, rect1.right + 3,
				rect1.bottom + 2, paint);
	}

	Rect getRect(Canvas canvas) {
		Rect rect = canvas.getClipBounds();
		rect.bottom -= getPaddingBottom();
		rect.right -= getPaddingRight();
		rect.left += getPaddingLeft();
		rect.top += getPaddingTop();
		return rect;
	}
}
