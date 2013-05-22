/**
 * 创建人：SongZhiyong
 * 创建时间：2013-1-3
 */
package com.jerome.utils.format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 时间日期解析格式化
 * 
 * @author SongZhiyong
 * 
 */
public class DateTimeFormatter {
	/** UTC时间样式 */
	public static final String UTC_TIME_PATTERN = "E MMM dd HH:mm:ss ZZZZ yyyy";
	/** 普通时间样式 */
	public static final String NORMAL_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 根据字符串及样式解析出时间Date
	 * 
	 * @param pattern 样式
	 * @param time 时间
	 * @return date 解析完成返回的日期
	 * @throws ParseException 解析错误
	 */
	public static Date parse(String pattern, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(time);
	}
	/**
	 * 根据日期和样式格式化时间
	 * 
	 * @param pattern 样式
	 * @param date  输入日期
	 * @return 格式化之后的时间字符串
	 */
	public static String format(String pattern, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
}
