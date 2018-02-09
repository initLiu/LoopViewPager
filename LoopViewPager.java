package com.lzp.test17;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by p_panzhliu on 2018/1/30.
 */

public class LoopViewPager extends ViewPager {
    private LoopPagerAdapterWrapper mAdapterWrapper;

    public LoopViewPager(Context context) {
        super(context);
        init();
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnPageChangeListener(mPageChangeListener);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        mAdapterWrapper = new LoopPagerAdapterWrapper(adapter);
        super.setAdapter(mAdapterWrapper);
        setCurrentItem(0);
    }

    @Override
    public int getCurrentItem() {
        return mAdapterWrapper == null ? 0 : mAdapterWrapper.getOriginalPosition(super.getCurrentItem());
    }

    @Override
    public void setCurrentItem(int item) {
        setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        if (mAdapterWrapper == null) {
            return;
        }
        super.setCurrentItem(mAdapterWrapper.getWrapperPosition(item), smoothScroll);
    }

    private OnPageChangeListener mPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int wrapperPosition = LoopViewPager.super.getCurrentItem();
                int originalPosition = getCurrentItem();
                if (wrapperPosition == 0 || wrapperPosition == mAdapterWrapper.getCount() - 1) {
                    setCurrentItem(originalPosition);
                }
            }
        }
    };
}
