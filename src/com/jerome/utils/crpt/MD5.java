package com.jerome.utils.crpt;

import java.security.MessageDigest;

/**
 * @author JeromeSong<br>
 *         MD5加密算法--单向加密用于验证，常与Base64一起使用
 */
public class MD5 {

	public static String encrypt(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		// 写法(1)，忒不专业，不推荐////////////////////
		// for (int i = 0; i < md5Bytes.length; i++) {
		// int val = ((int) md5Bytes[i]) & 0xff;
		// if (val < 16) {
		// hexValue.append("0");
		// }
		// hexValue.append(Integer.toHexString(val));
		// }
		// 写法(2)/////////////////////////
		// for (int i = 0; i < arr.length; ++i) {
		// sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
		// 3));
		// }
		// 写法(3)/////////////////////////////////////
		// for (byte b : toencode) {
		// sb.append(Integer.toHexString((b & 0xf0) >>> 4));
		// sb.append(Integer.toHexString(b & 0x0f));
		// }
		// ////////////////////////////////////
		for (int i = 0; i < md5Bytes.length; i++) {
			hexValue.append(String.format("%02x", ((int) md5Bytes[i]) & 0xff));
		}
		return hexValue.toString();
	}
	public static void main(String[] args) {
		System.out.println(encrypt("123456"));
	}
}
