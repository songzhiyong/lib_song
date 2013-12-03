/**
 * Settings.java
 * com.jerome.utils.device
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-7-11 		Jerry
 *
 * Copyright (c) 2013, JEROME All Rights Reserved.
 */

package com.jerome.utils.device;

import android.content.ContentResolver;
import android.content.Context;

/**
 * ClassName:Settings <br>
 * Function: 系统设置相关
 * 
 * @author Jerry
 * @version
 * @Date 2013-7-11 下午5:24:10
 * 
 * @see
 */
public class Settings {
	public static final String TIME_24 = "24";
	public static final String TIME_12 = "12";

	/**
	 * 判断系统时间设置是否为24小时制
	 * 
	 * @param context
	 *            上下文对象
	 * @return
	 */
	public static boolean timeIs24HourFormat(Context context) {
		ContentResolver cv = context.getContentResolver();
		// 获取当前系统设置
		String strTimeFormat = android.provider.Settings.System.getString(cv,
				android.provider.Settings.System.TIME_12_24);
		return strTimeFormat.equals(TIME_24);
	}

}
