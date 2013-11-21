package com.jerome.utils.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

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

	/**
	 * 把文件读取为字符串
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 输出的字符串
	 * @throws IOException
	 */
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

	/**
	 * 将文件大小进行相应格式化
	 * 
	 * @param size
	 *            文件大小 (单位byte)
	 * @return
	 */
	public static String getReadableFileSize(int size) {
		final int BYTES_IN_KILOBYTES = 1024;
		final DecimalFormat dec = new DecimalFormat("###.#");
		final String KILOBYTES = " KB";
		final String MEGABYTES = " MB";
		final String GIGABYTES = " GB";
		float fileSize = 0;
		String suffix = KILOBYTES;

		if (size > BYTES_IN_KILOBYTES) {
			fileSize = size / BYTES_IN_KILOBYTES;
			if (fileSize > BYTES_IN_KILOBYTES) {
				fileSize = fileSize / BYTES_IN_KILOBYTES;
				if (fileSize > BYTES_IN_KILOBYTES) {
					fileSize = fileSize / BYTES_IN_KILOBYTES;
					suffix = GIGABYTES;
				} else {
					suffix = MEGABYTES;
				}
			}
		}
		return String.valueOf(dec.format(fileSize) + suffix);
	}
}
