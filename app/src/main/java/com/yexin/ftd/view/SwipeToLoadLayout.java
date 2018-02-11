package com.yexin.ftd.view;/*
package vod.clearcrane.com.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import vod.clearcrane.com.myapplication.R;

*/
/**
 * Created by yexin on 2018/1/15.
 *//*


public class SwipeToLoadLayout extends ViewGroup {

    private static final String TAG = SwipeToLoadLayout.class.getSimpleName();

    private static final int DEFAULT_SWIPING_TO_REFRESH_TO_DEFAULT_SCROLLING_DURATION = 200;

    private static final int DEFAULT_RELEASE_TO_REFRESHING_SCROLLING_DURATION = 200;

    private static final int DEFAULT_REFRESH_COMPLETE_DELAY_DURATION = 300;

    private static final int DEFAULT_REFRESH_COMPLETE_TO_DEFAULT_SCROLLING_DURATION = 500;

    private static final int DEFAULT_DEFAULT_TO_REFRESHING_SCROLLING_DURATION = 500;

    private static final int DEFAULT_SWIPING_TO_LOAD_MORE_TO_DEFAULT_SCROLLING_DURATION = 200;

    private static final int DEFAULT_RELEASE_TO_LOADING_MORE_SCROLLING_DURATION = 200;

    private static final int DEFAULT_LOAD_MORE_COMPLETE_DELAY_DURATION = 300;

    private static final int DEFAULT_LOAD_MORE_COMPLETE_TO_DEFAULT_SCROLLING_DURATION = 300;

    private static final int DEFAULT_DEFAULT_TO_LOADING_MORE_SCROLLING_DURATION = 300;

    */
/**
     * how hard to drag
     *//*

    private static final float DEFAULT_DRAG_RATIO = 0.5f;

    private static final int INVALID_POINTER = -1;

    private static final int INVALID_COORDINATE = -1;

    private AutoScroller mAutoScroller;

    private OnRefreshListener mRefreshListener;

    private OnLoadMoreListener mLoadMoreListener;

    private View mHeaderView;

    private View mTargetView;

    private View mFooterView;

    private int mHeaderHeight;

    private int mFooterHeight;

    private boolean mHasHeaderView;

    private boolean mHasFooterView;

    */
/**
     * indicate whether in debug mode
     *//*

    private boolean mDebug;

    private float mDragRatio = DEFAULT_DRAG_RATIO;

    private boolean mAutoLoading;

    */
/**
     * the threshold of the touch event
     *//*

    private final int mTouchSlop;

    */
/**
     * status of SwipeToLoadLayout
     *//*

    private int mStatus = STATUS.STATUS_DEFAULT;

    */
/**
     * target view top offset
     *//*

    private int mHeaderOffset;

    */
/**
     * target offset
     *//*

    private int mTargetOffset;

    */
/**
     * target view bottom offset
     *//*

    private int mFooterOffset;

    */
/**
     * init touch action down point.y
     *//*

    private float mInitDownY;

    */
/**
     * init touch action down point.x
     *//*

    private float mInitDownX;

    */
/**
     * last touch point.y
     *//*

    private float mLastY;

    */
/**
     * last touch point.x
     *//*

    private float mLastX;

    */
/**
     * action touch pointer's id
     *//*

    private int mActivePointerId;

    */
/**
     * <b>ATTRIBUTE:</b>
     * a switcher indicate whither refresh function is enabled
     *//*

    private boolean mRefreshEnabled = true;

    */
/**
     * <b>ATTRIBUTE:</b>
     * a switcher indicate whiter load more function is enabled
     *//*

    private boolean mLoadMoreEnabled = true;

    */
/**
     * <b>ATTRIBUTE:</b>
     * the style default classic
     *//*

    private int mStyle = STYLE.CLASSIC;

    */
/**
     * <b>ATTRIBUTE:</b>
     * offset to trigger refresh
     *//*

    private float mRefreshTriggerOffset;

    */
/**
     * <b>ATTRIBUTE:</b>
     * offset to trigger load more
     *//*

    private float mLoadMoreTriggerOffset;

    */
/**
     * <b>ATTRIBUTE:</b>
     * the max value of top offset
     *//*

    private float mRefreshFinalDragOffset;

    */
