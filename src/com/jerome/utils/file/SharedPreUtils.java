/**
 * 创建人：SongZhiyong
 * 创建时间：2013-1-3
 */
package com.jerome.utils.file;
import android.content.Context;
import android.content.SharedPreferences;
/**
 * 对SharedPreferences的封装
 * 
 * @author SongZhiyong
 * 
 */
public class SharedPreUtils {
	// 文件名
	private static final String PREF_NAME = "config";
	private static SharedPreferences pref;
	private static SharedPreferences.Editor editor;
	public static void initialize(Context context) {
		// 程序私有
		pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		editor = pref.edit();
	}
	public static boolean contains(String key) {
		return pref.contains(key);
	}
	public static boolean get(String key, boolean defValue) {
		return pref.getBoolean(key, defValue);
	}
	public static float get(String key, float defValue) {
		return pref.getFloat(key, defValue);
	}
	public static int get(String key, int defValue) {
		return pref.getInt(key, defValue);
	}
	public static long get(String key, long defValue) {
		return pref.getLong(key, defValue);
	}
	public static String get(String key, String defValue) {
		return pref.getString(key, defValue);
	}
	public static void put(String key, boolean value) {
		editor.putBoolean(key, value);
	}
	public static void put(String key, float value) {
		editor.putFloat(key, value);
	}
	public static void put(String key, int value) {
		editor.putInt(key, value);
	}
	public static void put(String key, long value) {
		editor.putLong(key, value);
	}
	public static void put(String key, String value) {
		editor.putString(key, value);
	}
	public static void commit() {
		editor.commit();
	}
	public static void clear() {
		editor.clear();
	}
	public static void remove(String key) {
		editor.remove(key);
	}
}
