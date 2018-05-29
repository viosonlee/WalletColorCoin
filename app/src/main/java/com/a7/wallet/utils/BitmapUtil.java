package com.a7.wallet.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by viosonlee
 * on 2018/5/28.
 * for
 */
public class BitmapUtil {
    public static Bitmap strCreateBitmap(String str) {
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawARGB(255, 160, 143, 242);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        canvas.drawText(str, 100, 100, paint);
        return bitmap;
    }
}
