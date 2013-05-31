/**
 * 创建人：SongZhiyong
 * 创建时间：2013-1-5
 */
package com.jerome.basebeans;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;

/**
 * 基本Application类，包含一些程序基本信息
 * 
 * @author Jerome
 */
public class BaseApplication extends Application {
	public static final int DEBUG = 0; // 调试
	public static final int RELEASE = 1; // 发布

	// 控制软件模式
	public static int STAT_DISTRIBUTE;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		// 控制软件模式
		STAT_DISTRIBUTE = DEBUG;
		super.onCreate();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
	}

	@Override
	public void registerActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback) {
		super.registerActivityLifecycleCallbacks(callback);
	}

	@Override
	public void registerComponentCallbacks(ComponentCallbacks callback) {
		super.registerComponentCallbacks(callback);
	}

	@Override
	public void unregisterActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback) {
		super.unregisterActivityLifecycleCallbacks(callback);
	}

	@Override
	public void unregisterComponentCallbacks(ComponentCallbacks callback) {
		super.unregisterComponentCallbacks(callback);
	}

}
