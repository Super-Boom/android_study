package com.xzg.androidstudy;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIv;
        private TextView mTv;
        private View mItemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            mIv = itemView.findViewById(R.id.iv);
//            mTv = itemView.findViewById(R.id.tv);
            mItemView = itemView;
        }
    }
}
