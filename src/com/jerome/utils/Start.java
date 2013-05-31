package com.jerome.utils;

import com.jerome.lib_song.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * 
 * Activity的启动器
 * 
 * @author SongZhiyong
 */
public class Start {
	// 左侧滑入
	public static final int MODE_LEFT_IN_RIGHT_OUT = 0;
	// 右侧滑入
	public static final int MODE_RIGHT_IN_LEFT_OUT = 1;
	// 顶部滑入
	public static final int MODE_UP_IN_BOTTOM_OUT = 2;
	// 底部滑入
	public static final int MODE_BOTTOM_IN_UP_OUT = 3;
	// 缩放
	public static final int MODE_ZOOM_IN_ZOOM_OUT = 4;
	// 渐变
	public static final int MODE_FADE_IN_FADE_OUT = 5;

	// 不需要返回结果的启动新Activity,无请求码
	public static final int REQUEST_CODE_INVALID = -1;

	/**
	 * Fragment启动Activity
	 * 
	 * @param fragment
	 *            当前Fragment
	 * @param cls
	 *            目标Activity字节码
	 */
	public static void start(Fragment fragment, Class<?> cls) {
		start(null, fragment, cls, null, REQUEST_CODE_INVALID, 0, 0);
	}

	/**
	 * Fragment启动Activity(携带数据)
	 * 
	 * @param fragment
	 *            当前Fragment
	 * @param cls
	 *            目标Activity字节码
	 * @param extras
	 *            携带数据的bundle
	 */
	public static void start(Fragment fragment, Class<?> cls, Bundle extras) {
		start(null, fragment, cls, extras, REQUEST_CODE_INVALID, 0, 0);
	}

	/**
	 * Fragment启动Activity(forResult)
	 * 
	 * @param fragment
	 *            当前Fragment
	 * @param cls
	 *            目标Activity字节码
	 * @param reqCode
	 *            请求码
	 */
	public static void start(Fragment fragment, Class<?> cls, int reqCode) {
		start(null, fragment, cls, null, reqCode, 0, 0);
	}

	/**
	 * Fragment启动Activity(携带数据，forResult)
	 * 
	 * @param fragment
	 *            当前Fragment
	 * @param cls
	 *            目标Activity字节码
	 * @param extras
	 *            携带数据的bundle
	 * @param reqCode
	 *            请求码
	 */
	public static void start(Fragment fragment, Class<?> cls, Bundle extras,
			int reqCode) {
		start(null, fragment, cls, extras, reqCode, 0, 0);
	}

	/**
	 * Activity启动Activity
	 * 
	 * @param activity
	 *            当前Activity
	 * @param cls
	 *            目标Activity字节码
	 */
	public static void start(Activity activity, Class<?> cls) {
		start(activity, null, cls, null, REQUEST_CODE_INVALID, 0, 0);
	}

	/**
	 * Activity启动Activity(携带数据)
	 * 
	 * @param activity
	 *            当前Activity
	 * @param cls
	 *            目标Activity字节码
	 * @param extras
	 *            携带数据的bundle
	 */
	public static void start(Activity activity, Class<?> cls, Bundle extras) {
		start(activity, null, cls, extras, REQUEST_CODE_INVALID, 0, 0);
	}

	/**
	 * Activity启动Activity(forResult)
	 * 
	 * @param activity
	 *            当前Activity
	 * @param cls
	 *            目标Activity字节码
	 */
	public static void start(Activity activity, Class<?> cls, int reqCode) {
		start(activity, null, cls, null, reqCode, 0, 0);
	}

	/**
	 * Activity启动Activity(携带数据，forResult)
	 * 
	 * @param activity
	 *            当前Activity
	 * @param cls
	 *            目标Activity字节码
	 * @param extras
	 *            携带数据的bundle
	 */
	public static void start(Activity activity, Class<?> cls, Bundle extras,
			int reqCode) {
		start(activity, null, cls, extras, reqCode, 0, 0);
	}

	/**
	 * Activity启动Activity(动画效果)
	 * 
	 * @param activity
	 *            当前Activity
	 * @param cls
	 *            目标Activity字节码
	 * @param enterAnim
	 *            新Activity进入动画
	 * @param exitAnim
	 *            原Activity退出动画
	 * @deprecated 将渐变动画改为模式,下面废弃方法相同，如有更好动画可以自定义<br>
	 *             ，但应用中尽量统一
	 */
	public static void start(Activity activity, Class<?> cls, int enterAnim,
			int exitAnim) {
		start(activity, null, cls, null, REQUEST_CODE_INVALID, enterAnim,
				exitAnim);
	}

