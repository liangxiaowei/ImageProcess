package com.example.imageprocess;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;

/**
 * @author 梁振伟 (liangxiaowei18@sina.com)
 * @version ImageProcess
 * @Datetime 2017-09-08 17:01
 * @Copyright (c) 2017 中国邮政电子商务运营中心. All rights reserved.
 * @since ImageProcess
 */
public class ImageHelper {

    public static Bitmap adjust(Bitmap bm, float contrast, float brightness, float saturation) {
        long begin = System.currentTimeMillis();
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        float t = (1.0F - contrast) / 2.0F * 255.0F;
        float gap = t + brightness;
        ColorMatrix contrastMatrix = new ColorMatrix();
        contrastMatrix.set(new float[]{
                contrast, 0, 0, 0, gap,
                0, contrast, 0, 0, gap,// 改变亮度
                0, 0, contrast, 0, gap,
                0, 0, 0, 1, 0});

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(contrastMatrix);
        imageMatrix.postConcat(saturationMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm, 0, 0, paint);
        long duration = System.currentTimeMillis() - begin;
        Log.d("test", "调整了" + duration + "毫秒");
        return bmp;
    }
}
