package com.view.jeff.customviewdemo.ui;

import android.os.Handler;
import android.os.Message;

import com.view.jeff.customviewdemo.BaseActivity;
import com.view.jeff.customviewdemo.R;
import com.view.jeff.customviewdemo.view.FirstPhaseView;

import butterknife.BindView;

/**
 * Created by max216 on 2016/12/29.
 */

public class FirstPhaseActivity extends BaseActivity {

    @BindView(R.id.circle)
    FirstPhaseView mCircle;
    private int raduis = 100;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mCircle.setRaduis(raduis);
        }
    };
    @Override
    protected void init() {
        new Thread(mCircle).start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_first_ohase;
    }
}