/**
     * <b>ATTRIBUTE:</b>
     * the max value of bottom offset
     *//*

    private float mLoadMoreFinalDragOffset;

    */
/**
     * <b>ATTRIBUTE:</b>
     * Scrolling duration swiping to refresh -> default
     *//*

    private int mSwipingToRefreshToDefaultScrollingDuration = DEFAULT_SWIPING_TO_REFRESH_TO_DEFAULT_SCROLLING_DURATION;

    */
/**
     * <b>ATTRIBUTE:</b>
     * Scrolling duration status release to refresh -> refreshing
     *//*

    private int mReleaseToRefreshToRefreshingScrollingDuration = DEFAULT_RELEASE_TO_REFRESHING_SCROLLING_DURATION;

    */
/**
     * <b>ATTRIBUTE:</b>
     * Refresh complete delay duration
     *//*

    private int mRefreshCompleteDelayDuration = DEFAULT_REFRESH_COMPLETE_DELAY_DURATION;

    */
/**
     * <b>ATTRIBUTE:</b>
     * Scrolling duration status refresh complete -> default
     * {@link #setRefreshing(boolean)} false
     *//*

    private int mRefreshCompleteToDefaultScrollingDuration = DEFAULT_REFRESH_COMPLETE_TO_DEFAULT_SCROLLING_DURATION;

    */
/**
     * <b>ATTRIBUTE:</b>
     * Scrolling duration status default -> refreshing, mainly for auto refresh
     * {@link #setRefreshing(boolean)} true
     *//*

    private int mDefaultToRefreshingScrollingDuration = DEFAULT_DEFAULT_TO_REFRESHING_SCROLLING_DURATION;

    */
/**
     * <b>ATTRIBUTE:</b>
     * Scrolling duration status release to loading more -> loading more
     *//*

    private int mReleaseToLoadMoreToLoadingMoreScrollingDuration = DEFAULT_RELEASE_TO_LOADING_MORE_SCROLLING_DURATION;


    */
/**
     * <b>ATTRIBUTE:</b>
     * Load more complete delay duration
     *//*

    private int mLoadMoreCompleteDelayDuration = DEFAULT_LOAD_MORE_COMPLETE_DELAY_DURATION;

    */
/**
     * <b>ATTRIBUTE:</b>
     * Scrolling duration status load more complete -> default
     * {@link #setLoadingMore(boolean)} false
     *//*

    private int mLoadMoreCompleteToDefaultScrollingDuration = DEFAULT_LOAD_MORE_COMPLETE_TO_DEFAULT_SCROLLING_DURATION;

    */
/**
     * <b>ATTRIBUTE:</b>
     * Scrolling duration swiping to load more -> default
     *//*

    private int mSwipingToLoadMoreToDefaultScrollingDuration = DEFAULT_SWIPING_TO_LOAD_MORE_TO_DEFAULT_SCROLLING_DURATION;

    */
/**
     * <b>ATTRIBUTE:</b>
     * Scrolling duration status default -> loading more, mainly for auto load more
     * {@link #setLoadingMore(boolean)} true
     *//*

    private int mDefaultToLoadingMoreScrollingDuration = DEFAULT_DEFAULT_TO_LOADING_MORE_SCROLLING_DURATION;


    */
