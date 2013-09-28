package com.jerome.interfaces;

import android.app.Dialog;

/**
 * 
 * ClassName:IDialogFactory <br>
 * Function: 能够create和prepare对话框的接口 <br>
 * 
 * @author Jerome Song
 * @version
 * @Date 2013 2013-7-21 上午8:10:07
 * 
 * @see
 */
public interface IDialogFactory {

	public Dialog onCreateDialog(int id);

	public void onPrepareDialog(int id, Dialog dialog);
}
