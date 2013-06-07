package com.jerome.utils.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * ClassName:FileUtils <br>
 * Function: Android文件工具类
 * 
 * @author Jerome Song
 * @version
 * @since Ver 1.1
 * @Date 2013 2013-6-6 上午10:00:57
 * 
 * @see
 */
public class FileUtils {
	public static String file2String(String filePath) throws IOException {
		StringBuilder data = new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader(
				new File(filePath)));
		String string;
		while ((string = in.readLine()) != null) {
			data.append(string);
		}
		in.close();
		return data.toString();
	}

	/**
	 * 把文件读取为字节数组
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 输出字节数组
	 * @throws IOException
	 */
	public static byte[] file2ByteArray(String filePath) throws IOException {
		byte[] buffer = new byte[1024];
		int len = -1;
		ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();
		FileInputStream inputStream = new FileInputStream(filePath);
		while ((len = inputStream.read(buffer)) != -1) {
			mOutputStream.write(buffer, 0, len);
		}
		mOutputStream.close();
		inputStream.close();
		return mOutputStream.toByteArray();
	}
}