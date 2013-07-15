/**
 * 创建人：SongZhiyong
 * 创建时间：2013-1-5
 */
package com.jerome.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * @author SongZhiyong
 * 
 */
public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * 
	 * getDrawableResID:根据资源名称获取其ID
	 * 
	 * @param @param imageName
	 * @param @return
	 * @return int
	 * @throws
	 * @since 下午4:41:53
	 */
	public int getDrawableResID(String imageName) {
		Context ctx = getBaseContext();
		int resId = getResources().getIdentifier(imageName, "drawable",
				ctx.getPackageName());
		return resId;
	}
}
