package com.xzg.xzglib.holder;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.xzg.xzglib.apapter.RecyclerArrayAdapter;
import com.xzg.xzglib.inter.OnItemChildClickListener;

import java.lang.reflect.Field;

/**
 * <pre>
 *     @author xzg
 *     blog  : https://github.com/Super-Boom
 *     time  : 2021/11/29 16:51:31
 *     desc  : ViewHolder的简单封装，M为这个itemView对应的model,使用RecyclerArrayAdapter就一定要用这个ViewHolder
 *             推荐子类继承第二个构造函数。并将子类的构造函数设为一个ViewGroup parent
 *     revise:
 * </pre>
 */
public class BaseViewHolder<M> extends RecyclerView.ViewHolder {

    // 用来存放 View 以减少 findViewById 的次数
    private SparseArray<View> viewSparseArray;

    public BaseViewHolder(View itemView) {
        super(itemView);
        if (viewSparseArray == null) {
            viewSparseArray = new SparseArray<>();
        }
    }

    public BaseViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
        if (viewSparseArray == null) {
            viewSparseArray = new SparseArray<>();
        }
    }

    /**
     * 子类设置数据方法
     *
     * @param data data
     */
    public void setData(M data) {
    }

    /**
     * findViewById方式
     * 根据ID来获取View
     *
     * @param viewId viewID
     * @param <T>    View的泛型
     * @return 将结果强转为 View 或 View 的子类型
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取上下文context
     *
     * @return context
     */
    protected Context getContext() {
        return itemView.getContext();
    }

    /**
     * 获取数据索引的位置
     *
     * @return position
     */
    protected int getDataPosition() {
        RecyclerView.Adapter adapter = getOwnerAdapter();
        if (adapter instanceof RecyclerArrayAdapter) {
            int headerCount = ((RecyclerArrayAdapter) adapter).getHeaderCount();
            // 注意要减去header的count，否则造成索引错乱
            return getAdapterPosition() - headerCount;
        }
        return getAdapterPosition();
    }

    /**
     * 获取adapter对象
     *
     * @param <T> adapter
     * @return adapter
     */
    private <T extends RecyclerView.Adapter> T getOwnerAdapter() {
        RecyclerView recyclerView = getOwnerRecyclerView();
        return recyclerView != null ? (T) recyclerView.getAdapter() : null;
    }

    @Nullable
    private RecyclerView getOwnerRecyclerView() {
        try {
            Field field = RecyclerView.ViewHolder.class.getDeclaredField("mOwnerRecyclerView");
            field.setAccessible(true);
            return (RecyclerView) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 添加子控件点击事件
     *
     * @param viewId 控件id
     */
    protected void addOnClickListener(@IdRes final int viewId) {
        final View view = getView(viewId);
        if (view != null) {
            if (!view.isClickable()) {
                // 如果是不可点击，则需手动设置为可点击
                view.setClickable(true);
            }
            view.setOnClickListener(listener);
        }
    }


    /**
     * 创建listener监听，主要是item中的child点击监听
     */
    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (getOwnerAdapter() != null) {
                OnItemChildClickListener onItemChildClickListener = ((RecyclerArrayAdapter) getOwnerAdapter()).getOnItemChildClickListener();
                if (onItemChildClickListener != null) {
                    onItemChildClickListener.onItemChildClick(view, getDataPosition());
                }
            }
        }
    };

    /**
     * 设置TextView的值
     */
    public BaseViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
}
