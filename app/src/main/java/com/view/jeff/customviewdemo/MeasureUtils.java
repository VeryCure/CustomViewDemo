package com.view.jeff.customviewdemo;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by max216 on 2016/12/29.
 */

public class MeasureUtils {
    public static int[] getScreenSize(Activity context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        return new int[]{widthPixels, heightPixels};
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
