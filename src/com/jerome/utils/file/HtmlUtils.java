package com.jerome.utils.file;

/**
 * HtmlUtils <br>
 * Function: Html标签和string的相互转换
 * 
 * @author Jerome Song
 */
public class HtmlUtils {
	/**
	 * 把html标签转换成String类能够承载的
	 * 
	 * @param str
	 * @return
	 */
	public static String htmlToString(String str) {
		if (str == null)
			return "";
		if (str.equals(""))
			return "";
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("&amp;amp;", "&amp;");
		str = str.replaceAll("&amp;quot;", "&quot;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("&amp;lt;", "&lt;");
		str = str.replaceAll("&amp;gt;", "&gt;");
		str = str.replaceAll("&amp;nbsp;", "&nbsp;");
		return str;
	}

	/**
	 * 把String转换成html标签
	 * 
	 * @param str
	 * @return
	 */
	public static String stringToHtml(String str) {
		if (str == null)
			return "";
		if (str.equals(""))
			return "";
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&quot;", "\"");
		str = str.replaceAll("&nbsp;", " ");
		return str;
	}
}
