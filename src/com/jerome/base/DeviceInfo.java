/**
 * 创建人：SongZhiyong
 * 创建时间：2013-1-5
 */
package com.jerome.base;

import java.io.Serializable;
import java.util.UUID;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 设备相关信息
 * 
 * @author SongZhiyong
 * 
 */
public class DeviceInfo implements Serializable {
	private static final long serialVersionUID = 3868922105621459535L;
	/** 品牌，制造商 */
	private String MANUFACTURER;
	/** 型号 */
	private String MODEL;
	/** 系统对应的SDK级别level */
	private int SDK;
	/** Android系统版本 */
	private String REALEASE;

	/** 唯一物理标识码 */
	private DeviceInfo instance = new DeviceInfo();

	private DeviceInfo() {
		super();
		MANUFACTURER = android.os.Build.MANUFACTURER;
		MODEL = android.os.Build.MODEL;
		SDK = android.os.Build.VERSION.SDK_INT;
		REALEASE = android.os.Build.VERSION.RELEASE;
	}

	public DeviceInfo getInstance() {
		return instance;
	}

	/**
	 * 获取设备唯一标识码<br>
	 * ps:注意清单文件中添加权限<br>
	 * --- <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceId(Context context) {
		final String tmDevice, tmSerial, androidId;
		final TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = ""
				+ android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		return deviceUuid.toString();
	}

	public String getMANUFACTURER() {
		return MANUFACTURER;
	}

	public String getMODEL() {
		return MODEL;
	}

	public int getSDK() {
		return SDK;
	}

	public String getREALEASE() {
		return REALEASE;
	}
}
