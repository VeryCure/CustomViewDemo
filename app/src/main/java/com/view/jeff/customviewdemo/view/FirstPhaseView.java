package com.view.jeff.customviewdemo.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.view.jeff.customviewdemo.MeasureUtils;


/**
 * Created by max216 on 2016/12/28.
 */

public class FirstPhaseView extends View implements Runnable {

    private Paint mPaint;
    private Context mContext;
    private int mRaduis = 100;

    public FirstPhaseView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public FirstPhaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        /**
         * 社会i画笔的样式，是描边Paint.Style.STROKE还是填充Paint.Style.FILL，或者描边并填充Paint.Style.FILL_AND_STROKE
         */
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(MeasureUtils.getScreenSize((Activity) mContext)[0] / 2, MeasureUtils.getScreenSize((Activity) mContext)[1] / 2, mRaduis, mPaint);
    }

    public synchronized void setRaduis(int raduis) {
        this.mRaduis = raduis;
        //重绘
        invalidate();
    }

    @Override
    public void run() {
        int maxRaduis = 0;
        while (true) {
            try {
                if (maxRaduis == 300) {
                    mRaduis -= 10;
//                    invalidate();
                    maxRaduis = mRaduis == 0? 0 : 300;
                } else if (maxRaduis ==0){
                    mRaduis += 10;
                    maxRaduis = mRaduis == 300? 300 : 0;
                }
                postInvalidate();
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
