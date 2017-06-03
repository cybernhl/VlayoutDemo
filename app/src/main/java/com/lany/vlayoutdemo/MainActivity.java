package com.lany.vlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.VirtualLayoutManager.LayoutParams;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final boolean ONEN_LAYOUT = true;
    private static final boolean GRID_LAYOUT = true;
    private static final boolean STICKY_LAYOUT = true;
    private static final boolean SCROLL_FIX_LAYOUT = true;


    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_view);
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        mRecyclerView.setAdapter(delegateAdapter);

        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        //添加banner
        adapters.add(new BannerSubAdapter(this, new LinearLayoutHelper(), 1));

        //添加分类
        GridLayoutHelper cateLayoutHelper;
        cateLayoutHelper = new GridLayoutHelper(4);
        cateLayoutHelper.setMargin(0, 8, 0, 8);
//        cateLayoutHelper.setHGap(3);
//        cateLayoutHelper.setAspectRatio(4f);
        adapters.add(new CateAdapter(this, cateLayoutHelper, ModelUtil.getChannelData()));


        if (GRID_LAYOUT) {
            GridLayoutHelper layoutHelper;
            layoutHelper = new GridLayoutHelper(2);
            layoutHelper.setMargin(0, 10, 0, 10);
            layoutHelper.setHGap(3);
            layoutHelper.setAspectRatio(3f);
            adapters.add(new SubAdapter(this, layoutHelper, 2));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xff876384);
            helper.setMargin(10, 10, 10, 10);
            helper.setPadding(10, 10, 10, 10);
            adapters.add(new SubAdapter(this, helper, 3) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
//                    LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
//                    layoutParams.leftMargin = 10;
//                    layoutParams.topMargin = 10;
//                    layoutParams.rightMargin = 10;
//                    layoutParams.bottomMargin = 10;
//                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 4));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 5));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 5));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            helper.setColWeights(new float[]{40f, 45f, 15f, 60f, 0f});
            adapters.add(new SubAdapter(this, helper, 5));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            helper.setColWeights(new float[]{20f, 80f, 0f, 60f, 20f});
            helper.setAspectRatio(4);
            adapters.add(new SubAdapter(this, helper, 5));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 6));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 7));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            helper.setColWeights(new float[]{40f, 45f, 15f, 60f, 0f, 30f, 30f});
            adapters.add(new SubAdapter(this, helper, 7));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xffed7612);
//            helper.setMargin(10, 10, 10, 10);
//            helper.setPadding(10, 10, 10, 10);
            helper.setColWeights(new float[]{30f, 20f, 50f, 40f, 30f, 35f, 35f});
            adapters.add(new SubAdapter(this, helper, 7) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
//                    LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
//                    layoutParams.leftMargin = 10;
//                    layoutParams.topMargin = 10;
//                    layoutParams.rightMargin = 10;
//                    layoutParams.bottomMargin = 10;
//                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (STICKY_LAYOUT) {
            StickyLayoutHelper layoutHelper = new StickyLayoutHelper();
            layoutHelper.setAspectRatio(4);
            adapters.add(new SubAdapter(this, layoutHelper, 1, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));
        }

        if (SCROLL_FIX_LAYOUT) {
            ScrollFixLayoutHelper layoutHelper = new ScrollFixLayoutHelper(FixLayoutHelper.BOTTOM_RIGHT, 20, 20);
            layoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);
            adapters.add(new SubAdapter(this, layoutHelper, 1) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    LayoutParams layoutParams = new LayoutParams(100, 100);
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }


        adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 100));
        delegateAdapter.setAdapters(adapters);
    }
}
