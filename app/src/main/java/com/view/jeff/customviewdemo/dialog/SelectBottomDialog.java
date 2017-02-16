package com.view.jeff.customviewdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.view.jeff.customviewdemo.R;
import com.view.jeff.customviewdemo.RecycleAdapter;
import com.view.jeff.customviewdemo.TextViewCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by max216 on 2017/1/12.
 */

public class SelectBottomDialog extends Dialog{

    private RecyclerView mRecyclerView;
    private Context mContext;
    private List<String> mDatas = new ArrayList<>();
    private RecycleAdapter mRecycleAdapter;

    public SelectBottomDialog(Context context) {
        super(context, R.style.Dialog_style);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_dialog);
        initData();
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);
        window.setWindowAnimations(R.style.select_resume_style);
        setCanceledOnTouchOutside(true);
        findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleAdapter = new RecycleAdapter(mContext,mDatas);
        mRecyclerView.setAdapter(mRecycleAdapter);
        setListViewHeightBasedOnChildren(mRecyclerView);
    }
    /**
     * 动态设置listview的高度
     *
     * @param listView
     */
    public void setListViewHeightBasedOnChildren(RecyclerView listView) {

        RecycleAdapter adapter = (RecycleAdapter) listView.getAdapter();

        if (adapter == null) {
            return;
        }

        int totalHeight = 0;

        if (mDatas.size() > 3) {

            for (int i = 0; i <= 3; i++) {
                View listItem = new TextViewCard(mContext);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }else {
            for (int i = 0; i <= mDatas.size(); i++) {
                View listItem = new TextViewCard(mContext);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight;
        listView.setLayoutParams(params);
    }

    private void initData() {
        for (int i=0 ;i <10;i++){
            mDatas.add("我是第"+i+"条目");
        }
    }

}
