package com.lany.vlayoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CateSubAdapter extends DelegateAdapter.Adapter<CateSubAdapter.ViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private int mCount = 0;

    public CateSubAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cate_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void onBindViewHolderWithOffset(ViewHolder holder, int position, int offsetTotal) {
        holder.title.setText("item" + position + " ");
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}