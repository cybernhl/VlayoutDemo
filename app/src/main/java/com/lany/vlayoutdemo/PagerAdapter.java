package com.lany.vlayoutdemo;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

public class PagerAdapter extends RecyclablePagerAdapter<MainViewHolder> {

    public PagerAdapter(SubAdapter adapter, RecyclerView.RecycledViewPool pool) {
        super(adapter, pool);
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public void onBindViewHolder(MainViewHolder viewHolder, int position) {
        viewHolder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((TextView) viewHolder.itemView.findViewById(R.id.title)).setText("Banner: " + position);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}