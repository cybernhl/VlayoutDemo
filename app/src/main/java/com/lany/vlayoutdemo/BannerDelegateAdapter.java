package com.lany.vlayoutdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.lany.view.BannerAdapter;


public class BannerDelegateAdapter extends DelegateAdapter.Adapter<BannerViewHolder> {
    private Context mContext;

    public BannerDelegateAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_item, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {

        holder.bannerView.setAdapter(new BannerAdapter<TravelingEntity>(ModelUtil.getTravelingData()) {


            @Override
            public void bindData(ImageView banner, TextView title, TravelingEntity item) {
                Glide.with(mContext)
                        .load(item.getImage_url())
                        .centerCrop()
                        .placeholder(R.drawable.item_background)
                        .crossFade()
                        .into(banner);
            }

            @Override
            public void onItemClicked(int position, TravelingEntity item) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