/**
     * <b>ATTRIBUTE:</b>
     * a switcher indicate whither refresh function is enabled
     *//*

    private boolean mRefreshEnabled = true;

    public SwipeToLoadLayout(Context context) {
        this(context, null);
    }

    public SwipeToLoadLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeToLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwipeToLoadLayout, defStyleAttr, 0);
//        try {
//            final int N = a.getIndexCount();
//            for (int i = 0; i < N; i++) {
//                int attr = a.getIndex(i);
//                if (attr == R.styleable.SwipeToLoadLayout_refresh_enabled) {
//                    setRefreshEnabled(a.getBoolean(attr, true));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_load_more_enabled) {
//                    setLoadMoreEnabled(a.getBoolean(attr, true));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_swipe_style) {
//                    setSwipeStyle(a.getInt(attr, STYLE.CLASSIC));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_drag_ratio) {
//                    setDragRatio(a.getFloat(attr, DEFAULT_DRAG_RATIO));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_refresh_final_drag_offset) {
//                    setRefreshFinalDragOffset(a.getDimensionPixelOffset(attr, 0));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_load_more_final_drag_offset) {
//                    setLoadMoreFinalDragOffset(a.getDimensionPixelOffset(attr, 0));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_refresh_trigger_offset) {
//                    setRefreshTriggerOffset(a.getDimensionPixelOffset(attr, 0));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_load_more_trigger_offset) {
//                    setLoadMoreTriggerOffset(a.getDimensionPixelOffset(attr, 0));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_swiping_to_refresh_to_default_scrolling_duration) {
//                    setSwipingToRefreshToDefaultScrollingDuration(a.getInt(attr, DEFAULT_SWIPING_TO_REFRESH_TO_DEFAULT_SCROLLING_DURATION));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_release_to_refreshing_scrolling_duration) {
//                    setReleaseToRefreshingScrollingDuration(a.getInt(attr, DEFAULT_RELEASE_TO_REFRESHING_SCROLLING_DURATION));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_refresh_complete_delay_duration) {
//                    setRefreshCompleteDelayDuration(a.getInt(attr, DEFAULT_REFRESH_COMPLETE_DELAY_DURATION));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_refresh_complete_to_default_scrolling_duration) {
//                    setRefreshCompleteToDefaultScrollingDuration(a.getInt(attr, DEFAULT_REFRESH_COMPLETE_TO_DEFAULT_SCROLLING_DURATION));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_default_to_refreshing_scrolling_duration) {
//                    setDefaultToRefreshingScrollingDuration(a.getInt(attr, DEFAULT_DEFAULT_TO_REFRESHING_SCROLLING_DURATION));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_swiping_to_load_more_to_default_scrolling_duration) {
//                    setSwipingToLoadMoreToDefaultScrollingDuration(a.getInt(attr, DEFAULT_SWIPING_TO_LOAD_MORE_TO_DEFAULT_SCROLLING_DURATION));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_release_to_loading_more_scrolling_duration) {
//                    setReleaseToLoadingMoreScrollingDuration(a.getInt(attr, DEFAULT_RELEASE_TO_LOADING_MORE_SCROLLING_DURATION));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_load_more_complete_delay_duration) {
//                    setLoadMoreCompleteDelayDuration(a.getInt(attr, DEFAULT_LOAD_MORE_COMPLETE_DELAY_DURATION));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_load_more_complete_to_default_scrolling_duration) {
//                    setLoadMoreCompleteToDefaultScrollingDuration(a.getInt(attr, DEFAULT_LOAD_MORE_COMPLETE_TO_DEFAULT_SCROLLING_DURATION));
//
//                } else if (attr == R.styleable.SwipeToLoadLayout_default_to_loading_more_scrolling_duration) {
//                    setDefaultToLoadingMoreScrollingDuration(a.getInt(attr, DEFAULT_DEFAULT_TO_LOADING_MORE_SCROLLING_DURATION));
//
//                }
//            }
//        } finally {
//            a.recycle();
//        }
//
//        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
//        mAutoScroller = new AutoScroller();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    */
/**
     * switch refresh function on or off
     *
     * @param enable
     *//*

    public void setRefreshEnabled(boolean enable) {
        this.mRefreshEnabled = enable;
    }

    */
/**
     * switch load more function on or off
     *
     * @param enable
     *//*

    public void setLoadMoreEnabled(boolean enable) {
        this.mLoadMoreEnabled = enable;
    }

    */
/**
     * set the style of the refresh header
     *
     * @param style
     *//*

    public void setSwipeStyle(int style) {
        this.mStyle = style;
        requestLayout();
    }

    */
/**
     * set how hard to drag. bigger easier, smaller harder;
     *
     * @param dragRatio default value is {@link #DEFAULT_DRAG_RATIO}
     *//*

    public void setDragRatio(float dragRatio) {
        this.mDragRatio = dragRatio;
    }

    */
/**
     * set the value of {@link #mRefreshTriggerOffset}.
     * Default value is the refresh header view height {@link #mHeaderHeight}<p/>
     * If the offset you set is smaller than {@link #mHeaderHeight} or not set,
     * using {@link #mHeaderHeight} as default value
     *
     * @param offset
     *//*

    public void setRefreshTriggerOffset(int offset) {
        mRefreshTriggerOffset = offset;
    }

    */
