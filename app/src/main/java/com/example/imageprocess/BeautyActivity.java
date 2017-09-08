package com.example.imageprocess;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


/**
 * @author 梁振伟 (liangxiaowei18@sina.com)
 * @version ImageProcess
 * @Datetime 2017-09-08 16:34
 * @Copyright (c) 2017 中国邮政电子商务运营中心. All rights reserved.
 * @since ImageProcess
 */
public class BeautyActivity extends Activity {
    private Bitmap bitmap;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);
        bitmap = BitmapUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.test_beauty_skin1, 500, 500);
        Log.d("test", "bitmap height" + bitmap.getHeight());
        Log.d("test", "bitmap width" + bitmap.getWidth());
        imageView = (ImageView) findViewById(R.id.img);
        imageView.setImageBitmap(bitmap);
        findViewById(R.id.btn_beauty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //之后放在子线程里做
                long begin = System.currentTimeMillis();
                imageView.setImageBitmap(new BeautifyMultiThread().beautifyImg(bitmap, 10));
                long duration = System.currentTimeMillis() - begin;
                Log.d("test", "磨皮" + duration + "毫秒");
            }
        });
    }
}
