package com.xzg.xzglib.apapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xzg.xzglib.holder.BaseViewHolder;
import com.xzg.xzglib.inter.InterItemView;
import com.xzg.xzglib.inter.OnItemChildClickListener;
import com.xzg.xzglib.utils.RecyclerUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerArrayAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

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
        View view =
        return null;
    }

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

    private View createViewByType(ViewGroup parent, int viewType) {
        for (InterItemView headerView : headers) {
            if (headerView.hashCode() == viewType) {
                View view = headerView.onCreateView(parent);
                
            }
        }
    }


    public OnItemChildClickListener getOnItemChildClickListener() {
        return onItemChildClickListener;
    }
}
