package com.view.jeff.customviewdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.view.jeff.customviewdemo.R;


/**
 * Created by max216 on 2017/2/10.
 */

public class ReflectView extends View {

    private Bitmap mSrcBitmap;
    private Bitmap mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;

    public ReflectView(Context context) {
        this(context,null);
    }

    public ReflectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        Matrix matrix = new Matrix();
        matrix.setScale(1,-1);
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);

        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(0,mSrcBitmap.getHeight(),0,mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4,0xdd000000,0x10000000, Shader.TileMode.CLAMP));
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap,0,0,null);
        canvas.drawBitmap(mRefBitmap,0,mRefBitmap.getHeight(),null);
        mPaint.setXfermode(mXfermode);

        canvas.drawRect(0,mSrcBitmap.getHeight(),mRefBitmap.getWidth(),mSrcBitmap.getHeight()*2,mPaint);
        mPaint.setXfermode(null);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int height = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        switch (mode){
            case MeasureSpec.EXACTLY:
                height = size;
                break;
            case MeasureSpec.AT_MOST:
                height = Math.min(size,mSrcBitmap.getHeight()*2);
                break;
            case MeasureSpec.UNSPECIFIED:
                height = mSrcBitmap.getHeight()*2;
                break;
        }
        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int width = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY){
            width = size;
        }else {
            width = mSrcBitmap.getWidth();
            if (mode == MeasureSpec.AT_MOST){
                width = Math.min(size,mSrcBitmap.getWidth());
            }
        }
        return width;
    }

}
