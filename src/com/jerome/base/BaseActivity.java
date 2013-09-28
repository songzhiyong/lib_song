/**
 * 创建人：SongZhiyong
 * 创建时间：2013-1-5
 */
package com.jerome.base;

import com.jerome.client.ApiResponseHandler;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author SongZhiyong
 * 
 */
public abstract class BaseActivity<APP extends BaseApplication, R> extends Activity {
	protected APP mApp;
	protected String mClassName;
	protected ApiResponseHandler<R> mApiResultHandler;

	public BaseActivity() {
		mClassName = getClass().getSimpleName();
		Log.d(mClassName, String.format("%s.%s()", mClassName, mClassName));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApp = (APP) getApplication();
		mApiResultHandler = getApiResultHandler();
		Log.d(mClassName, String.format("%s.onCreate(savedInstanceState=%s)",
				mClassName, savedInstanceState));
	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		return super.onCreateView(name, context, attrs);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.d(mClassName, String.format("%s.onNewIntent()", mClassName));
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(mClassName, String.format("%s.onRestart()", mClassName));
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(mClassName, String.format("%s.onStart()", mClassName));
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();
		Log.d(mClassName, String.format("%s.onPostResume()", mClassName));
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(mClassName,
				String.format(
						"%s.onResume(NativeHeapAllocatedSize:%d, NativeHeapFreeSize:%d)",
						mClassName,
						android.os.Debug.getNativeHeapAllocatedSize(),
						android.os.Debug.getNativeHeapFreeSize()));
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.d(mClassName, String.format(
				"%s.onConfigurationChanged(newConfig=%s)", mClassName,
				newConfig));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(mClassName, String.format(
				"%s.onActivityResult(requestCode=%d, resultCode=%d, data=%s)",
				mClassName, requestCode, resultCode, data));
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Log.d(mClassName, String.format("%s.onBackPressed()", mClassName));
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(mClassName,
				String.format("%s.onRestoreInstanceState()", mClassName));
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d(mClassName, String.format("%s.onSaveInstanceState()", mClassName));
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onPause() {
		Log.d(mClassName, String.format("%s.onPause()", mClassName));
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.d(mClassName, String.format("%s.onStop()", mClassName));
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.d(mClassName, String.format("%s.onDestroy()", mClassName));
		super.onDestroy();
	}

	@Override
	public void finish() {
		Log.d(mClassName, String.format("%s.finish()", mClassName));
		super.finish();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		return mApiResultHandler.onCreateDialog(id);
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		mApiResultHandler.onPrepareDialog(id, dialog);
	}

	protected abstract ApiResponseHandler<R> getApiResultHandler();

	/**
	 * 
	 * getDrawableResID:根据drawable资源名称获取其ID
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
