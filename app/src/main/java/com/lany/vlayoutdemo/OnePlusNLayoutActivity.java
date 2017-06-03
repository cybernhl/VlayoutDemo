package com.lany.vlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.RecyclablePagerAdapter;
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

public class OnePlusNLayoutActivity extends AppCompatActivity {
    private static final boolean BANNER_LAYOUT = true;
    private static final boolean LINEAR_LAYOUT = true;
    private static final boolean ONEN_LAYOUT = true;
    private static final boolean GRID_LAYOUT = true;
    private static final boolean STICKY_LAYOUT = true;
    private static final boolean HORIZONTAL_SCROLL_LAYOUT = true;
    private static final boolean SCROLL_FIX_LAYOUT = true;

    private Runnable trigger;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        recyclerView = (RecyclerView) findViewById(R.id.main_view);
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        recyclerView.setAdapter(delegateAdapter);

        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        if (BANNER_LAYOUT) {
            adapters.add(new BannerSubAdapter(this, new LinearLayoutHelper(), 1) {

                @Override
                public void onViewRecycled(MainViewHolder holder) {
                    if (holder.itemView instanceof ViewPager) {
                        ((ViewPager) holder.itemView).setAdapter(null);
                    }
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    if (viewType == 1)
                        return new MainViewHolder(LayoutInflater.from(OnePlusNLayoutActivity.this).inflate(R.layout.view_pager, parent, false));
                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return 1;
                }

                @Override
                protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {

                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (holder.itemView instanceof ViewPager) {
                        ViewPager viewPager = (ViewPager) holder.itemView;
                        viewPager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
                        // from position to get adapter
                        viewPager.setAdapter(new BannerPagerAdapter(this, viewPool));
                    }
                }
            });
        }

        if (GRID_LAYOUT) {
            GridLayoutHelper layoutHelper;
            layoutHelper = new GridLayoutHelper(4);
            layoutHelper.setMargin(0, 10, 0, 10);
            layoutHelper.setHGap(3);
            layoutHelper.setAspectRatio(4f);
            adapters.add(new SubAdapter(this, layoutHelper, 8));
        }

        if (HORIZONTAL_SCROLL_LAYOUT) {

        }

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
                    LayoutParams layoutParams = new LayoutParams(50, 50);
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (LINEAR_LAYOUT)
            adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 100));

        delegateAdapter.setAdapters(adapters);

        final Handler mainHandler = new Handler(Looper.getMainLooper());

        trigger = new Runnable() {
            @Override
            public void run() {
                // recyclerView.scrollToPosition(22);
                // recyclerView.getAdapter().notifyDataSetChanged();
                recyclerView.requestLayout();
                // mainHandler.postDelayed(trigger, 1000);
            }
        };


        mainHandler.postDelayed(trigger, 1000);
    }
}
