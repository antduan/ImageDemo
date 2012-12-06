package com.tomyzhou.imagedemo.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

/**
 * ����һЩ����ͼƬ�ķ���
 */
public class ImageUtil {

	// ��Drawableת��ΪBitmap
	public static Bitmap drawableToBitmap(Drawable drawable) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
				.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;

	}

	// �Ŵ���СͼƬ
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = (float) w / width;
		float scaleHieght = (float) h / height;
		matrix.postScale(scaleWidth, scaleHieght);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);

		return newbmp;
	}

	// ��ô�Բ�ǵ�ͼƬ
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Bitmap newbmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(newbmp);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, width, height);
		final RectF rectf = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectf, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return newbmp;
	}

	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		// ԭͼ�͵�Ӱ�ļ��
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
				width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width, height
				+ height / 2, Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);

		Paint defaultPaint = new Paint();
		// �Ȼ�����������������
		canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);
		// �ٰ�������ͼƬ����ȥ
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		// ���Խ��� ���ֲ�
		LinearGradient shader = new LinearGradient(0, height, 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0xafffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// ��Ƭ���ͣ���ĳ�ֺϳ�Ч��
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		return bitmapWithReflection;
	}
}
