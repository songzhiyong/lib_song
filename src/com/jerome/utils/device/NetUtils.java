/**
 * 创建人：SongZhiyong
 * 创建时间：2013-1-3
 */
package com.jerome.utils.device;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;

import com.jerome.lib_song.R;

/**
 * 系统联网状态工具类
 * 
 * @author SongZhiyong
 * 
 */
public class NetUtils {
	/**
	 * 检查android联网状态
	 * 
	 * @param context
	 * @return
	 */
	public boolean checkNetworkState(final Context context) {
		boolean flag = false;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager.getActiveNetworkInfo() != null) {
			flag = manager.getActiveNetworkInfo().isAvailable();
		}
		if (!flag) {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setTitle(R.string.app_netstate);
			builder.setMessage(R.string.app_nonet);
			builder.setPositiveButton(R.string.settings,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent mIntent = null;
							if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {// 判断是否
								mIntent = new Intent(Settings.ACTION_SETTINGS);
							} else {
								mIntent = new Intent(
										Settings.ACTION_WIRELESS_SETTINGS);
							}
							context.startActivity(mIntent);
						}
					});
			builder.setNegativeButton(R.string.exit,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
			builder.create();
			builder.show();
		}
		return flag;
	}
}