	/**
	 * Activity启动Activity(动画效果+携带数据)
	 * 
	 * @param activity
	 *            当前Activity
	 * @param cls
	 *            目标Activity字节码
	 * @param extras
	 *            携带数据的bundle
	 * @param enterAnim
	 *            新Activity进入动画
	 * @param exitAnim
	 *            原Activity退出动画
	 * @deprecated
	 */
	public static void start(Activity activity, Class<?> cls, Bundle extras,
			int enterAnim, int exitAnim) {
		start(activity, null, cls, extras, REQUEST_CODE_INVALID, enterAnim,
				exitAnim);
	}

	/**
	 * Activity启动Activity(动画效果+forResult)
	 * 
	 * @param activity
	 *            当前Activity
	 * @param cls
	 *            目标Activity字节码
	 * @param extras
	 *            携带数据的bundle
	 * @param enterAnim
	 *            新Activity进入动画
	 * @param exitAnim
	 *            原Activity退出动画
	 * @deprecated
	 */
	public static void start(Activity activity, Class<?> cls, int reqCode,
			int enterAnim, int exitAnim) {
		start(activity, null, cls, null, reqCode, enterAnim, exitAnim);
	}

	/**
	 * Activity启动Activity(动画效果+携带数据+forResult)
	 * 
	 * @param activity
	 *            当前Activity
	 * @param cls
	 *            目标Activity字节码
	 * @param extras
	 *            携带数据的bundle
	 * @param enterAnim
	 *            新Activity进入动画
	 * @param exitAnim
	 *            原Activity退出动画
	 * @deprecated
	 */
	public static void start(Activity activity, Class<?> cls, Bundle extras,
			int reqCode, int enterAnim, int exitAnim) {
		start(activity, null, cls, extras, reqCode, enterAnim, exitAnim);
	}

	/**
	 * Activity跳转方法
	 * 
	 * @param activity
	 *            当前Activity
	 * @param fragment
	 *            当前Fragment
	 * @param cls
	 *            目标Activity字节码
	 * @param extras
	 *            携带数据的bundle
	 * @param reqCode
	 *            请求码
	 * @param enterAnim
	 *            新Activity进入动画
	 * @param exitAnim
	 *            原Activity退出动画
	 * 
	 */
	private static void start(Activity activity, Fragment fragment,
			Class<?> cls, Bundle extras, int reqCode, int enterAnim,
			int exitAnim) {
		if (null != activity) {
			Intent intent = new Intent(activity, cls);
			if (null != extras) {
				intent.putExtras(extras);
			}
			if (REQUEST_CODE_INVALID == reqCode) {
				activity.startActivity(intent);
			} else {
				activity.startActivityForResult(intent, reqCode);
			}
			if (0 != enterAnim || 0 != exitAnim) {
				activity.overridePendingTransition(enterAnim, exitAnim);
			}
		} else if (null != fragment) {
			activity = fragment.getActivity();
			if (null != activity) {
				Intent intent = new Intent(activity, cls);
				if (null != extras) {
					intent.putExtras(extras);
				}
				if (REQUEST_CODE_INVALID == reqCode) {
					fragment.startActivity(intent);
				} else {
					fragment.startActivityForResult(intent, reqCode);
				}
				if (0 != enterAnim || 0 != exitAnim) {
					activity.overridePendingTransition(enterAnim, exitAnim);
				}
			}
		}
	}

	public static void start(int mode, Activity activity, Class<?> cls) {
		start(mode, activity, cls, null, REQUEST_CODE_INVALID);
	};

	public static void start(int mode, Activity activity, Class<?> cls,
			int reqCode) {
		start(mode, activity, cls, null, reqCode);
	}

	public static void start(int mode, Activity activity, Class<?> cls,
			Bundle extras) {
		start(mode, activity, cls, extras, REQUEST_CODE_INVALID);
	}

	public static void start(int mode, Activity activity, Class<?> cls,
			Bundle extras, int reqCode) {
		int enterAnim = 0;
		int exitAnim = 0;
		switch (mode) {
		case MODE_LEFT_IN_RIGHT_OUT:
			enterAnim = R.anim.slide_in_from_left;
			exitAnim = R.anim.slide_out_to_right;
			break;
		case MODE_RIGHT_IN_LEFT_OUT:
			enterAnim = R.anim.slide_in_from_right;
			exitAnim = R.anim.slide_out_to_left;
			break;
		case MODE_UP_IN_BOTTOM_OUT:
			enterAnim = R.anim.slide_in_from_top;
			exitAnim = R.anim.slide_out_to_bottom;
			break;
		case MODE_BOTTOM_IN_UP_OUT:
			enterAnim = R.anim.slide_in_from_top;
			exitAnim = R.anim.slide_out_to_bottom;
			break;
		case MODE_ZOOM_IN_ZOOM_OUT:
			enterAnim = R.anim.zoom_in;
			exitAnim = R.anim.zoom_out;
			break;
		case MODE_FADE_IN_FADE_OUT:
			enterAnim = android.R.anim.fade_in;
			exitAnim = android.R.anim.fade_out;
			break;

		default:
			break;
		}
		start(activity, null, cls, extras, reqCode, enterAnim, exitAnim);
	}
}
