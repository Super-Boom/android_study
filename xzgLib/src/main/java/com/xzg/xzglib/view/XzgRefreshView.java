package com.xzg.xzglib.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xzg.xzglib.R;

import java.util.ArrayList;

public class XzgRefreshView extends FrameLayout {

    protected RecyclerView mRecyclerView;
    protected ViewGroup mProgressView;
    protected ViewGroup mEmptyView;
    protected ViewGroup mErrorView;
    private int mProgressId;
    private int mEmptyId;
    private int mErrorId;
    protected boolean mClipToPadding;
    protected int mPadding;
    protected int mPaddingTop;
    protected int mPaddingBottom;
    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mScrollbarStyle;
    protected int mScrollbar;

    protected RecyclerView.OnScrollListener mInternalOnScrollListener;
    protected RecyclerView.OnScrollListener mExternalOnScrollListener;
    protected ArrayList<RecyclerView.OnScrollListener> mExternalOnScrollListenerList = new ArrayList<>();
    protected SwipeRefreshLayout mPtrLayout;
    protected SwipeRefreshLayout.OnRefreshListener mRefreshListener;

    public XzgRefreshView(Context context) {
        super(context);
    }


    /**
     * 初始化资源
     *
     * @param attrs attrs
     */
    protected void initAttrs(AttributeSet attrs) {
        // 加载attr属性
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.XzgRefreshView);
        try {
            mClipToPadding = a.getBoolean(R.styleable.XzgRefreshView_recyclerClipToPadding, false);
            mPadding = (int) a.getDimension(R.styleable.XzgRefreshView_recyclerPadding, -1.0f);
            mPaddingTop = (int) a.getDimension(R.styleable.XzgRefreshView_recyclerPaddingTop, 0.0f);
            mPaddingBottom = (int) a.getDimension(R.styleable.XzgRefreshView_recyclerPaddingBottom, 0.0f);
            mPaddingLeft = (int) a.getDimension(R.styleable.XzgRefreshView_recyclerPaddingLeft, 0.0f);
            mPaddingRight = (int) a.getDimension(R.styleable.XzgRefreshView_recyclerPaddingRight, 0.0f);
            mScrollbarStyle = a.getInteger(R.styleable.XzgRefreshView_scrollbarStyle, -1);
            mScrollbar = a.getInteger(R.styleable.XzgRefreshView_scrollbars, -1);
            mEmptyId = a.getResourceId(R.styleable.XzgRefreshView_layout_empty, 0);
            mProgressId = a.getResourceId(R.styleable.XzgRefreshView_layout_progress, 0);
            mErrorId = a.getResourceId(R.styleable.XzgRefreshView_layout_error, 0);
        } finally {
            a.recycle();
        }
    }


    /**
     * 初始化
     */
    private void initView() {
        // 使用isInEditMode解决可视化编辑器无法识别自定义控件的问题
        if (isInEditMode()) {
            return;
        }
        // 生成主View
        View v = LayoutInflater.from(getContext()).inflate(R.layout.refresh_recyclerview, this);
        mPtrLayout = v.findViewById(R.id.ptr_layout);
        mPtrLayout.setEnabled(false);

        // 加载进度 view
        mProgressView = v.findViewById(R.id.progress);
        if (mProgressId != 0) {
            LayoutInflater.from(getContext()).inflate(mProgressId, mProgressView);
        }
        // 数据为空时的view
        mEmptyView = v.findViewById(R.id.empty);
        if (mEmptyId != 0) {
            LayoutInflater.from(getContext()).inflate(mEmptyId, mEmptyView);
        }
        // 数据加载错误view
        mErrorView = v.findViewById(R.id.error);
        if (mErrorId != 0) {
            LayoutInflater.from(getContext()).inflate(mEmptyId, mErrorView);
        }
        // 初始化
        initRecyclerView(v);
    }

    protected void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(android.R.id.list);
        setItemAnimator(null);
        if (mRecyclerView != null) {
            // 设置为true时，当item尺寸发生变化，避免RecyclerView重绘
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setClipToPadding(mClipToPadding);
            // 设置recyclerView的padding值
            if (mPadding != -1) {
                mRecyclerView.setPadding(mPadding, mPadding, mPadding, mPadding);
            } else {
                mRecyclerView.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
            }
            if (mScrollbarStyle != -1) {
                mRecyclerView.setScrollBarStyle(mScrollbarStyle);
            }
            // 区分滑动方向
            switch (mScrollbar) {
                case 0:
                    setVerticalScrollBarEnabled(false);
                    break;
                case 1:
                    setHorizontalScrollBarEnabled(false);
                    break;
                case 2:
                    setVerticalScrollBarEnabled(false);
                    setHorizontalScrollBarEnabled(false);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void setVerticalScrollBarEnabled(boolean verticalScrollBarEnabled) {
        mRecyclerView.setVerticalScrollBarEnabled(verticalScrollBarEnabled);
    }

    @Override
    public void setHorizontalScrollBarEnabled(boolean horizontalScrollBarEnabled) {
        mRecyclerView.setHorizontalScrollBarEnabled(horizontalScrollBarEnabled);
    }

    /**
     * 设置条目动画效果
     *
     * @param animator ItemAnimator
     */
    public void setItemAnimator(RecyclerView.ItemAnimator animator) {
        mRecyclerView.setItemAnimator(animator);
    }

}
