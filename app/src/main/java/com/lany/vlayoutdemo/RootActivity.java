package com.lany.vlayoutdemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RootActivity extends ListActivity {

    private String[] mTitles = new String[]{
            VLayoutActivity.class.getSimpleName(),
            MainActivity.class.getSimpleName(),
            OnePlusNLayoutActivity.class.getSimpleName()
    };

    private Class[] mActivities = new Class[]{
            VLayoutActivity.class,
            MainActivity.class,
            OnePlusNLayoutActivity.class,
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mTitles));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(this, mActivities[position]));
    }

}
