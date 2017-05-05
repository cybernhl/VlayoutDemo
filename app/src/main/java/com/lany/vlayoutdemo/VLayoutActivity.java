package com.lany.vlayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.DelegateAdapter.Adapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.VirtualLayoutManager.LayoutParams;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;

import java.util.LinkedList;
import java.util.List;

public class VLayoutActivity extends AppCompatActivity {
    private static final boolean BANNER_LAYOUT = true;
    private static final boolean FIX_LAYOUT = true;
    private static final boolean LINEAR_LAYOUT = true;
    private static final boolean SINGLE_LAYOUT = true;
    private static final boolean FLOAT_LAYOUT = true;
    private static final boolean ONEN_LAYOUT = true;
    private static final boolean COLUMN_LAYOUT = true;
    private static final boolean GRID_LAYOUT = true;
    private static final boolean STICKY_LAYOUT = true;
    private static final boolean STAGGER_LAYOUT = true;

    private Runnable trigger;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_view);
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // layoutManager.setReverseLayout(true);
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        mRecyclerView.setAdapter(delegateAdapter);
        final List<Adapter> adapters = new LinkedList<>();

        if (BANNER_LAYOUT) {
            adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 1) {

                @Override
                public void onViewRecycled(MainViewHolder holder) {
                    if (holder.itemView instanceof ViewPager) {
                        ((ViewPager) holder.itemView).setAdapter(null);
                    }
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    if (viewType == 1)
                        return new MainViewHolder(
                                LayoutInflater.from(VLayoutActivity.this).inflate(R.layout.view_pager, parent, false));
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
                        viewPager.setAdapter(new PagerAdapter(this, viewPool));
                    }
                }
            });
        }

        if (FLOAT_LAYOUT) {
            FloatLayoutHelper layoutHelper = new FloatLayoutHelper();
            layoutHelper.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);
            layoutHelper.setDefaultLocation(100, 400);
            LayoutParams layoutParams = new LayoutParams(150, 150);
            adapters.add(new SubAdapter(this, layoutHelper, 1, layoutParams));
        }

        if (LINEAR_LAYOUT) {
            LinearLayoutHelper layoutHelper1 = new LinearLayoutHelper();
            layoutHelper1.setAspectRatio(2.0f);
            LinearLayoutHelper layoutHelper2 = new LinearLayoutHelper();
            layoutHelper2.setAspectRatio(4.0f);
            layoutHelper2.setDividerHeight(10);
            layoutHelper2.setMargin(10, 30, 10, 10);
            layoutHelper2.setPadding(10, 30, 10, 10);
            layoutHelper2.setBgColor(0xFFff0000);
            adapters.add(new SubAdapter(this, layoutHelper1, 1));
            adapters.add(new SubAdapter(this, layoutHelper2, 6) {

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (position % 2 == 0) {
                        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
                        layoutParams.mAspectRatio = 5;
                        holder.itemView.setLayoutParams(layoutParams);
                    }
                }
            });
        }

        if (STICKY_LAYOUT) {
            StickyLayoutHelper layoutHelper = new StickyLayoutHelper();
            layoutHelper.setOffset(100);
            layoutHelper.setAspectRatio(4);
            adapters.add(new SubAdapter(this, layoutHelper, 1, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));
        }

        if (SINGLE_LAYOUT) {
            SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
            layoutHelper.setBgColor(Color.rgb(135, 225, 90));
            layoutHelper.setAspectRatio(4);
            layoutHelper.setMargin(10, 20, 10, 20);
            layoutHelper.setPadding(10, 10, 10, 10);
            adapters.add(new SubAdapter(this, layoutHelper, 1, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));
        }

        if (COLUMN_LAYOUT) {
            ColumnLayoutHelper layoutHelper = new ColumnLayoutHelper();
            layoutHelper.setBgColor(0xff00f0f0);
            layoutHelper.setWeights(new float[]{40.0f, Float.NaN, 40});
            adapters.add(new SubAdapter(this, layoutHelper, 5) {

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (position == 0) {
                        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
                        layoutParams.mAspectRatio = 4;
                        holder.itemView.setLayoutParams(layoutParams);
                    } else {
                        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
                        layoutParams.mAspectRatio = Float.NaN;
                        holder.itemView.setLayoutParams(layoutParams);
                    }
                }

            });
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xff876384);
            helper.setAspectRatio(4.0f);
            helper.setColWeights(new float[]{40f, 45f});
            helper.setMargin(10, 20, 10, 20);
            helper.setPadding(10, 10, 10, 10);
            adapters.add(new SubAdapter(this, helper, 2));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xffef8ba3);
            helper.setAspectRatio(2.0f);
            helper.setColWeights(new float[]{40f});
            helper.setRowWeight(30f);
            helper.setMargin(10, 20, 10, 20);
            helper.setPadding(10, 10, 10, 10);
            adapters.add(new SubAdapter(this, helper, 4) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    LayoutParams lp = (LayoutParams) holder.itemView.getLayoutParams();
                    if (position == 0) {
                        lp.rightMargin = 1;
                    } else if (position == 1) {

                    } else if (position == 2) {
                        lp.topMargin = 1;
                        lp.rightMargin = 1;
                    }
                }
            });
        }

        if (ONEN_LAYOUT) {
            adapters.add(new SubAdapter(this, new OnePlusNLayoutHelper(), 0));
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xff87e543);
            helper.setAspectRatio(1.8f);
            helper.setColWeights(new float[]{33.33f, 50f, 40f});
            helper.setMargin(10, 20, 10, 20);
            helper.setPadding(10, 10, 10, 10);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            adapters.add(new SubAdapter(this, helper, 3, lp) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    LayoutParams lp = (LayoutParams) holder.itemView.getLayoutParams();
                    if (position == 0) {
                        lp.rightMargin = 1;
                    }
                }
            });
        }

        if (COLUMN_LAYOUT) {
            adapters.add(new SubAdapter(this, new ColumnLayoutHelper(), 0));
            adapters.add(new SubAdapter(this, new ColumnLayoutHelper(), 4));
        }

        if (FIX_LAYOUT) {
            FixLayoutHelper layoutHelper = new FixLayoutHelper(10, 10);
            adapters.add(new SubAdapter(this, layoutHelper, 0));

            layoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_RIGHT, 20, 20);

            adapters.add(new SubAdapter(this, layoutHelper, 1) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    LayoutParams layoutParams = new LayoutParams(200, 200);
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (STICKY_LAYOUT) {
            StickyLayoutHelper layoutHelper = new StickyLayoutHelper(false);
            adapters.add(new SubAdapter(this, layoutHelper, 0));
            layoutHelper = new StickyLayoutHelper(false);
            layoutHelper.setOffset(100);
            adapters.add(new SubAdapter(this, layoutHelper, 1, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));
        }

        if (GRID_LAYOUT) {
            GridLayoutHelper layoutHelper = new GridLayoutHelper(2);
            layoutHelper.setMargin(7, 0, 7, 0);
            layoutHelper.setWeights(new float[]{46.665f});
            layoutHelper.setHGap(3);
            adapters.add(new SubAdapter(this, layoutHelper, 2));

            layoutHelper = new GridLayoutHelper(4);
            layoutHelper.setWeights(new float[]{20f, 26.665f});
            layoutHelper.setMargin(7, 0, 7, 0);
            layoutHelper.setHGap(3);
            adapters.add(new SubAdapter(this, layoutHelper, 8));
        }


        if (GRID_LAYOUT) {
            adapters.add(new SubAdapter(this, new GridLayoutHelper(4), 0));

            GridLayoutHelper helper = new GridLayoutHelper(4);
            helper.setAspectRatio(4f);
            //helper.setColWeights(new float[]{40, 20, 30, 30});
            // helper.setMargin(0, 10, 0, 10);
            helper.setGap(10);
            adapters.add(new SubAdapter(this, helper, 80) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    LayoutParams lp = (LayoutParams) holder.itemView.getLayoutParams();
                    // lp.bottomMargin = 1;
                    // lp.rightMargin = 1;
                }
            });
        }

        if (FIX_LAYOUT) {
            adapters.add(new SubAdapter(this, new ScrollFixLayoutHelper(20, 20), 1) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    LayoutParams layoutParams = new LayoutParams(200, 200);
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (LINEAR_LAYOUT)
            adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 10));

        if (GRID_LAYOUT) {
            GridLayoutHelper helper = new GridLayoutHelper(3);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 3));
        }

        if (STAGGER_LAYOUT) {
            // adapters.add(new SubAdapter(this, new StaggeredGridLayoutHelper(2, 0), 0));
            final StaggeredGridLayoutHelper helper = new StaggeredGridLayoutHelper(2, 10);
            helper.setMargin(20, 10, 10, 10);
            helper.setPadding(10, 10, 20, 10);
            helper.setBgColor(0xFF86345A);
            adapters.add(new SubAdapter(this, helper, 27) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                    if (position % 2 == 0) {
                        layoutParams.mAspectRatio = 1.0f;
                    } else {
                        layoutParams.height = 340 + position % 7 * 20;
                    }
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (COLUMN_LAYOUT) {
            // adapters.add(new SubAdapter(this, new ColumnLayoutHelper(), 3));
        }

        if (GRID_LAYOUT) {
            // adapters.add(new SubAdapter(this, new GridLayoutHelper(4), 24));
        }

        delegateAdapter.setAdapters(adapters);


        final Handler mainHandler = new Handler(Looper.getMainLooper());

        trigger = new Runnable() {
            @Override
            public void run() {
                // recyclerView.scrollToPosition(22);
                // recyclerView.getAdapter().notifyDataSetChanged();
                // mainHandler.postDelayed(trigger, 1000);
                //List<DelegateAdapter.Adapter> newAdapters = new ArrayList<>();
                //newAdapters.add((new SubAdapter(VLayoutActivity.this, new ColumnLayoutHelper(), 3)));
                //newAdapters.add((new SubAdapter(VLayoutActivity.this, new GridLayoutHelper(4), 24)));
                //delegateAdapter.addAdapters(newAdapters);
                mRecyclerView.requestLayout();
            }
        };


        mainHandler.postDelayed(trigger, 1000);
    }
}
