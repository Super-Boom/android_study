package com.xzg.androidstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Exp extends AppCompatActivity {


    List<Integer> pics = new ArrayList<>();
    LinearLayout dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager2_exp);

        pics.add(R.mipmap.a);
        pics.add(R.mipmap.b);
        pics.add(R.mipmap.d);

        // 实例化adapter
        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // 每个图片元素都是由一个ViewHolder包含，ViewHolder需要传入一个View
                View v = LayoutInflater.from(ViewPager2Exp.this).inflate(R.layout.view_pager2_item, parent, false);
                return new ViewHolder(v);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ViewHolder h = (ViewHolder) holder;
                h.container.setBackgroundResource(pics.get(position));
            }

            @Override
            public int getItemCount() {
                return pics.size();
            }
        };
        // 获取 viewPager
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(adapter);

        // 添加选中事件
        dots = findViewById(R.id.dots);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                for (int i = 0; i < dots.getChildCount(); i++) {
                    // 将所有点设置为未选中
                    ((ImageView) dots.getChildAt(i)).setBackgroundResource(R.drawable.dot_unselect);
                }
                // 设置当前位置对应的点未选中状态
                ((ImageView) dots.getChildAt(position)).setBackgroundResource(R.drawable.dot_selected);
            }
        });


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
        }
    }
}