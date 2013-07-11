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

import com.alibaba.fastjson.JSONWriter.Context;
import com.jerome.base.Log;

/**
 * ClassName:Settings Function: TODO ADD FUNCTION
 * 
 * @author Jerry
 * @version
 * @Date 2013-7-11 下午5:24:10
 * 
 * @see
 */
public class Settings {
	public static timeIs24HourFormat(Context context) {
		ContentResolver cv = context.getContentResolver();
		// 获取当前系统设置
		String strTimeFormat = android.provider.Settings.System.getString(cv,
				android.provider.Settings.System.TIME_12_24);

		ContentResolver cv = getContentResolver();
		// 获取当前系统设置
		String strTimeFormat = android.provider.Settings.System.getString(cv,
				android.provider.Settings.System.TIME_12_24);
		return strTimeFormat.equals("24");
	}

}
