package com.view.jeff.customviewdemo;

import android.content.Intent;
import android.view.View;

import com.view.jeff.customviewdemo.dialog.SelectBottomDialog;
import com.view.jeff.customviewdemo.ui.BitmapColorActivity;
import com.view.jeff.customviewdemo.ui.FirstPhaseActivity;
import com.view.jeff.customviewdemo.ui.XfermodeActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.first_phase,R.id.second_phase,R.id.third_phase,R.id.forth_phase,R.id.fifth_phase})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.first_phase:
                Intent first = new Intent(this, FirstPhaseActivity.class);
                startActivity(first);
                break;
            case R.id.second_phase:
                new SelectBottomDialog(this).show();
                break;
            case R.id.third_phase:
                Intent bitmap = new Intent(this, BitmapColorActivity.class);
                startActivity(bitmap);
                break;
            case R.id.forth_phase:
                Intent xfermode = new Intent(this, XfermodeActivity.class);
                startActivity(xfermode);
                break;
            case R.id.fifth_phase:
                break;
        }
    }
}
