package com.view.jeff.customviewdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.view.jeff.customviewdemo.R;


/**
 * Created by max216 on 2017/2/9.
 */

public class XfermodeView extends View {


    private Paint mCleanPaint;
    private Path mPath;
    private Bitmap mBgBitmap;
    private Bitmap mFgBitmap;
    private Canvas mCanvas;

    public XfermodeView(Context context) {
        this(context,null);
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mCleanPaint = new Paint();
        mCleanPaint.setAntiAlias(true);
        mCleanPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mCleanPaint.setStyle(Paint.Style.STROKE);
        mCleanPaint.setStrokeJoin(Paint.Join.ROUND);
        mCleanPaint.setStrokeWidth(50);
        mCleanPaint.setStrokeCap(Paint.Cap.ROUND);
        mCleanPaint.setAlpha(0);

        mPath = new Path();
        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        mFgBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(), mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);
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
                height = Math.min(size,mBgBitmap.getHeight());
                break;
            case MeasureSpec.UNSPECIFIED:
                height = mBgBitmap.getHeight();
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
            width = mBgBitmap.getWidth();
            if (mode == MeasureSpec.AT_MOST){
                width = Math.min(size,mBgBitmap.getWidth());
            }
        }
        return width;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
        }
        mCanvas.drawPath(mPath,mCleanPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBgBitmap,0,0,null);
        canvas.drawBitmap(mFgBitmap,0,0,null);
    }
}
