package com.lany.vlayoutdemo;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 基类RecyclerView.Adapter
 */
public abstract class RecyclerAdapter<T, V extends RecyclerAdapter.BaseHolder> extends RecyclerView.Adapter<V> {
    protected String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected List<T> mItems = new ArrayList<>();
    private OnItemClickListener mItemListener;

    public RecyclerAdapter(Context ctx, List<T> items) {
        this.mContext = ctx;
        this.mItems = items;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemListener = listener;
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public T getItem(int position) {
        return mItems != null ? mItems.get(position) : null;
    }

    public void clear() {
        this.mItems.clear();
        notifyDataSetChanged();
    }

    public Context getContext() {
        return mContext;
    }

    public List<T> getItems() {
        return mItems;
    }

    public void addItems(List<T> items) {
        if (items != null && items.size() > 0) {
            this.mItems.clear();
            this.mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    final View getItemView(@LayoutRes int itemLayoutId, @Nullable ViewGroup root) {
        return LayoutInflater.from(mContext).inflate(itemLayoutId, root, false);
    }

    final Resources getResources() {
        return mContext.getResources();
    }

    final CharSequence getString(@StringRes int stringResId) {
        return mContext.getResources().getString(stringResId);
    }

    final int getColor(@ColorRes int colorResId) {
        return ContextCompat.getColor(mContext, colorResId);
    }

    final CharSequence strNoNull(CharSequence str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public abstract class BaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemListener != null) {
                mItemListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
