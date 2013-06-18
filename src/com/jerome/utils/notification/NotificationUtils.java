package com.jerome.utils.notification;

import android.app.NotificationManager;

/**
 * 
 * ClassName:NotificationUtils <br>
 * Function: 显示通知栏提示的工具类 <br>
 * Reason: TODO ADD REASON <br>
 * 
 * @author Jerome
 * @version
 * @since Ver 1.1
 * @Date 2013 2013-6-13 上午9:20:00
 * 
 */
public class NotificationUtils {
	private static NotificationManager notificationManager;

//	public static void showNotification(int mode, String contentText,
//			String name, Intent intent) {
//		notificationManager = (NotificationManager) context
//				.getSystemService(context.NOTIFICATION_SERVICE);
//		Notification notification = new Notification();
//		MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.msg_in);
//		mediaPlayer.start();
//		notification.defaults = Notification.DEFAULT_VIBRATE;
//		notification.flags = Notification.FLAG_AUTO_CANCEL;
//		String text = contentText;
//		String title = name;
//		switch (mode) {
//		case ChatMessage.CHAT_MESSAGE_MODE_AUDIO:
//			text = "[" + getString(R.string.text_add_audio) + "]";
//			break;
//		case ChatMessage.CHAT_MESSAGE_MODE_CARD:
//			text = "[" + getString(R.string.text_add_card) + "]";
//			break;
//		case ChatMessage.CHAT_MESSAGE_MODE_IMG:
//			text = "[" + getString(R.string.text_add_image) + "]";
//			break;
//		case ChatMessage.CHAT_MESSAGE_MODE_LOCATION:
//			text = "[" + getString(R.string.text_add_location) + "]";
//			break;
//		case ChatMessage.CHAT_MESSAGE_MODE_VIDEO:
//			text = "[" + getString(R.string.text_add_video) + "]";
//			break;
//		case ChatMessage.CHAT_MESSAGE_MODE_TEXT:
//			break;
//		case ChatMessage.CHAT_MESSAGE_MODE_SHARE:
//			text = "[" + getString(R.string.text_add_share) + "]";
//			break;
//		default:
//			break;
//		}
//		notification.icon = R.drawable.notification_icon;
//		notification.tickerText = context.getResources().getString(
//				R.string.chatMessage_string);
//		intent.putExtra("from", "notification");
//		PendingIntent pi = PendingIntent.getActivity(context, 1111, intent,
//				PendingIntent.FLAG_UPDATE_CURRENT);
//		notification.setLatestEventInfo(context, title, text, pi);
//		notificationManager.cancel(1111);
//		notificationManager.notify(1111, notification);
//	}
//
//	@SuppressWarnings("static-access")
//	public static void clearNotifications() {
//		notificationManager = (NotificationManager) context
//				.getSystemService(context.NOTIFICATION_SERVICE);
//		notificationManager.cancel(1111);
//	}
//
//	public static final String getString(int id) {
//		return DoveboxApp.getInstance().getString(id);
//	}
//
//	public static final void showSendingNoti() {
//		notificationManager = (NotificationManager) context
//				.getSystemService(Context.NOTIFICATION_SERVICE);
//		String tickerText = getString(R.string.text_sending_dashboard);
//		int icon = android.R.drawable.stat_sys_upload;
//		String contentTitle = getString(R.string.app_name);
//		String contentText = getString(R.string.text_let_dove_flying);
//		Intent notificationIntent = new Intent();
//		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
//				notificationIntent, 0);
//		Notification notification = new Notification(icon, tickerText,
//				System.currentTimeMillis());
//		notification.flags |= Notification.FLAG_INSISTENT;
//		notification.defaults |= Notification.DEFAULT_LIGHTS;
//		notification.setLatestEventInfo(context, contentTitle, contentText,
//				contentIntent);
//		// 显示这个notification
//		notificationManager.notify(1234, notification);
//	}
//
//	public static final void clearSendNoti() {
//		notificationManager = (NotificationManager) context
//				.getSystemService(Context.NOTIFICATION_SERVICE);
//		notificationManager.cancel(1234);
//	}
}
