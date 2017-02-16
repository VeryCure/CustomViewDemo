package com.view.jeff.customviewdemo.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;


import com.view.jeff.customviewdemo.BaseActivity;
import com.view.jeff.customviewdemo.ImageHelper;
import com.view.jeff.customviewdemo.R;

import butterknife.BindView;

/**
 * Created by max216 on 2017/2/9.
 */

public class BitmapColorActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener{
    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.seekBar1)
    SeekBar mSeekBar1;
    @BindView(R.id.seekBar2)
    SeekBar mSeekBar2;
    @BindView(R.id.seekBar3)
    SeekBar mSeekBar3;
    @BindView(R.id.reset)
    Button mReset;
    private Bitmap mBitmap;
    private static int MID_VALUE = 50;
    private float mHue = 0;
    private float mSatuartion = 1;
    private float mLum = 1;

    @Override
    protected void init() {
        Drawable drawable = mImage.getDrawable();
        mBitmap = ImageHelper.drawableToBitmap(drawable);
        mSeekBar1.setOnSeekBarChangeListener(this);
        mSeekBar2.setOnSeekBarChangeListener(this);
        mSeekBar3.setOnSeekBarChangeListener(this);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekBar1.setProgress(50);
                mSeekBar2.setProgress(50);
                mSeekBar3.setProgress(50);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bitmap_color;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.e("currentprogress","currentprogress :"+progress);
        switch (seekBar.getId()){
            case R.id.seekBar1:
                mHue = (progress-MID_VALUE) * 1.0F / MID_VALUE*180;
                break;
            case R.id.seekBar2:
                mSatuartion = progress * 1.0F / MID_VALUE;
                break;
            case R.id.seekBar3:
                mLum = progress * 1.0f /MID_VALUE;
                break;
        }
        mImage.setImageBitmap(ImageHelper.handleImageEffect(mBitmap,mHue,mSatuartion,mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
