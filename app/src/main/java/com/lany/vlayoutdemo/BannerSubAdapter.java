package com.lany.vlayoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;
import com.lany.view.BannerAdapter;
import com.lany.view.BannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BannerSubAdapter extends DelegateAdapter.Adapter<BannerSubAdapter.BannerViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private int mCount = 0;

    List<String> items = new ArrayList<>();


    public BannerSubAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;

        items.add("http://img02.tooopen.com/images/20160427/tooopen_sy_160701449393.jpg");
        items.add("http://img02.tooopen.com/images/20151109/tooopen_sy_148088156554.jpg");
        items.add("http://img02.tooopen.com/images/20141223/sy_77772491193.jpg");
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_item, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {
        holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
    }

    @Override
    protected void onBindViewHolderWithOffset(BannerViewHolder holder, int position, int offsetTotal) {
        holder.bannerView.setAdapter(new BannerAdapter<String>(items) {

            @Override
            public void bindData(ImageView banner, TextView title, String item) {
                Glide.with(mContext)
                        .load(item)
                        .placeholder(R.mipmap.ic_launcher)
                        .crossFade()
                        .into(banner);
            }

            @Override
            public void onItemClicked(int position, String item) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner_view)
        BannerView bannerView;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}