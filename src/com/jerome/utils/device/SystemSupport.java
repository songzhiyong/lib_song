package com.jerome.utils.device;

import java.io.File;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Android系统启动其他自带程序方法
 * 
 * @author SongZhiyong
 * 
 */
public class SystemSupport {
	public static final String MIME_TYPE_EMAIL = "message/rfc822";
	public static final String MIME_TYPE_TEXT = "text/*";
	public static final String MAP_GOOGLE_TRAFICLINE_API = "http://ditu.google.cn/maps?f=d&source=s_d&saddr=%s,%s&daddr=%s,%s";

	/**
	 * 启动系统短信的Intent
	 * 
	 * @param message
	 *            要发送的短信文本
	 * 
	 */
	public static void startMessage(Context context, String message) {
		Uri uri = Uri.parse("smsto:");
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		intent.putExtra("sms_body", message);
		context.startActivity(intent);
	}

	/**
	 * 通过号码启动系统短信的Intent
	 * 
	 * @param context
	 *            上下文对象
	 * @param message
	 *            要发送的文本消息
	 * @param number
	 *            目标号码
	 */
	public static void startMessageWithNumber(Context context, String message,
			String number) {
		Uri uri = null;
		if (number != null) {
			uri = Uri.parse("smsto:" + number);
		} else {
			uri = Uri.parse("smsto:");
		}
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		intent.putExtra("sms_body", message);
		context.startActivity(intent);
	}

	/**
	 * 获取发送Email的Intent
	 * 
	 * @param address
	 *            目标地址
	 * @param subject
	 *            主题
	 * @param message
	 *            Email内容
	 */
	public static void startEmail(Context context, String[] address,
			String subject, String body) {
		Uri uri = Uri.parse("mailto:");
		Intent intent = new Intent(Intent.ACTION_SEND, uri);
		intent.putExtra(Intent.EXTRA_EMAIL, address);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, body);
		intent.setType(MIME_TYPE_EMAIL);
		context.startActivity(intent);
	}

	/**
	 * 直接启动系统媒体库获取选中结果
	 * 
	 * @param context
	 *            上下文对象
	 * @param reqCode
	 *            请求码
	 * @param isImage
	 *            启动图库(true) 启动视频库(false)
	 */
	public static void startImagesStoreForResult(Context context, int reqCode,
			boolean isImage) {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		if (isImage) {
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					"image/*");
		} else {
			intent.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
					"video/*");
		}
		((Activity) context).startActivityForResult(intent, reqCode);
	}

	/**
	 * 启动系统安装的所有选择多媒体选项
	 * 
	 * @param context
	 *            上下文对象
	 * @param reqCode
	 *            请求码
	 * @param isImage
	 *            启动图库(true) 启动视频库(false)
	 */
	public static void startChooserForResult(Context context, int reqCode,
			boolean isImage) {
		Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT); // "android.intent.action.GET_CONTENT"
		innerIntent.setType(isImage ? "image/*" : "video/*");
		Intent wrapperIntent = Intent.createChooser(innerIntent, null);
		((Activity) context).startActivityForResult(wrapperIntent, reqCode);
	}

	/**
	 * 启动网页地图，查询路线
	 * 
	 * @param context
	 *            上下文对象
	 * @param srcLat
	 *            起始点经度
	 * @param srcLng
	 *            起始点纬度
	 * @param tarLat
	 *            终止点经度
	 * @param tarLng
	 *            终止点纬度
	 */
	public static void startWebTraficLine(Context context, String srcLat,
			String srcLng, String tarLat, String tarLng) {
		String url = String.format(MAP_GOOGLE_TRAFICLINE_API, srcLat, srcLng,
				tarLat, tarLng);
		Uri uri = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(intent);
	}

	/**
	 * 启动系统位置与安全设置界面
	 * 
	 * @param context
	 *            上下文对象
	 */
	public static void startSecuritySettingsIntent(Context context) {
		context.startActivity(new Intent(
				"android.settings.LOCATION_SOURCE_SETTINGS"));
	}

	public static boolean saveImage2Gallery(Context context, File file) {
		final Uri STORAGE_URI = Images.Media.EXTERNAL_CONTENT_URI;
		String IMAGE_MIME_TYPE = "image/png";
		ContentValues values = new ContentValues(7);
		values.put(Images.Media.TITLE, file.getName());
		values.put(Images.Media.DISPLAY_NAME, file.getName());
		values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis());
		values.put(Images.Media.MIME_TYPE, IMAGE_MIME_TYPE);
		values.put(Images.Media.ORIENTATION, 0);
		values.put(Images.Media.DATA, file.getAbsolutePath());
		values.put(Images.Media.SIZE, 0);
		Uri uri = context.getContentResolver().insert(STORAGE_URI, values);
		return null != uri;
	}

	/**
	 * 实现输入法在窗口上切换显示
	 * 
	 * @param context
	 */
	public static void toggleSoftKeyboard(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * 隐藏键盘
	 * 
	 * @param context
	 * @param v
	 */
	public static void hideSoftKeyboard(Context context, View v) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getApplicationWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 显示键盘
	 * 
	 * @param context
	 * @param v
	 */
	public static void showSoftKeyboard(Context context, View v) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
		imm.showSoftInputFromInputMethod(v.getApplicationWindowToken(),
				InputMethodManager.SHOW_IMPLICIT);
	}

}
