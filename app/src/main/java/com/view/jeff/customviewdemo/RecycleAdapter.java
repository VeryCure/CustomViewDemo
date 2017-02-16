package com.view.jeff.customviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by max216 on 2017/1/12.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private MyViewHolder myViewHolder;

    public RecycleAdapter(Context context, List<String> mdata) {
        this.mContext = context;
        this.mDatas = mdata;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder = new MyViewHolder(new TextViewCard(mContext));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.bindData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public View getRecyclerItemView(){
        return myViewHolder.itemView;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewCard textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextViewCard) itemView;
        }
    }
    public void refreshData(List<String> mlist){
        if (mlist != null && mlist.size()>0){
            mDatas.addAll(mlist);
            notifyDataSetChanged();
        }
    }
}
