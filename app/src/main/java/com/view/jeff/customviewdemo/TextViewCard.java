package com.view.jeff.customviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
/**
 * Created by max216 on 2017/1/12.
 */

public class TextViewCard extends FrameLayout{

    private TextView mTextview;
    private View mContentView;

    public TextViewCard(Context context) {
        this(context,null);
    }

    public TextViewCard(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextViewCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContentView = inflate(context, R.layout.card_text_view, this);

    }

    public void bindData(String s){
        TextView mTextView = (TextView) mContentView.findViewById(R.id.text);
        mTextView.setText(s);
    }
}
