/**
 * 创建人：SongZhiyong
 * 创建时间：2013-1-5
 */
package com.jerome.base;
import java.io.Serializable;
/**
 * 设备相关信息
 * 
 * @author SongZhiyong
 * 
 */
public class DeviceInfo implements Serializable {
	private static final long serialVersionUID = 3868922105621459535L;
	/** 品牌，制造商 */
	String MANUFACTURER;
	/** 型号 */
	String MODEL;
	/** 系统对应的SDK级别level */
	String SDK;
	/** Android系统版本 */
	String REALEASE;
	private DeviceInfo instance = new DeviceInfo();
	private DeviceInfo() {
		super();
		MANUFACTURER = android.os.Build.MANUFACTURER;
		MODEL = android.os.Build.MODEL;
		SDK = android.os.Build.VERSION.SDK;
		REALEASE = android.os.Build.VERSION.RELEASE;
	}
	public DeviceInfo getInstance() {
		return instance;
	}
}
