package com.xzg.androidstudy.pages.recyclerview_example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xzg.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private List<String> dataSource;
    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        this.dataSource = new ArrayList<>();
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
