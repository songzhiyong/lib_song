package com.utils;
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
	public static void start(Fragment fragment, Class<?> cls, Bundle extras, int reqCode) {
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
	public static void start(Activity activity, Class<?> cls, Bundle extras, int reqCode) {
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
	 */
	public static void start(Activity activity, Class<?> cls, int enterAnim, int exitAnim) {
		start(activity, null, cls, null, REQUEST_CODE_INVALID, enterAnim, exitAnim);
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
	 */
	public static void start(Activity activity, Class<?> cls, Bundle extras, int enterAnim,
			int exitAnim) {
		start(activity, null, cls, extras, REQUEST_CODE_INVALID, enterAnim, exitAnim);
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
	 */
	public static void start(Activity activity, Class<?> cls, int reqCode, int enterAnim,
			int exitAnim) {
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
	 */
	public static void start(Activity activity, Class<?> cls, Bundle extras, int reqCode,
			int enterAnim, int exitAnim) {
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
	 */
	public static void start(Activity activity, Fragment fragment, Class<?> cls, Bundle extras,
			int reqCode, int enterAnim, int exitAnim) {
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
}
