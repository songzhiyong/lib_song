package com.jerome.interfaces;

/**
 * 
 * ClassName:IApiResponseHandler <br>
 * Function:网络请求结果的Handler接口 <br>
 * 
 * @author Jerome Song
 * @version @param <R>
 * @Date 2013 2013-7-21 上午8:10:40
 * 
 * @see @param <R>
 */
public interface IApiResponseHandler<R> {

	// 请求成功
	public static final byte STATUS_SUCCESS = 1;
	// 请求失败
	public static final byte STATUS_FAILURE = 0;
	// 请求异常
	public static final byte STATUS_EXCEPTION = -1;

	public void onResult(int api, byte status, R response);
}
