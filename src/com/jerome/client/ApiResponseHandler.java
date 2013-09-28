package com.jerome.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.jerome.interfaces.IApiResponseHandler;
import com.jerome.interfaces.IDialogFactory;
import com.jerome.lib_song.R;

/**
 * @author lifeix
 * 
 *         网络请求结果处理的Hanlder基类。其实例用于传给HttpTask中，回调给UI层。
 * 
 */
@SuppressWarnings("hiding")
public class ApiResponseHandler<R> implements IApiResponseHandler<R>,
		IDialogFactory, OnClickListener {

	private final int DIALOG_API_EXCEPTION = -1024;

	private Activity mActivity;

	public ApiResponseHandler(Activity activity) {
		mActivity = activity;
	}

	@Override
	public void onResult(int api, byte status, R response) {
		if (status == STATUS_EXCEPTION) {
			mActivity.showDialog(DIALOG_API_EXCEPTION);
		}
	}

	@Override
	public Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case DIALOG_API_EXCEPTION:
			AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setTitle(com.jerome.lib_song.R.string.network_error_label);
			builder.setMessage(com.jerome.lib_song.R.string.network_error_label);
			builder.setPositiveButton(android.R.string.cancel, this);
			dialog = builder.create();
			break;
		}
		return dialog;
	}

	@Override
	public void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case DIALOG_API_EXCEPTION:
			break;
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case AlertDialog.BUTTON_POSITIVE:
			dialog.dismiss();
			break;
		}
	}
}
