package com.xzg.xzglib.apapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.xzg.xzglib.holder.BaseViewHolder;
import com.xzg.xzglib.inter.InterItemView;
import com.xzg.xzglib.inter.OnItemChildClickListener;
import com.xzg.xzglib.utils.RecyclerUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class RecyclerArrayAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private OnItemChildClickListener onItemChildClickListener;
    private ArrayList<InterItemView> headers = new ArrayList<>();
    private ArrayList<InterItemView> footers = new ArrayList<>();
    private Context mContext;
    private List<T> mObjects;




    public RecyclerArrayAdapter(Context context) {
        RecyclerUtils.checkContent(context);
        init(context, new ArrayList<T>());
    }

    public RecyclerArrayAdapter(Context context, T[] objects) {
        RecyclerUtils.checkContent(context);
        init(context, Arrays.asList(objects));
    }

    public RecyclerArrayAdapter(Context context, List<T> objects) {
        RecyclerUtils.checkContent(context);
        init(context, objects);
    }

    private void init(Context context, List<T> objects) {
        mContext = context;
        mObjects = new ArrayList<>(objects);
    }


    /**
     * 创建viewHolder,主要作用是创建item视图，并返回相应的ViewHolder
     *
     * @param parent   parent
     * @param viewType type类型
     * @return 返回viewHolder
     */
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = createViewByType(parent, viewType);
        if (view != null) {
            return new BaseViewHolder(view);
        }
        final BaseViewHolder viewHolder = onCreateViewHolder(parent, viewType);

        return null;
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    public abstract BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * 获取header的数量
     *
     * @return 数量
     */
    public int getHeaderCount() {
        return headers.size();
    }


    /**
     * 获取footer的数量
     *
     * @return 数量
     */
    public int getFooterCount() {
        return footers.size();
    }

    /**
     * 添加headerView
     *
     * @param view view
     */
    public void addHeader(InterItemView view) {
        if (view == null) {
            throw new NullPointerException("InterItemView can't be null");
        }
        headers.add(view);
        notifyItemInserted(headers.size() - 1);
    }

    private View createViewByType(ViewGroup parent, int viewType) {
        for (InterItemView headerView : headers) {
            if (headerView.hashCode() == viewType) {
                View view = headerView.onCreateView(parent);
                StaggeredGridLayoutManager.LayoutParams layoutParams;
                if (view.getLayoutParams() != null) {
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(view.getLayoutParams());
                } else {
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                layoutParams.setFullSpan(true);
                view.setLayoutParams(layoutParams);
                return view;
            }
        }
        for (InterItemView footerView : footers) {
            if (footerView.hashCode() == viewType) {
                View view = footerView.onCreateView(parent);
                StaggeredGridLayoutManager.LayoutParams layoutParams;
                if (view.getLayoutParams() != null) {
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(view.getLayoutParams());
                } else {
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                layoutParams.setFullSpan(true);
                view.setLayoutParams(layoutParams);
                return view;
            }
        }
        return null;
    }


    public OnItemChildClickListener getOnItemChildClickListener() {
        return onItemChildClickListener;
    }

    /**
     * 添加所有数据
     * @param collection Collection集合数据
     */
    public void addAll(Collection<? extends T> collection) {

    }
}
