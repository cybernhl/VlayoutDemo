package com.lany.vlayoutdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lany.view.BannerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.banner_view)
    BannerView bannerView;

    public BannerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}