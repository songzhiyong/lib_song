/**
 * 创建人：SongZhiyong
 * 创建时间：2012-12-30
 */
package com.jerome.utils.dimen;
import android.content.Context;
/**
 * 功能：尺寸转化
 * 
 * @author SongZhiyong
 * 
 */
public class DimenUtils {
	/**
	 * 设备独立像素dip(dp)--->像素px
	 * 
	 * @param 上下文对象
	 * @param dipValue
	 *            需要转换的dp值
	 * @return dp转化为像素的值
	 */
	public static int dip2px(Context context, float dipValue) {
		final float density = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * density + 0.5f);
	}
	/**
	 * 像素转换为设备独立像素
	 * 
	 * @param context
	 *            上下文对象
	 * @param pxValue
	 *            像素值
	 * @return 像素转化为设备独立像素的值
	 */
	public static int px2dip(Context context, float pxValue) {
		final float density = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / density + 0.5f);
	}
}
