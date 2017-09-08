package com.example.imageprocess;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import static android.R.attr.max;

/**
 * @author 梁振伟 (liangxiaowei18@sina.com)
 * @version ImageProcess
 * @Datetime 2017-09-08 16:45
 * @Copyright (c) 2017 中国邮政电子商务运营中心. All rights reserved.
 * @since ImageProcess
 */
public class AdjustActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private ImageView mImageView;
    private SeekBar mBrightSeekbar, mSaturationSeekbar, mContrastSeekbar;

    private float mStauration, mBright, mContrast;
    private Bitmap bitmap;
    private Bitmap resultBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust);
        mContrast = 1.0f;
        mStauration = 1.0f;
        bitmap = BitmapUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.test_big_photo, 500, 500);
        Log.d("test", "bitmap height" + bitmap.getHeight());
        Log.d("test", "bitmap width" + bitmap.getWidth());
        mImageView = (ImageView) findViewById(R.id.image_view);
        mImageView.setImageBitmap(bitmap);

        mBrightSeekbar = initSeekBar(R.id.seek_bar_bright, 510, 255);
        mSaturationSeekbar = initSeekBar(R.id.seek_bar_saturation, 200, 100);
        mContrastSeekbar = initSeekBar(R.id.seek_bar_contrast, 255, 127);

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapUtils.saveBitmap(resultBitmap, "test");
                //保存在myImageProcess文件夹里
                Toast.makeText(AdjustActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private SeekBar initSeekBar(int id, int max, int progress) {
        SeekBar seekBar = (SeekBar) findViewById(id);
        seekBar.setMax(max);
        seekBar.setProgress(progress);
        seekBar.setOnSeekBarChangeListener(this);

        return seekBar;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seek_bar_contrast:
                mContrast = progress / 125F;
                break;
            case R.id.seek_bar_saturation:
                mStauration = progress / 100F;
                break;
            case R.id.seek_bar_bright:
                mBright = progress - 255;
                break;
        }
        resultBitmap = ImageHelper.adjust(bitmap, mContrast, mBright, mStauration);
        mImageView.setImageBitmap(resultBitmap);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
