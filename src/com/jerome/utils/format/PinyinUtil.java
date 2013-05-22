/**
 * 创建人：SongZhiyong
 * 创建时间：2013-2-21
 */
package com.jerome.utils.format;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * 
 * 汉字转换为拼音
 * 
 * @author SongZhiyong
 * 
 */
public class PinyinUtil {
	/**
	 * 汉字转换为拼音
	 * 
	 * @param src
	 *            需要转换的汉语字符串
	 * @return 返回拼音字符串
	 */
	public static String getPingYin(String src) {
		char[] cs = null;
		cs = src.toCharArray();
		String[] s = new String[cs.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String str = "";
		int t0 = cs.length;
		try {
			for (int i = 0; i < t0; i++) {
				if (java.lang.Character.toString(cs[i]).matches("[\\u4E00-\\u9FA5]+")) {
					s = PinyinHelper.toHanyuPinyinStringArray(cs[i], t3);
					str += s[0];
				} else {
					str += java.lang.Character.toString(cs[i]);
				}
			}
			return str;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return str;
	}
}
