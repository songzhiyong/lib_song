package com.jerome.utils.media;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class ImageUtil {
	public static final Bitmap round(Bitmap paramBitmap, float paramFloat) {
		Bitmap localObject = null;
		if (paramBitmap == null)
			return localObject;
		try {
			Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(),
					paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
			localObject = localBitmap;
			if (localObject != null) {

				Canvas localCanvas = new Canvas(localObject);
				Paint localPaint = new Paint();
				Rect localRect = new Rect(0, 0, paramBitmap.getWidth(),
						paramBitmap.getHeight());
				RectF localRectF = new RectF(localRect);
				localPaint.setAntiAlias(true);
				localCanvas.drawRoundRect(localRectF, paramFloat, paramFloat,
						localPaint);
				localPaint.setXfermode(new PorterDuffXfermode(
						PorterDuff.Mode.SRC_IN));
				localCanvas.drawBitmap(paramBitmap, localRect, localRect,
						localPaint);
			}
		} catch (OutOfMemoryError localOutOfMemoryError) {
			Log.d("ImageUtil", "OOM occurred in ImageUtil.round"
					+ localOutOfMemoryError.getMessage());
		}
		return localObject;
	}
}

/*
 * Location: D:\android反编译\apk2java\dex2jar-0.0.9.9\jdgui\classes_dex2jar.jar
 * Qualified Name: com.tencent.mobileqq.utils.ImageUtil JD-Core Version: 0.5.4
 */