/**
     * set the value of {@link #mLoadMoreTriggerOffset}.
     * Default value is the load more footer view height {@link #mFooterHeight}<p/>
     * If the offset you set is smaller than {@link #mFooterHeight} or not set,
     * using {@link #mFooterHeight} as default value
     *
     * @param offset
     *//*

    public void setLoadMoreTriggerOffset(int offset) {
        mLoadMoreTriggerOffset = offset;
    }

    */
/**
     * Set the final offset you can swipe to refresh.<br/>
     * If the offset you set is 0(default value) or smaller than {@link #mRefreshTriggerOffset}
     * there no final offset
     *
     * @param offset
     *//*

    public void setRefreshFinalDragOffset(int offset) {
        mRefreshFinalDragOffset = offset;
    }

    */
/**
     * Set the final offset you can swipe to load more.<br/>
     * If the offset you set is 0(default value) or smaller than {@link #mLoadMoreTriggerOffset},
     * there no final offset
     *
     * @param offset
     *//*

    public void setLoadMoreFinalDragOffset(int offset) {
        mLoadMoreFinalDragOffset = offset;
    }

    */
/**
     * set {@link #mSwipingToRefreshToDefaultScrollingDuration} in milliseconds
     *
     * @param duration
     *//*

    public void setSwipingToRefreshToDefaultScrollingDuration(int duration) {
        this.mSwipingToRefreshToDefaultScrollingDuration = duration;
    }

    */
/**
     * set {@link #mReleaseToRefreshToRefreshingScrollingDuration} in milliseconds
     *
     * @param duration
     *//*

    public void setReleaseToRefreshingScrollingDuration(int duration) {
        this.mReleaseToRefreshToRefreshingScrollingDuration = duration;
    }

    */
/**
     * set {@link #mRefreshCompleteDelayDuration} in milliseconds
     *
     * @param duration
     *//*

    public void setRefreshCompleteDelayDuration(int duration) {
        this.mRefreshCompleteDelayDuration = duration;
    }

    */
/**
     * set {@link #mRefreshCompleteToDefaultScrollingDuration} in milliseconds
     *
     * @param duration
     *//*

    public void setRefreshCompleteToDefaultScrollingDuration(int duration) {
        this.mRefreshCompleteToDefaultScrollingDuration = duration;
    }

    */
/**
     * set {@link #mDefaultToRefreshingScrollingDuration} in milliseconds
     *
     * @param duration
     *//*

    public void setDefaultToRefreshingScrollingDuration(int duration) {
        this.mDefaultToRefreshingScrollingDuration = duration;
    }

    */
/**
     * set {@link @mSwipingToLoadMoreToDefaultScrollingDuration} in milliseconds
     *
     * @param duration
     *//*

    public void setSwipingToLoadMoreToDefaultScrollingDuration(int duration) {
        this.mSwipingToLoadMoreToDefaultScrollingDuration = duration;
    }

    */
/**
     * set {@link #mReleaseToLoadMoreToLoadingMoreScrollingDuration} in milliseconds
     *
     * @param duration
     *//*

    public void setReleaseToLoadingMoreScrollingDuration(int duration) {
        this.mReleaseToLoadMoreToLoadingMoreScrollingDuration = duration;
    }

    */
/**
     * set {@link #mLoadMoreCompleteDelayDuration} in milliseconds
     *
     * @param duration
     *//*

    public void setLoadMoreCompleteDelayDuration(int duration) {
        this.mLoadMoreCompleteDelayDuration = duration;
    }

    */
/**
     * set {@link #mLoadMoreCompleteToDefaultScrollingDuration} in milliseconds
     *
     * @param duration
     *//*

    public void setLoadMoreCompleteToDefaultScrollingDuration(int duration) {
        this.mLoadMoreCompleteToDefaultScrollingDuration = duration;
    }

    */
/**
     * set {@link #mDefaultToLoadingMoreScrollingDuration} in milliseconds
     *
     * @param duration
     *//*

    public void setDefaultToLoadingMoreScrollingDuration(int duration) {
        this.mDefaultToLoadingMoreScrollingDuration = duration;
    }

}
*/
