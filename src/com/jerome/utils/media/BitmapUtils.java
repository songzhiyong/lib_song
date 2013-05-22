/**
 * 
 * 创建人：SongZhiyong
 * 创建时间：2012-12-30
 */
package com.jerome.utils.media;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
/**
 * 功能：Bitmap相关操作及特效
 * 
 * @author SongZhiyong
 * 
 */
public class BitmapUtils {
	private static Bitmap destBmp = null;
	/**
	 * 创建圆角图片
	 * 
	 * @param bmp
	 *            源bitmap
	 * @param radius
	 *            圆角半径
	 * @return destBmp 已生成的圆角图片
	 * @throws Exception
	 *             圆角直径大于最小边长
	 */
	public static Bitmap createRoundCornerImg(Bitmap bmp, int radius) throws Exception {
		if (2 * radius > Math.min(bmp.getWidth(), bmp.getHeight())) {
			throw new Exception("参数错误");
		}
		destBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(destBmp);
		Paint paint = new Paint();
		Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
		RectF rectF = new RectF(rect);
		// 去锯齿
		paint.setAntiAlias(true);
		canvas.drawRoundRect(rectF, radius, radius, paint);
		// 设置相交模式
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bmp, null, rect, paint);
		return destBmp;
	}
	/**
	 * 保存图片到指定的路径
	 * 
	 * @param bmp
	 *            要保存的bitmap
	 * @param path
	 *            保存到的路径
	 * @param quality
	 *            保存图片质量
	 * @param format
	 *            压缩格式（CompressFormat.JPEG,CompressFormat.PNG）
	 * @throws IOException
	 *             IO异常
	 */
	public static void saveBitmap(CompressFormat format, Bitmap bmp, String path, int quality)
			throws IOException {
		File file = new File(path);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream stream = new FileOutputStream(file);
		bmp.compress(format, quality, stream);
	}
	/**
	 * 利用Matrix旋转图片
	 * 
	 * @param bmp
	 *            需要旋转的图片
	 * @param degree
	 *            旋转角度
	 * @return destBmp 旋转后的图片
	 */
	public static Bitmap rotate(Bitmap bmp, int degree) {
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		destBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
		bmp.recycle();
		return destBmp;
	}
	/**
	 * 根据传入的Bitmap对象构建带有倒影的Bitmap
	 * 
	 * @param bmp
	 *            图片位图
	 * @return Bitmap 返回带有倒影的位图Bitmap
	 * */
	public static Bitmap createWithReflectedImage(Bitmap bmp) {
		if (bmp == null) {
			return bmp;
		}
		// 图片与倒影之间的距离间隔
		int reflectionGap = 4;
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		// 变换所需的Matrix,完成 图片旋转，缩放等控制
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		// 获取倒影Bitmap
		Bitmap reflectionBitmap = Bitmap.createBitmap(bmp, 0, height / 2, width, height / 2,
				matrix, false);
		// 获取带倒影的Bitmap.即整体的效果图位图对象
		Bitmap withReflectionBitmap = Bitmap.createBitmap(width, height + height / 2,
				Config.ARGB_8888);
		/** Bitmap的显示还需要画布Canvas来完成 */
		// 由该位图对象创建初始画布(规定了画布的宽高)
		Canvas canvas = new Canvas(withReflectionBitmap);
		canvas.drawBitmap(bmp, 0, 0, null);
		// 绘制出原图与倒影之间的间隔，用矩形来描绘
		Paint paint1 = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, paint1);
		// 绘制出倒影的Bitmap
		canvas.drawBitmap(reflectionBitmap, 0, height + reflectionGap, paint1);
		// 绘制线性渐变对象
		Paint paint2 = new Paint();
		LinearGradient shader = new LinearGradient(0, bmp.getHeight(), 0,
				withReflectionBitmap.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff,
				TileMode.CLAMP);
		// 把渐变效果应用在画笔上
		paint2.setShader(shader);
		// 设置倒影的阴影度，使其与原来的图像颜色区别开来，此处显示灰度，会被染上下面的底部的原图片的倒影颜色，实现倒影的修饰
		paint2.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// 用设置好的paint2绘制此倒影
		canvas.drawRect(0, height, width, withReflectionBitmap.getHeight() + reflectionGap, paint2);
		return withReflectionBitmap;
	}
	/**
	 * 怀旧效果(相对之前做了优化快一倍)
	 * 
	 * @param bmp
	 *            源bitmap
	 * @return destBmp 怀旧效果的图片
	 * 
	 */
	public static Bitmap createOlderImg(Bitmap bmp) {
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		destBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		int pixColor = 0;
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int newR = 0;
		int newG = 0;
		int newB = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				pixColor = pixels[width * i + k];
				pixR = Color.red(pixColor);
				pixG = Color.green(pixColor);
				pixB = Color.blue(pixColor);
				newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
				newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
				newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
				int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG,
						newB > 255 ? 255 : newB);
				pixels[width * i + k] = newColor;
			}
		}
		destBmp.setPixels(pixels, 0, width, 0, 0, width, height);
		return destBmp;
	}
	/**
	 * 图片锐化（拉普拉斯变换）
	 * 
	 * @param bmp
	 *            源bitmap
	 * @return 锐化后图片
	 */
	public static Bitmap createSharpenImg(Bitmap bmp) {
		// 拉普拉斯矩阵
		int[] laplacian = new int[] { -1, -1, -1, -1, 9, -1, -1, -1, -1 };
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		destBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int pixColor = 0;
		int newR = 0;
		int newG = 0;
		int newB = 0;
		int idx = 0;
		float alpha = 0.3F;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				idx = 0;
				for (int m = -1; m <= 1; m++) {
					for (int n = -1; n <= 1; n++) {
						pixColor = pixels[(i + n) * width + k + m];
						pixR = Color.red(pixColor);
						pixG = Color.green(pixColor);
						pixB = Color.blue(pixColor);
						newR = newR + (int) (pixR * laplacian[idx] * alpha);
						newG = newG + (int) (pixG * laplacian[idx] * alpha);
						newB = newB + (int) (pixB * laplacian[idx] * alpha);
						idx++;
					}
				}
				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));
				pixels[i * width + k] = Color.argb(255, newR, newG, newB);
				newR = 0;
				newG = 0;
				newB = 0;
			}
		}
		destBmp.setPixels(pixels, 0, width, 0, 0, width, height);
		return destBmp;
	}
	/**
	 * 柔化效果(高斯模糊)(优化后比上面快三倍)
	 * 
	 * @param bmp
	 *            源bitmap
	 * @return destBmp 模糊后的图片
	 */
	public static Bitmap createBlurImg(Bitmap bmp) {
		// 高斯矩阵
		int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		destBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int pixColor = 0;
		int newR = 0;
		int newG = 0;
		int newB = 0;
		int delta = 16; // 值越小图片会越亮，越大则越暗
		int idx = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				idx = 0;
				for (int m = -1; m <= 1; m++) {
					for (int n = -1; n <= 1; n++) {
						pixColor = pixels[(i + m) * width + k + n];
						pixR = Color.red(pixColor);
						pixG = Color.green(pixColor);
						pixB = Color.blue(pixColor);
						newR = newR + (int) (pixR * gauss[idx]);
						newG = newG + (int) (pixG * gauss[idx]);
						newB = newB + (int) (pixB * gauss[idx]);
						idx++;
					}
				}
				newR /= delta;
				newG /= delta;
				newB /= delta;
				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));
				pixels[i * width + k] = Color.argb(255, newR, newG, newB);
				newR = 0;
				newG = 0;
				newB = 0;
			}
		}
		destBmp.setPixels(pixels, 0, width, 0, 0, width, height);
		return destBmp;
	}
	/**
	 * 底片效果
	 * 
	 * @param bmp
	 *            源bitmap
	 * @return 底片效果图片
	 */
	public static Bitmap createFilmImg(Bitmap bmp) {
		// RGBA的最大值
		final int MAX_VALUE = 255;
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		destBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int pixColor = 0;
		int newR = 0;
		int newG = 0;
		int newB = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		int pos = 0;
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				pos = i * width + k;
				pixColor = pixels[pos];
				pixR = Color.red(pixColor);
				pixG = Color.green(pixColor);
				pixB = Color.blue(pixColor);
				newR = MAX_VALUE - pixR;
				newG = MAX_VALUE - pixG;
				newB = MAX_VALUE - pixB;
				newR = Math.min(MAX_VALUE, Math.max(0, newR));
				newG = Math.min(MAX_VALUE, Math.max(0, newG));
				newB = Math.min(MAX_VALUE, Math.max(0, newB));
				pixels[pos] = Color.argb(MAX_VALUE, newR, newG, newB);
			}
		}
		destBmp.setPixels(pixels, 0, width, 0, 0, width, height);
		return destBmp;
	}
	/**
	 * Bitmap转换到Byte[]
	 * 
	 * @param bitmap
	 *            源bitmap
	 * @return byte 字节数组
	 */
	public static byte[] bitmap2Bytes(Bitmap bitmap) {
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bas);
		return bas.toByteArray();
	}
}
