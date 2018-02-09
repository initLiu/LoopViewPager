package com.lzp.test17;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by p_panzhliu on 2018/1/30.
 */

public class LoopPagerAdapterWrapper extends PagerAdapter {
    private PagerAdapter mAdapter;

    public LoopPagerAdapterWrapper(PagerAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getCount() {
        return mAdapter.getCount() + 2;
    }

    public int getOriginalCount() {
        return mAdapter.getCount();
    }

    public int getOriginalPosition(int wrappPosition) {
        if (getOriginalCount() == 0)
            return 0;
        int position = (wrappPosition - 1) % getOriginalCount();
        if (position < 0) {
            position += getOriginalCount();
        }
        return position;
    }

    public int getWrapperPosition(int originalPosition) {
        return originalPosition + 1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int originalPosition = getOriginalPosition(position);
        return mAdapter.instantiateItem(container, originalPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int originalPosition = getOriginalPosition(position);
        mAdapter.destroyItem(container, originalPosition, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return mAdapter.isViewFromObject(view, object);
    }

    @Override
    public void startUpdate(ViewGroup container) {
        mAdapter.startUpdate(container);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mAdapter.setPrimaryItem(container, getOriginalPosition(position), object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        mAdapter.finishUpdate(container);
    }

    @Override
    public void startUpdate(View container) {
        mAdapter.startUpdate(container);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        return mAdapter.instantiateItem(container, getOriginalPosition(position));
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        mAdapter.destroyItem(container, getOriginalPosition(position), object);
    }

    @Override
    public void setPrimaryItem(View container, int position, Object object) {
        mAdapter.setPrimaryItem(container, getOriginalPosition(position), object);
    }

    @Override
    public void finishUpdate(View container) {
        mAdapter.finishUpdate(container);
    }

    @Override
    public Parcelable saveState() {
        return mAdapter.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        mAdapter.restoreState(state, loader);
    }

    @Override
    public int getItemPosition(Object object) {
        return getWrapperPosition(mAdapter.getItemPosition(object));
    }

    @Override
    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        mAdapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mAdapter.unregisterDataSetObserver(observer);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mAdapter.getPageTitle(getOriginalPosition(position));
    }

    @Override
    public float getPageWidth(int position) {
        return mAdapter.getPageWidth(getOriginalPosition(position));
    }
}
