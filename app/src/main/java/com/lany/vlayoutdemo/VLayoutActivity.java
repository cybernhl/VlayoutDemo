package com.lany.vlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.DelegateAdapter.Adapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;

import java.util.LinkedList;
import java.util.List;

public class VLayoutActivity extends AppCompatActivity {
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
        viewPool.setMaxRecycledViews(0, 50);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        mRecyclerView.setAdapter(delegateAdapter);
        final List<Adapter> adapters = new LinkedList<>();
        //adapters.add(new BannerDelegateAdapter(this));
        adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 20));
        adapters.add(new SubAdapter(this, new OnePlusNLayoutHelper(), 3));
        adapters.add(new SubAdapter(this, new ColumnLayoutHelper(), 2));
        adapters.add(new SubAdapter(this, new ColumnLayoutHelper(), 4));
        delegateAdapter.setAdapters(adapters);

    }
}
