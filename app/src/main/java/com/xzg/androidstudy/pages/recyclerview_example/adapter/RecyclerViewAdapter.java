package com.xzg.androidstudy.pages.recyclerview_example.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.luck.picture.lib.tools.ScreenUtils;
import com.xzg.androidstudy.R;
import com.xzg.androidstudy.pages.recyclerview_example.Item;

import java.util.ArrayList;
import java.util.List;


/**
 * 1. 继承RecyclerView.Adapter
 * 2. 绑定ViewHolder
 * 3. 实现Adapter的相关方法
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private List<String> dataSource;


    private List<Item> cardList;

    private Context context;
    private RecyclerView recyclerView;
    private int addDataPosition = -1;

    // 元素属性
    private String title;
    private String img_url;
    private int width;
    private int height;

    private final double STANDARD_SCALE = 1.1;
    private final float SCALE = 4 * 1.0f / 3;


    public RecyclerViewAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.dataSource = new ArrayList<>();
        this.recyclerView = recyclerView;
    }

    public void setDataSource(List<String> dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false));
    }

    /**
     * ViewHolder绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(getIcon(position));
        holder.textView.setText(dataSource.get(position));

        /**
         * 只在瀑布流布局中使用随机高度
         */
        if (recyclerView.getLayoutManager().getClass() == StaggeredGridLayoutManager.class) {
            String imageView = dataSource.get(position);
            // 计算图片宽高
            ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
            float itemWidth = (ScreenUtils.getScreenWidth(context) - 20) / 2;
            layoutParams.width = (int) itemWidth;
            width = holder.imageView.getWidth();
            height = holder.imageView.getHeight();
            Log.d("-----width", String.valueOf(width));
            Log.d("-----height", String.valueOf(height));
            float scale = height / width;
            if (scale > STANDARD_SCALE) {
                layoutParams.height = (int) (itemWidth * SCALE);
            } else {
                layoutParams.height = (int) itemWidth;
            }


            holder.imageView.setLayoutParams(layoutParams);
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.imageView.setLayoutParams(params);
        }

        // 改ItemView的背景颜色
        if (addDataPosition == position) {
            holder.itemView.setBackgroundColor(Color.RED);
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#A4D3EE"));
        }
    }

    /**
     * 返回数据数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    private int getIcon(int position) {
        switch (position % 5) {
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;
            case 4:
                return R.drawable.avatar5;
        }
        return 0;
    }

    /**
     * 返回不同的ItemView高度
     *
     * @return
     */
    private int getRandomHeight() {
        return (int) (Math.random() * 1000);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
            textView = itemView.findViewById(R.id.tv);
        }
    }
}
