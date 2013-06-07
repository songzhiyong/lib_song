/**
 * 创建人：SongZhiyong
 * 创建时间：2013-1-5
 */
package com.jerome.base;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;

/**
 * 基本Application类，包含一些程序基本信息
 * 
 * @author Jerome
 */
public abstract class BaseApplication extends Application {

	public static final int DEBUG = 0; // 调试
	public static final int RELEASE = 1; // 发布

	// 控制软件模式
	protected static int STAT_DISTRIBUTE = DEBUG;
	// Application实例
	private Application mApp;
	// Application实例
	protected int ACTIVITY_SWITCH_MODE = -1;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mApp = this;
		setDistribute();
		switch (STAT_DISTRIBUTE) {
		case DEBUG:
			Log.setLogLevel(Log.VERBOSE);
		case RELEASE:
			Log.setLogLevel(Log.SUPPRESS);
			break;
		default:
			break;
		}
	}

	/** setDistribute:设置软件是否发布.子类实现 */
	protected abstract void setDistribute();

	protected abstract void setActivitySwitchMode();

	public Application getmApp() {
		return mApp;
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
